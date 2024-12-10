package com.pjieyi.yisou.datasource;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.yisou.model.dto.picture.PictureQueryRequest;
import com.pjieyi.yisou.model.entity.Picture;
import com.pjieyi.yisou.service.PictureService;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片数据源相关
 */
@Service
public class PictureDataSource implements DataSource<Picture> {
    @Resource
    private PictureService pictureService;
    @Override
    public Page<Picture> doSearch(String searchText, int pageNum, int pageSize) {
        PictureQueryRequest pictureQueryRequest=new PictureQueryRequest();
        pictureQueryRequest.setSearchText(searchText);
        pictureQueryRequest.setCurrent(pageNum);
        pictureQueryRequest.setPageSize(pageSize);
        HttpServletRequest request=((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest();
        return pictureService.getPicturePage(pictureQueryRequest,request);
    }
}
