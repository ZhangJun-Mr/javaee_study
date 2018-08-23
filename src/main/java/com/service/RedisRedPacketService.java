package com.service;

public interface RedisRedPacketService {
    void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount);
}
