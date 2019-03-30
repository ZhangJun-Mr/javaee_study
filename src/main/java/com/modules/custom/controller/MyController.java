package com.modules.custom.controller;

import com.modules.custom.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/aa")
public class MyController {
    @Autowired
    UserService userRedPacketService;

    @RequestMapping("/grapRedPacket")
    public Map<String, Object> invoke(Long redPacketId, Long userId) {
        Map<String, Object> retMap = new HashMap<>(16);
        retMap.put("success", 0);
        retMap.put("message", "抢红包成功" );
        return retMap;
    }
}
