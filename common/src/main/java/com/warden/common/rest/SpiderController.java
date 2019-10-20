package com.warden.common.rest;

import com.warden.common.MyWebSocket;
import com.warden.common.bean.Response;
import com.warden.common.utils.Spider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @author YangJiaYing
 * @date 2019/06/19
 */
@RestController
public class SpiderController {
    @Autowired
    MyWebSocket myWebSocket;
    @Autowired
    Spider spider;

    @RequestMapping(method = RequestMethod.GET, value = "/newData")
    public Response getNewData(String url) {
        try {
            System.out.println(url);
            MyWebSocket.sendInfo("start spider :"+url);
            spider.get(url);
            return Response.success();
        } catch (IOException e) {
            e.printStackTrace();
            return Response.failure(404, e.getMessage());
        }
    }
}
