package com.warden.common.service;

import com.warden.common.entity.Movie;
import com.warden.common.repository.MovieRepository;
import org.springframework.stereotype.Service;

/**
 * @author YangJiaYing
 * @date 2019/05/27
 */
@Service
public class MovieService extends BaseService<MovieRepository, Movie, Long> {

}
