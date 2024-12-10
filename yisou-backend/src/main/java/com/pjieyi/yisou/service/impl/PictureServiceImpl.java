package com.pjieyi.yisou.service.impl;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.util.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.pjieyi.yisou.model.dto.picture.PictureQueryRequest;
import com.pjieyi.yisou.model.entity.Picture;
import com.pjieyi.yisou.service.PictureService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PictureServiceImpl implements PictureService {
    @Override
    public Page<Picture> getPicturePage(PictureQueryRequest pictureQueryRequest, HttpServletRequest request) {
        long pageSize = pictureQueryRequest.getPageSize();
        long pageNum= pictureQueryRequest.getCurrent();
        long current= (pageNum-1)*pageSize;
        String searchText = pictureQueryRequest.getSearchText();
        if (StringUtils.isEmpty(searchText)){
            searchText="默认图片";
        }
        String url = String.format("https://cn.bing.com/images/search?q=%s&first=%s",searchText,current);
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Elements elements = doc.select(".iuscp.isv");
        List<Picture> pictures = new ArrayList<>();
        for (Element element : elements) {
            if (pictures.size() >= pageSize){
                break;
            }
            // 取图片地址（murl）
            String m = element.select(".iusc").get(0).attr("m");
            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            String murl = (String) map.get("murl");
            // 取标题
            String title = element.select(".inflnk").get(0).attr("aria-label");
            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(murl);
            pictures.add(picture);
        }
        Page<Picture> picturePage = new Page<>(pageNum, pageSize);
        picturePage.setRecords(pictures);
        return picturePage;
    }
}
