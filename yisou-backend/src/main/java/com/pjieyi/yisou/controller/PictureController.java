package com.pjieyi.yisou.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.yisou.common.BaseResponse;
import com.pjieyi.yisou.common.ErrorCode;
import com.pjieyi.yisou.common.ResultUtils;
import com.pjieyi.yisou.exception.ThrowUtils;
import com.pjieyi.yisou.model.dto.picture.PictureQueryRequest;
import com.pjieyi.yisou.model.entity.Picture;
import com.pjieyi.yisou.service.PictureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片接口
 *

 */
@RestController
@RequestMapping("/picture")
@Slf4j
public class PictureController {

    @Resource
    private PictureService pictureService;

    /**
     * 分页获取列表（封装类）
     * @param pictureQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/list/page/vo")
    public BaseResponse<Page<Picture>> listPostVOByPage(@RequestBody PictureQueryRequest pictureQueryRequest,
                                                        HttpServletRequest request) {
        // 限制爬虫
        ThrowUtils.throwIf(pictureQueryRequest.getPageSize() > 20, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(pictureService.getPicturePage(pictureQueryRequest, request));
    }

}
