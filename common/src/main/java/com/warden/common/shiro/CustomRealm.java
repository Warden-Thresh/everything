package com.warden.common.shiro;

import com.warden.common.biz.PermissionBiz;
import com.warden.common.biz.UserBiz;
import com.warden.common.entity.Permission;
import com.warden.common.entity.Role;
import com.warden.common.entity.User;
import com.warden.common.service.RoleService;
import com.warden.common.service.UserService;
import com.warden.common.utils.JWTUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created with IntelliJ IDEA
 *
 * @author
 * 自定义 Realm
 * @date 2018-03-25
 */
@Component
public class CustomRealm extends AuthorizingRealm {
    private final UserService userService;
    private final RoleService roleService;
    private final UserBiz userBiz;
    private final PermissionBiz permissionBiz;

    @Autowired
    @Lazy
    public CustomRealm(UserService userService, RoleService roleService, UserBiz userBiz, PermissionBiz permissionBiz) {
        this.userService = userService;
        this.roleService = roleService;
        this.userBiz = userBiz;
        this.permissionBiz = permissionBiz;
    }

    /**
     * 必须重写此方法，不然会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    public String getName() {
        return "Default";
    }

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = (String) authenticationToken.getCredentials();
        Long userId = JWTUtil.getUserId(token);
        if (userId == null || !JWTUtil.verify(token, userId)) {
            throw new AuthenticationException("token认证失败！");
        }
        return new SimpleAuthenticationInfo(token, token, "Custom");
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————Custom权限认证————");
        Long userId = JWTUtil.getUserId(principalCollection.toString());
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        User user = userBiz.getUserById(userId);
        List<Role> roleList = userBiz.getRoleByUserId(userId);
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        Set<String> permissionSet = roleList.stream().flatMap(role -> permissionBiz.getPermissionByRoleId(role.getId()).stream()).map(Permission::getPermissionName).collect(Collectors.toSet());
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        //设置该用户拥有的角色
        info.setRoles(roleSet);
        info.addStringPermissions(permissionSet);
        return info;
    }
}
