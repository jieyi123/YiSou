package com.pjieyi.yisou.controller;

import com.pjieyi.yisou.common.BaseResponse;
import com.pjieyi.yisou.common.ErrorCode;
import com.pjieyi.yisou.common.ResultUtils;
import com.pjieyi.yisou.exception.ThrowUtils;
import com.pjieyi.yisou.manager.SearchFacade;
import com.pjieyi.yisou.model.dto.search.SearchQueryRequest;
import com.pjieyi.yisou.model.vo.SearchVO;
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
        return ResultUtils.success(searchFacade.searchAll(searchQueryRequest));
    }

}
