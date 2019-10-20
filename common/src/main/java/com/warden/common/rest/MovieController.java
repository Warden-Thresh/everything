package com.warden.common.rest;

import com.warden.common.entity.Movie;
import com.warden.common.service.MovieService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJiaYing
 * @date 2019/05/27
 */
@RestController
@RequestMapping("/movie")
public class MovieController extends BaseController<Movie, MovieService> {

}
