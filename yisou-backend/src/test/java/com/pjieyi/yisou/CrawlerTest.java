package com.pjieyi.yisou;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.pjieyi.yisou.model.entity.Picture;
import com.pjieyi.yisou.model.vo.CommodityVo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class CrawlerTest {
    @Test
    void doCrawler() {
        String json = "busInfo={\"loginNo\":null,\"keyWord\":\"\",\"commodityTypeId\":\"10003\",\"sortType\":\"2\",\"sortParam\":\"PRICE\",\"pageNo\":\"1\",\"pageSize\":\"10\"}&appKey=C2F9BEBCE8DD3CEDFF8016A4B8248FF4&timeStamp=2024-12-03 \"10\":30:00&sign=aee2c205801a2c7a716627eb8136d143";
        String url= "https://www.gzsuanli.com/slzx/ecosp/v1.0/commodity/queryList";
        String result2 = HttpRequest.post(url)
                .body(json)
                .execute().body();
        Map resultMap = JSONUtil.toBean(result2, Map.class);
        JSONObject busiResp = (JSONObject)resultMap.get("busiResp");
        JSONObject busiDataResp = (JSONObject)busiResp.get("busiDataResp");
        JSONArray commodityInfos = (JSONArray)busiDataResp.get("commodityInfos");
        List<CommodityVo> commodityVos=new ArrayList<>();
        for (Object commodityInfo : commodityInfos) {
            if (commodityVos.size()>=10){
                break;
            }
            JSONObject commodityInfoJson = (JSONObject) commodityInfo;
            CommodityVo commodityVo=new CommodityVo();
            commodityVo.setCommodityName(commodityInfoJson.getStr("commodityName"));
            commodityVo.setCommodityId(commodityInfoJson.getStr("commodityId"));
            commodityVo.setUseScene(commodityInfoJson.getStr("useScene"));
            commodityVo.setMinPrice(commodityInfoJson.getStr("minPrice"));
            commodityVos.add(commodityVo);
        }
        System.out.println(commodityVos);
    }
    @Test
    void testFetchPicture() throws IOException {
        int current = 1;
        String url = "https://cn.bing.com/images/search?q=美女图片&first=" + current;
        Document doc = Jsoup.connect(url).get();
        Elements elements = doc.select(".iuscp.isv");
        List<Picture> pictures = new ArrayList<>();
        for (Element element : elements) {
            // 取图片地址（murl）
            String m = element.select(".iusc").get(0).attr("m");
            Map<String, Object> map = JSONUtil.toBean(m, Map.class);
            String murl = (String) map.get("murl");
//            System.out.println(murl);
            // 取标题
            String title = element.select(".inflnk").get(0).attr("aria-label");

            Picture picture = new Picture();
            picture.setTitle(title);
            picture.setUrl(murl);
            pictures.add(picture);
        }
        System.out.println(pictures);
    }

}
