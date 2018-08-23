package com.service.impl;

import com.dao.RedPacketMapper;
import com.dao.UserRedPacketMapper;
import com.pojo.RedPacket;
import com.pojo.UserRedPacket;
import com.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService {
    @Autowired
    UserRedPacketMapper userRedPacketMapper;

    @Autowired
    RedPacketMapper redPacketMapper;

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
            RedPacket redPacket = redPacketMapper.getRedPacket(redPacketId);
            if (redPacket.getStock() > 0) {
                int update = redPacketMapper.decreaseRedPacket(redPacketId, redPacket.getVersion());
                if (update == 0) {
                    continue;
                }
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(redPacket.getUnitAmount());
                userRedPacket.setNote("抢红包 " + redPacketId);

                int result = userRedPacketMapper.grapRedPacket(userRedPacket);
                return result;
            }
            return FALIED;
        }
    }
}
