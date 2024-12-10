package com.pjieyi.yisou.model.dto.search;

import com.pjieyi.yisou.common.PageRequest;
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
