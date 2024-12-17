package com.pjieyi.yisou.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.yisou.model.dto.post.PostQueryRequest;
import com.pjieyi.yisou.model.entity.Post;
import com.pjieyi.yisou.model.vo.PostVO;
import com.pjieyi.yisou.service.PostService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 帖子数据源相关
 */
@Service
public class PostDataSource implements DataSource<PostVO> {

    @Resource
    private PostService postService;

    @Override
    public Page<PostVO> doSearch(String searchText, int pageNum, int pageSize) {
        PostQueryRequest postQueryRequest=new PostQueryRequest();
        postQueryRequest.setSearchText(searchText);
        postQueryRequest.setPageSize(pageSize);
        postQueryRequest.setCurrent(pageNum);
        HttpServletRequest request=((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        Page<Post> postPage = postService.searchFromEs(postQueryRequest);
        return postService.getPostVOPage(postPage,request);
    }
}
