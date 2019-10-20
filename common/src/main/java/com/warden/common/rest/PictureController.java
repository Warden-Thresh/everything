package com.warden.common.rest;

import com.warden.common.entity.Picture;
import com.warden.common.service.PicureService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author YangJiaYing
 * @date 2019/05/28
 */
@RestController
@RequestMapping("/picture")
public class PictureController extends BaseController<Picture, PicureService> {

}
