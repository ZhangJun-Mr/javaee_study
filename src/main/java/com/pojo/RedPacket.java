package com.pojo;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class RedPacket {
    private Long id;
    private Long userId;
    private Double amount;
    private Timestamp sendDate;
    private Integer total;
    private Double unitAmount;
    private Integer stock;
    private Integer version;
    private String note;
}
