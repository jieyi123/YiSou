package com.pjieyi.yisou.datasource;

import com.pjieyi.yisou.model.enums.SearchTypeEnum;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataSourceRegistry {

    @Resource
    private PictureDataSource pictureDataSource;
    @Resource
    private PostDataSource postDataSource;
    @Resource
    private UserDataSource userDataSource;

    private Map<String,DataSource<?>> dataSourceMap;

    @PostConstruct
    public void init(){
        System.out.println("bean加载完后，初始化map");
        dataSourceMap=new HashMap(){
            {
                put(SearchTypeEnum.USER.getValue(),userDataSource);
                put(SearchTypeEnum.POST.getValue(),postDataSource);
                put(SearchTypeEnum.PICTURE.getValue(),pictureDataSource);
            }
        };
    }

    public DataSource<?> getDataSourceByType(String type){
        if (dataSourceMap==null){
            return null;
        }
        return dataSourceMap.get(type);
    }

}
