package com.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class UserRedPacket {
    private Long id;
    private Long redPacketId;
    private Long userId;
    private Double amount;
    private Timestamp grabTime;
    private String note;
}
