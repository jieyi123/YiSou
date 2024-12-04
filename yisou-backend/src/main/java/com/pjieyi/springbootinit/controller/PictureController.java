package com.pjieyi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.springbootinit.common.BaseResponse;
import com.pjieyi.springbootinit.common.ErrorCode;
import com.pjieyi.springbootinit.common.ResultUtils;
import com.pjieyi.springbootinit.exception.ThrowUtils;
import com.pjieyi.springbootinit.model.dto.picture.PictureQueryRequest;
import com.pjieyi.springbootinit.model.entity.Picture;
import com.pjieyi.springbootinit.model.entity.Post;
import com.pjieyi.springbootinit.model.vo.PostVO;
import com.pjieyi.springbootinit.service.PictureService;
import com.pjieyi.springbootinit.service.PostService;
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
