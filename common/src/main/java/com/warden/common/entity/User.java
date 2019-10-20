package com.warden.common.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.annotatoin.AssignID;
import org.beetl.sql.core.annotatoin.AutoID;
import org.beetl.sql.core.orm.OrmCondition;
import org.beetl.sql.core.orm.OrmQuery;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * @author YangJiaYing
 * @date 2018/8/3
 */
@Entity
@Table
@Data
@DynamicInsert
@DynamicUpdate
public class User extends BaseEntity{
    @Id
    @AutoID
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;
    @Column(columnDefinition = "int default 0")
    private Integer notifyCount;
    @Basic
    @Column(name = "_group")
    private String group;
    @Column(columnDefinition = "bit default 0")
    private Boolean active;
    private String openid;
    private String email;
    private String phoneNumber;
    private String avatar;
    private String signature;
    @ManyToMany
//    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Role> role;
}
