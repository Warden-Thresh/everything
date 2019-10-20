package com.warden.common.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.List;

/**
 *
 * @author Warden
 * @date 2017/4/21
 */

@Data
@Table
@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String cover;
    private String title;
    private String url;
    private String movieId;
    private String description;
    private String type;
    private String region;
    @Lob
    @Column(columnDefinition = "text")
    private String synopsis;
    private String category;
    private String extra;
    @OneToMany
    @JoinColumn
    private List<Picture> pictures;
    @ManyToMany
    @JoinTable(
            name = "movie_actor",
            joinColumns = {@JoinColumn(name = "movie_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")}
    )
    private List<Actor> castList;
}
