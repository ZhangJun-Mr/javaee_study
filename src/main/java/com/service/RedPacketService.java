package com.service;

import com.pojo.RedPacket;

public interface RedPacketService {
    RedPacket getRedPacket(Long id);

    int decreaeRedPacket(Long id);
}
