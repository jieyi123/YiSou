package com.pjieyi.springbootinit.model.vo;

import cn.hutool.json.JSONUtil;
import com.pjieyi.springbootinit.model.entity.Picture;
import com.pjieyi.springbootinit.model.entity.Post;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.Date;
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
}
