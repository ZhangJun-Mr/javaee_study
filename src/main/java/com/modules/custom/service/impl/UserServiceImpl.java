package com.modules.custom.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.modules.custom.mapper.UserMapper;
import com.modules.custom.pojo.User;
import com.modules.custom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhangjun
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    UserMapper userMapper;

    private static final int FALIED = 0;


    @Override
    public List<User> findList(User user) {
        return userMapper.findList(user);
    }
}
