package com.pjieyi.springbootinit.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.springbootinit.model.dto.picture.PictureQueryRequest;
import com.pjieyi.springbootinit.model.dto.post.PostQueryRequest;
import com.pjieyi.springbootinit.model.dto.search.SearchQueryRequest;
import com.pjieyi.springbootinit.model.dto.user.UserQueryRequest;
import com.pjieyi.springbootinit.model.entity.Picture;
import com.pjieyi.springbootinit.model.enums.SearchTypeEnum;
import com.pjieyi.springbootinit.model.vo.PostVO;
import com.pjieyi.springbootinit.model.vo.SearchVO;
import com.pjieyi.springbootinit.model.vo.UserVO;
import com.pjieyi.springbootinit.service.PictureService;
import com.pjieyi.springbootinit.service.PostService;
import com.pjieyi.springbootinit.service.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 搜索门面-根据不同的类型返回不同的结果
 */
@Component
public class SearchFacade {

    @Resource
    private PictureService pictureService;

    @Resource
    private UserService userService;
    @Resource
    private PostService postService;

    public SearchVO searchAll(SearchQueryRequest searchQueryRequest,
                              HttpServletRequest request){
        SearchVO searchVO=new SearchVO();
        String type= searchQueryRequest.getType();
        String searchText= searchQueryRequest.getSearchText();
        int pageSize = searchQueryRequest.getPageSize();
        int current = searchQueryRequest.getCurrent();
        SearchTypeEnum enumByValue = SearchTypeEnum.getEnumByValue(type);
        if (enumByValue==null){
            //全量搜索
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
            searchVO.setPostList(postVOPage.getRecords());
            searchVO.setUserList(userVOPage.getRecords());
            searchVO.setPictureList(picturePage.getRecords());
        }else {
            switch (enumByValue){
                case USER:
                    UserQueryRequest userQueryRequest=new UserQueryRequest();
                    userQueryRequest.setUserName(searchText);
                    userQueryRequest.setPageSize(pageSize);
                    userQueryRequest.setCurrent(current);
                    Page<UserVO> userVOPage = userService.listUserVOByPage(userQueryRequest);
                    searchVO.setUserList(userVOPage.getRecords());
                    break;
                case POST:
                    PostQueryRequest postQueryRequest=new PostQueryRequest();
                    postQueryRequest.setSearchText(searchText);
                    postQueryRequest.setCurrent(current);
                    postQueryRequest.setPageSize(pageSize);
                    Page<PostVO> postVOPage = postService.listPostVOByPage(postQueryRequest, request);
                    searchVO.setPostList(postVOPage.getRecords());
                    break;
                case PICTURE:
                    PictureQueryRequest pictureQueryRequest=new PictureQueryRequest();
                    pictureQueryRequest.setSearchText(searchText);
                    pictureQueryRequest.setCurrent(current);
                    pictureQueryRequest.setPageSize(pageSize);
                    Page<Picture> picturePage = pictureService.getPicturePage(pictureQueryRequest, request);
                    searchVO.setPictureList(picturePage.getRecords());
                default:
                    break;
            }
        }
        return searchVO;
    }
}
