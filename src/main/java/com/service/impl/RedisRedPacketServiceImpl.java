package com.service.impl;

import com.pojo.UserRedPacket;
import com.service.RedisRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.BoundListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class RedisRedPacketServiceImpl implements RedisRedPacketService {
    private static final String PREFIX = "red_packet_list_";
    /**
     * 每次取出 1000 条，避免一次取出消耗太多内存
     */
    private static final int TIME_SIZE = 1000;

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    DataSource dataSource;

    @Override
    @Async
    public void saveUserRedPacketByRedis(Long redPacketId, Double unitAmount) {
        System.out.println("开始保存数据");
        Long start = System.currentTimeMillis();
        BoundListOperations operations = redisTemplate.boundListOps(PREFIX + redPacketId);
        Long size = operations.size();
        Long times = size % TIME_SIZE == 0 ? size / TIME_SIZE : size / TIME_SIZE + 1;
        int count = 0;
        List<UserRedPacket> userRedPackets = new ArrayList<UserRedPacket>(TIME_SIZE);
        for (int i = 0; i < times; i++) {
            List userIdList = null;
            if (i == 0) {
                userIdList = operations.range(i * TIME_SIZE, (i + 1) * TIME_SIZE);
            } else {
                userIdList = operations.range(i * TIME_SIZE + 1, (i + 1) * TIME_SIZE);
            }
            userRedPackets.clear();
            for (int j = 0; j < userIdList.size(); j++) {
                String args = userIdList.get(j).toString();
                String[] arr = args.split("-");
                String userIdStr = arr[0];
                String timeStr = arr[1];
                Long userId = Long.parseLong(userIdStr);
                Long time = Long.parseLong(timeStr);
                //生成抢红包信息
                UserRedPacket userRedPacket = new UserRedPacket();
                userRedPacket.setRedPacketId(redPacketId);
                userRedPacket.setUserId(userId);
                userRedPacket.setAmount(unitAmount);
                userRedPacket.setGrabTime(new Timestamp(time));
                userRedPacket.setNote(" 抢红包" + redPacketId);
                userRedPackets.add(userRedPacket);
            }
            count += executeBatch(userRedPackets);
        }
        redisTemplate.delete(PREFIX + redPacketId);
        Long end = System.currentTimeMillis();
        System.err.println("保存数据结束， 耗时" + (end - start) + "毫秒，工" + count + "条记录被保存。");
    }

    private int executeBatch(List<UserRedPacket> userRedPackets) {
        Connection connection = null;
        Statement statement = null;
        int[] count = null;
        try {
            connection = dataSource.getConnection();
            connection.setAutoCommit(false);
            statement = connection.createStatement();
            for (UserRedPacket userRedPacket : userRedPackets) {
                String sql1 = "update t_red_packet set stock = stock - 1 where id = " + userRedPacket.getRedPacketId();
                DateFormat dateFormat = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
                String sql2 = "insert into t_user_red_packet(red_packet_id, user_id, amount, grab_time, note) values " +
                        "('" + userRedPacket.getRedPacketId() + "', " + userRedPacket.getUserId() + "," +
                        "" + userRedPacket.getAmount() + ",'" + dateFormat.format(userRedPacket.getGrabTime()) + "')";
                statement.addBatch(sql1);
                statement.addBatch(sql2);
            }
            count = statement.executeBatch();
            connection.commit();


        } catch (SQLException e) {
            throw new RuntimeException("抢红包批量执行程序错误");
        } finally {
            try {
                if (connection != null && !connection.isClosed()) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return count.length / 2;
        }
    }
}
