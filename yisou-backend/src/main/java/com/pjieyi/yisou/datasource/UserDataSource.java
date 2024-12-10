package com.pjieyi.yisou.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.yisou.model.dto.user.UserQueryRequest;
import com.pjieyi.yisou.model.vo.UserVO;
import com.pjieyi.yisou.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户数据源相关
 */
@Service
public class UserDataSource implements DataSource<UserVO> {

    @Resource
    private UserService userService;

    @Override
    public Page<UserVO> doSearch(String searchText, int pageNum, int pageSize) {
        UserQueryRequest userQueryRequest = new UserQueryRequest();
        userQueryRequest.setUserName(searchText);
        userQueryRequest.setPageSize(pageSize);
        userQueryRequest.setCurrent(pageNum);
        return userService.listUserVOByPage(userQueryRequest);
    }
}
