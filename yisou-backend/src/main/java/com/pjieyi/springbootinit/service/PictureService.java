package com.pjieyi.springbootinit.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.springbootinit.model.dto.picture.PictureQueryRequest;
import com.pjieyi.springbootinit.model.entity.Picture;

import javax.servlet.http.HttpServletRequest;

/**
 * 图片服务
 *
 */
public interface PictureService {

    /**
     * 分页获取图片
     *
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    Page<Picture> getPicturePage(PictureQueryRequest pictureQueryRequest, HttpServletRequest request);
}
