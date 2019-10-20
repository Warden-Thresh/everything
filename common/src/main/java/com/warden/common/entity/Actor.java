package com.warden.common.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 * @author YangJiaYing
 * @date 2019/05/27
 */
@Data
@Table
@Entity
public class Actor {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    private String name;
    private String country;
    private String profiles;
}