package com.pjieyi.yisou.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.yisou.datasource.DataSource;
import com.pjieyi.yisou.datasource.PictureDataSource;
import com.pjieyi.yisou.datasource.PostDataSource;
import com.pjieyi.yisou.datasource.UserDataSource;
import com.pjieyi.yisou.model.dto.search.SearchQueryRequest;
import com.pjieyi.yisou.model.entity.Picture;
import com.pjieyi.yisou.model.enums.SearchTypeEnum;
import com.pjieyi.yisou.model.vo.PostVO;
import com.pjieyi.yisou.model.vo.SearchVO;
import com.pjieyi.yisou.model.vo.UserVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * 搜索门面-根据不同的类型返回不同的结果
 */
@Component
public class SearchFacade {

    @Resource
    private PictureDataSource pictureDataSource;
    @Resource
    private PostDataSource postDataSource;
    @Resource
    private UserDataSource userDataSource;

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
            Page<UserVO> userVOPage = userDataSource.doSearch(searchText, current, pageSize);
            //封装获取文章列表
            Page<PostVO> postVOPage = postDataSource.doSearch(searchText, current, pageSize);
            //封装图片列表
            Page<Picture> picturePage = pictureDataSource.doSearch(searchText, current, pageSize);
            searchVO.setPostList(postVOPage.getRecords());
            searchVO.setUserList(userVOPage.getRecords());
            searchVO.setPictureList(picturePage.getRecords());
        }else {
            HashMap<String, DataSource<?>> map = new HashMap(){
                {
                    put(SearchTypeEnum.USER.getValue(),userDataSource);
                    put(SearchTypeEnum.POST.getValue(),postDataSource);
                    put(SearchTypeEnum.PICTURE.getValue(),pictureDataSource);
                }
            };
            DataSource<?> dataSource = map.get(type);
            Page<?> page = dataSource.doSearch(searchText, current, pageSize);
            searchVO.setDataList(page.getRecords());
        }
        return searchVO;
    }
}
