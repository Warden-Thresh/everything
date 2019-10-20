package com.warden.common.shiro;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.HashMap;


/**
 * @author: YangJiaYing
 * @date: 2018/8/6
 */

public class UserModularRealmAuthenticator extends ModularRealmAuthenticator {
    private static final Logger logger = LoggerFactory.getLogger(UserModularRealmAuthenticator.class);

    @Override
    protected AuthenticationInfo doAuthenticate(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        logger.info("UserModularRealmAuthenticator:method doAuthenticate() execute ");

        JWTToken token = (JWTToken) authenticationToken;

        // 登录类型
        LoginType loginType = token.getLoginType();
        // 所有Realm
        Collection<Realm> realms = getRealms();
        // 登录类型对应的所有Realm
        HashMap<String, Realm> realmHashMap = new HashMap<>(realms.size());
        for (Realm realm : realms) {
            realmHashMap.put(realm.getName(), realm);
        }

        System.out.println(loginType.toString());
        System.out.println("realmHashMap = " + realmHashMap);
        if (realmHashMap.get(loginType.toString()) != null) {
            logger.info("doSingleRealmAuthentication() execute ");
            return doSingleRealmAuthentication(realmHashMap.get(loginType.toString()), token);
        } else {

            logger.info("doMultiRealmAuthentication() execute wechat");
            return doMultiRealmAuthentication(realms, token);
        }

    }
}
