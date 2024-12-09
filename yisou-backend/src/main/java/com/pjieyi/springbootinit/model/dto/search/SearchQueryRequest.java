package com.pjieyi.springbootinit.model.dto.search;

import com.pjieyi.springbootinit.common.PageRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Data
public class SearchQueryRequest extends PageRequest implements Serializable {
    /**
     * 搜索词
     */
    private String searchText;

    /**
     * 搜索类型
     * 文章-post 图片-picture
     */
    private String type;
}
