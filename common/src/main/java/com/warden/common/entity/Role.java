package com.warden.common.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beetl.sql.core.orm.OrmCondition;
import org.beetl.sql.core.orm.OrmQuery;

import javax.persistence.*;
import java.util.List;

/**
 * @author: YangJiaYing
 * @date: 2018/8/3
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table
public class Role extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String roleName;
    @ManyToMany
    private List<Permission> permission;
}
