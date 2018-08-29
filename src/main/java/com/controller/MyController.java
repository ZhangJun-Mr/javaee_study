package com.controller;

import com.dao.RedPacketMapper;
import com.pojo.RedPacket;
import com.pojo.UserRedPacket;
import com.service.RedPacketService;
import com.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/")
public class MyController {
    @Autowired
    UserRedPacketService userRedPacketService;

    @Autowired
    RedPacketMapper redPacketMapper;

    @RequestMapping("/grapRedPacket")
    public Map<String, Object> invoke(Long redPacketId, Long userId) {
        int result = userRedPacketService.grapRedPacket(redPacketId, userId);
        Map<String, Object> retMap = new HashMap<String, Object>(16);
        boolean flag = result > 0;
        retMap.put("success", flag);
        retMap.put("message", flag ? "抢红包成功" : "抢红包失败");
        return retMap;
    }

    @GetMapping("/getAll")
    public UserRedPacket getAll(Long redPacketId, Long userId) {
        return redPacketMapper.getAll(userId, redPacketId);
    }

    @GetMapping("/update")
    public int update(String note, double amount) {
        return redPacketMapper.update(note, amount);
    }
}
