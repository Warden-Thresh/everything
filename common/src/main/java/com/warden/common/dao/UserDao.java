package com.warden.common.dao;

import com.warden.common.entity.Role;
import com.warden.common.entity.User;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author YangJiaYing
 * @date 2019/05/19
 */
@Repository
public interface UserDao extends BaseMapper<User> {

    List<User> sample(String userName);

    /**
     * 根据userId查询角色
     * @param userId userID
     * @return roleList
     */
    List<Role> getRoleByUserId(Long userId);

    User selectUserAndRole(String userId);

    PageQuery pageQueryByCondition(int pageNumber,int pageSize,String name);
}
