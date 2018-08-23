package com.pojo;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@ToString
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
