package com.warden.common.repository;

import com.warden.common.entity.Movie;
import org.springframework.stereotype.Repository;

/**
 * @author YangJiaYing
 * @date 2019/05/27
 */
@Repository
public interface MovieRepository extends BaseRepository<Movie,Long> {
}
