package com.modules.custom.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author zhangjun
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@ToString
@EqualsAndHashCode
@TableName("sys_user")
public class User implements Serializable {

    private static final long serialVersionUID = 273801613517020022L;
    /** id */
    private String id;
    /** 用户名 */
    private String userName;
    /** 密码 */
    private String password;
    /** 昵称 */
    private String nickName;
    /** 创建时间 */
    private String createDate;


}
