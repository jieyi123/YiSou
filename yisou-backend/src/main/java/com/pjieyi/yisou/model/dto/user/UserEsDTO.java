package com.pjieyi.yisou.model.dto.user;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.json.JSONUtil;
import com.pjieyi.yisou.model.entity.Post;
import com.pjieyi.yisou.model.entity.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 帖子 ES 包装类
 *
 **/
// todo 取消注释开启 ES（须先配置 ES）
@Document(indexName = "user")
@Data
public class UserEsDTO implements Serializable {

    private static final String DATE_TIME_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    /**
     * id
     */
    @Id
    private Long id;

    /**
     * 用户账号
     */
    private String userAccount;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 用户简介
     */
    private String userProfile;



    /**
     * 创建时间
     */
    @Field(index = false, store = true, type = FieldType.Date, format = {}, pattern = DATE_TIME_PATTERN)
    private Date createTime;

    /**
     * 更新时间
     */
    @Field(index = false, store = true, type = FieldType.Date, format = {}, pattern = DATE_TIME_PATTERN)
    private Date updateTime;

    /**
     * 是否删除
     */
    private Integer isDelete;

    private static final long serialVersionUID = 1L;

    /**
     * 对象转包装类
     *
     * @param user
     * @return
     */
    public static UserEsDTO objToDto(User user) {
        if (user == null) {
            return null;
        }
        UserEsDTO userEsDTO=new UserEsDTO();
        BeanUtils.copyProperties(user, userEsDTO);
        return userEsDTO;
    }

    /**
     * 包装类转对象
     *
     * @param userEsDTO
     * @return
     */
    public static User dtoToObj(UserEsDTO userEsDTO) {
        if (userEsDTO == null) {
            return null;
        }
        User user=new User();
        BeanUtils.copyProperties(userEsDTO, user);
        return user;
    }
}
