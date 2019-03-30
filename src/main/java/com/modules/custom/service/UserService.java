package com.modules.custom.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.modules.custom.pojo.User;

import java.util.List;

/**
 * @author zhangjun
 */
public interface UserService {
    /**
     * 查询列表
     * @return
     */
    List<User> findList(User user);

}
