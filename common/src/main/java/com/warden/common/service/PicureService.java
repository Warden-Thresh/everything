package com.warden.common.service;

import com.warden.common.entity.Picture;
import com.warden.common.repository.PictureRepository;
import org.springframework.stereotype.Service;

/**
 * @author YangJiaYing
 * @date 2019/05/27
 */
@Service
public class PicureService extends BaseService<PictureRepository, Picture,Long> {
}
