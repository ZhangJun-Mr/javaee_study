package com.service.impl;

import com.dao.RedPacketMapper;
import com.pojo.RedPacket;
import com.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedPacketServiceImpl implements RedPacketService {
    @Autowired
    private RedPacketMapper redPacketMapper;

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public RedPacket getRedPacket(Long id) {
        RedPacket redPacket = redPacketMapper.getRedPacket(id);
        return redPacket;
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = RuntimeException.class)
    public int decreaeRedPacket(Long id, Integer version) {
        int i = redPacketMapper.decreaseRedPacket(id, version);
        return i;
    }
}
