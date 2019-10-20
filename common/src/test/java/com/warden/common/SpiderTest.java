package com.warden.common;

import com.warden.common.entity.Movie;
import com.warden.common.entity.Picture;
import com.warden.common.repository.MovieRepository;
import com.warden.common.service.MovieService;
import com.warden.common.service.PicureService;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@SpringBootTest
@RunWith(SpringRunner.class)
public class SpiderTest {
    @Autowired
    MovieService movieService;
    @Autowired
    PicureService picureService;
    @Autowired
    MovieRepository movieRepository;

    @Test
    @Transactional
    @Rollback(false)
    public void get() throws IOException {
        List<Movie> articalList;
        // write your code here
        String url = "http://s1.xp3839.pw/pw/thread.php?fid=18&page=1";
        Connection conn = Jsoup.connect(url);
        // 修改http包中的header,伪装成浏览器进行抓取
        conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
        Document doc = conn.get();
//        System.out.println(doc.toString());

        // 获取tbody元素下的所有tr元素
        Elements elements = doc.select("[class=tr3 t_one]");
        //System.out.printf(elements.toString());
        Elements elements1 = elements.select("h3");
        for (Element element : elements1) {
            Elements Article = element.select("a");
            String titleString = Article.text();
            String articleUrl = "http://h3.cnmbtgf.club/pw/" + Article.get(0).attr("href");
            String[] queryStringSplit = articleUrl.split("/");
            String idString = queryStringSplit[queryStringSplit.length - 1];
            String Id = idString.split(".h")[0];

            //String time = element.select("td.text-left cxxt-holdtime").first().text();
            if (!"read.".equals(Id)) {
                try {
                    String address = element.getElementsByClass("text-success company").text();
                    String title = "";
                    try {
                        title = titleString.split("\\[")[2].split("]")[1];
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    List<String> list = new ArrayList<String>();
                    Pattern p = Pattern.compile("(\\[[^\\]]*\\])");
                    Matcher m = p.matcher(titleString);
                    while (m.find()) {
                        list.add(m.group().substring(1, m.group().length() - 1));
                    }
                    String updateData = list.get(0);
                    String region = list.get(1).split("/")[0];
                    String category = list.get(1).split("/")[1];
                    String extra = list.get(2);
                    Movie movie = new Movie();
                    movie.setMovieId(Id);
                    movie.setTitle(title);
                    movie.setUrl(articleUrl);
                    movie.setRegion(region);
                    movie.setCategory(category);
                    movie.setExtra(extra);
                    Connection conn1 = Jsoup.connect(articleUrl);
                    // 修改http包中的header,伪装成浏览器进行抓取
                    conn1.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
                    Document doc1 = conn1.get();
                    //System.out.println(doc.toString());

                    // 获取tbody元素下的所有tr元素
                    Elements elements2 = doc1.select("[class=f14]");
                    Elements elements3 = elements2.select("img");
                    String synopsis = elements2.get(0).text();
                    String[] a = elements2.get(0).text().split("【");
                    movie.setSynopsis(synopsis);
                    List<Picture> pictureList = new ArrayList<>();
                    for (Element element1 : elements3) {
                        Picture picture = new Picture();
                        Elements Picture = element1.select("img");
                        String url1 = Picture.get(0).attr("src");
                        String[] queryStringSplit1 = url1.split("/");
                        String idString1 = queryStringSplit[queryStringSplit1.length - 1];
                        String Id1 = idString1.split(".j")[0];
                        picture.setUrl(url1);
                        System.out.println("Url:" + url1);
                        System.out.println("ID:" + Id1);
                        System.out.println("---------------------------------");
                        pictureList.add(picture);
                    }
                    movie.setCover(pictureList.get(0).getUrl());
                    movie.setPictures(pictureList);
                    System.out.println("movie:" + movie);
                    picureService.insert(pictureList);
                    movieService.insert(movie);

                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }


            } else {
                System.out.println("shib标题：" + titleString);
                System.out.println("Url:" + articleUrl);
                System.out.println("ID:" + Id);
                System.out.println("---------------------------------");
            }


        }
    }

    @Test
    public void getDetail() throws IOException {
        Connection conn = Jsoup.connect("http://s1.xp3839.pw/pw/html_data/5/1905/4092763.html");
        // 修改http包中的header,伪装成浏览器进行抓取
        conn.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/    20100101 Firefox/32.0");
        Document doc = conn.get();
        //System.out.println(doc.toString());

        // 获取tbody元素下的所有tr元素
        Elements elements = doc.select("[class=f14]");
//        System.out.printf(elements.toString());
        Elements elements1 = elements.select("img");
        System.out.println(elements.get(0).text());
        String[] a = elements.get(0).text().split("【");
        System.out.println(a);
        for (Element element : elements1) {
            Elements Picture = element.select("img");
            String url1 = Picture.get(0).attr("src");
            String[] queryStringSplit = url1.split("/");
            String idString = queryStringSplit[queryStringSplit.length - 1];
            String Id = idString.split(".j")[0];

            System.out.println("Url:" + url1);
            System.out.println("ID:" + Id);
            System.out.println("---------------------------------");


        }
    }


}
