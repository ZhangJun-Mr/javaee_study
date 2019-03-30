package com.modules.custom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.modules.custom.pojo.User;

import java.util.List;

/**
 * @author zhangjun
 */
public interface UserService extends IService<User> {
    /**
     * 查询列表
     * @return
     */
    List<User> findList(User user);

}
