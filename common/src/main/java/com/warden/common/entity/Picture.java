package com.warden.common.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * @author YangJiaYing
 * @date 2019/05/27
 */
@Data
@Table
@Entity
public class Picture {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;
    private String url;
    private String category;
}