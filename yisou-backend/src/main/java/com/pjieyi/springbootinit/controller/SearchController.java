package com.pjieyi.springbootinit.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.springbootinit.common.BaseResponse;
import com.pjieyi.springbootinit.common.ErrorCode;
import com.pjieyi.springbootinit.common.ResultUtils;
import com.pjieyi.springbootinit.exception.ThrowUtils;
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
    private PictureService pictureService;

    @Resource
    private UserService userService;
    @Resource
    private PostService postService;

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
        int pageSize = searchQueryRequest.getPageSize();
        int current = searchQueryRequest.getCurrent();
        String searchText = searchQueryRequest.getSearchText();

        //封装获取用户列表
        UserQueryRequest userQueryRequest=new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setPageSize(pageSize);
        userQueryRequest.setCurrent(current);
        Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);

        //封装获取文章列表
        PostQueryRequest postQueryRequest=new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setCurrent(current);
        postQueryRequest.setPageSize(pageSize);
        Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, request);

        //封装图片列表
        PictureQueryRequest pictureQueryRequest=new PictureQueryRequest();
        pictureQueryRequest.setSearchText(searchText);
        pictureQueryRequest.setCurrent(current);
        pictureQueryRequest.setPageSize(pageSize);
        Page<Picture> picturePage = pictureService.getPicturePage(pictureQueryRequest, request);
        SearchVO searchVO=new SearchVO();
        searchVO.setPostList(postVOPage.getRecords());
        searchVO.setUserList(userVOPage.getRecords());
        searchVO.setPictureList(picturePage.getRecords());
        return ResultUtils.success(searchVO);
    }

}
