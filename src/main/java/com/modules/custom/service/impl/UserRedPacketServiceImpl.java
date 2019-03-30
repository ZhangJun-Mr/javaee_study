package com.modules.custom.service.impl;

import com.modules.custom.mapper.UserRedPacketMapper;
import com.modules.custom.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Autowired
    UserRedPacketMapper userRedPacketMapper;

    private static final int FALIED = 0;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public int grapRedPacket(Long redPacketId, Long userId) {
        long start = System.currentTimeMillis();
        while (true) {
            long end = System.currentTimeMillis();
            if (end - start > 100) {
                return FALIED;
            }
            return FALIED;
        }
    }
}
