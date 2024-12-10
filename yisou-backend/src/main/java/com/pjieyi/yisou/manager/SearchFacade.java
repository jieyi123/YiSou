package com.pjieyi.yisou.manager;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.yisou.datasource.*;
import com.pjieyi.yisou.model.dto.search.SearchQueryRequest;
import com.pjieyi.yisou.model.entity.Picture;
import com.pjieyi.yisou.model.enums.SearchTypeEnum;
import com.pjieyi.yisou.model.vo.PostVO;
import com.pjieyi.yisou.model.vo.SearchVO;
import com.pjieyi.yisou.model.vo.UserVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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

    @Resource
    private DataSourceRegistry dataSourceRegistry;

    public SearchVO searchAll(SearchQueryRequest searchQueryRequest){
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
            DataSource<?> dataSource = dataSourceRegistry.getDataSourceByType(type);
            Page<?> page = dataSource.doSearch(searchText, current, pageSize);
            searchVO.setDataList(page.getRecords());
        }
        return searchVO;
    }
}
