package com.modules.custom.controller;

import com.modules.custom.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
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

    @RequestMapping("/grapRedPacket")
    public Map<String, Object> invoke(Long redPacketId, Long userId) {
        int result = userRedPacketService.grapRedPacket(redPacketId, userId);
        Map<String, Object> retMap = new HashMap<>(16);
        boolean flag = result > 0;
        retMap.put("success", flag);
        retMap.put("message", flag ? "抢红包成功" : "抢红包失败");
        return retMap;
    }
}
