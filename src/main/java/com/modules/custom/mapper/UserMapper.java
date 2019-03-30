package com.modules.custom.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.modules.custom.pojo.User;

import java.util.List;

public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询列表
     * @return
     */
    List<User> findList(User user);
}
