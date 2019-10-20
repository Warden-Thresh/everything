package com.warden.common.utils;

/**
 * @author YangJiaYing
 * @date 2019/05/19
 */

import com.warden.common.entity.User;
import org.beetl.sql.core.*;
import org.beetl.sql.core.db.DBStyle;
import org.beetl.sql.core.db.MySqlStyle;
import org.beetl.sql.ext.DebugInterceptor;
import org.beetl.sql.ext.gen.GenConfig;

import java.util.List;

/**
 * GenCode class
 */
public class GenCode {
    public static void main(String[] args) throws Exception {
        ConnectionSource source = ConnectionSourceHelper.getSimple("com.mysql.cj.jdbc.Driver", "jdbc:mysql:///everything?serverTimezone=UTC&useUnicode=true&characterEncoding=utf-8&autoReconnect=true&useSSL=false", "root", "admin");
        DBStyle mysql = new MySqlStyle();
        // sql语句放在classpagth的/sql 目录下
        SQLLoader loader = new ClasspathLoader("/sql");
        // 数据库命名跟java命名一样，所以采用DefaultNameConversion，还有一个是UnderlinedNameConversion，下划线风格的，
        UnderlinedNameConversion nc = new UnderlinedNameConversion();
        // 最后，创建一个SQLManager,DebugInterceptor 不是必须的，但可以通过它查看sql执行情况
        SQLManager sqlManager = new SQLManager(mysql, loader, source, nc, new Interceptor[]{new DebugInterceptor()});
        //使用内置的生成的sql 新增用户，如果需要获取主键，可以传入KeyHolder
//        sqlManager.genPojoCodeToConsole("user");

        GenConfig config = new GenConfig();
        config.preferBigDecimal(true);
        sqlManager.genSQLTemplateToConsole("permission");
        User query = new User();

    }
}
