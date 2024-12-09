package com.pjieyi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.springbootinit.common.BaseResponse;
import com.pjieyi.springbootinit.common.ErrorCode;
import com.pjieyi.springbootinit.common.ResultUtils;
import com.pjieyi.springbootinit.exception.ThrowUtils;
import com.pjieyi.springbootinit.manager.SearchFacade;
import com.pjieyi.springbootinit.model.dto.picture.PictureQueryRequest;
import com.pjieyi.springbootinit.model.dto.post.PostQueryRequest;
import com.pjieyi.springbootinit.model.dto.search.SearchQueryRequest;
import com.pjieyi.springbootinit.model.dto.user.UserQueryRequest;
import com.pjieyi.springbootinit.model.entity.Picture;
import com.pjieyi.springbootinit.model.vo.PostVO;
import com.pjieyi.springbootinit.model.vo.SearchVO;
import com.pjieyi.springbootinit.model.vo.UserVO;
import com.pjieyi.springbootinit.service.PictureService;
import com.pjieyi.springbootinit.service.PostService;
import com.pjieyi.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 聚合搜索接口
 *

 */
@RestController
@RequestMapping("/search")
@Slf4j
public class SearchController {

    @Resource
    private SearchFacade searchFacade;

    /**
     * 分页获取列表（封装类）
     * @param searchQueryRequest
     * @param request
     * @return
     */
    @PostMapping("/all")
    public BaseResponse<SearchVO> listPostVOByPage(@RequestBody SearchQueryRequest searchQueryRequest,
                                                   HttpServletRequest request) {
        // 限制爬虫
        ThrowUtils.throwIf(searchQueryRequest.getPageSize() > 20, ErrorCode.PARAMS_ERROR);
        return ResultUtils.success(searchFacade.searchAll(searchQueryRequest,request));
    }

}
