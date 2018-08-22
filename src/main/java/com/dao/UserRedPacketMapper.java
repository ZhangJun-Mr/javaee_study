package com.dao;

import com.pojo.UserRedPacket;

public interface UserRedPacketMapper {
    /**
     * 插入抢红包信息
     * @param userRedPacket 抢红包信息
     * @return 影响记录数
     */
    int grapRedPacket(UserRedPacket userRedPacket);
}
