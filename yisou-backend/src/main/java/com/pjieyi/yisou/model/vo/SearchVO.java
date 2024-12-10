package com.pjieyi.yisou.model.vo;

import com.pjieyi.yisou.model.entity.Picture;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 搜索结果视图
 *
 */
@Data
public class SearchVO implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<UserVO> userList;
    private List<PostVO> postList;
    private List<Picture> pictureList;
    private List<?> dataList;
}
