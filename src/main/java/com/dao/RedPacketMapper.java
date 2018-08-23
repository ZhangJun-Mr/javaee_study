package com.dao;

import com.pojo.RedPacket;
import org.apache.ibatis.annotations.Param;

public interface RedPacketMapper {
    /**
     * 获取红包信息
     * @param id 红包id
     * @return 红包具体信息
     */
    RedPacket getRedPacket(Long id);

    /**
     * 扣减抢红包数
     * @param id 红包id
     * @param version 版本号
     * @return 更新记录条数
     */
    int decreaseRedPacket(@Param("id") Long id, @Param("version") Integer version);
}
