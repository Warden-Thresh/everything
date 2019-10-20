package com.warden.common.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.sql.Timestamp;

/**
 * @author : YangJiaYing
 * @date : 2019/05/14
 */
@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    /**
     * 创建时间
     */
    @Column(name = "create_time")
    @CreatedDate
    private Timestamp createTime;
    /**
     * 创建人
     */
    @CreatedBy
    private String createBy;
    /**
     * 修改时间
     */
    @Column(name = "last_modified_time")
    @LastModifiedDate
    private Timestamp lastModifiedTime;
    /**
     * 修改人
     */
    @LastModifiedBy
    private String lastModifiedBy;
}
