package com.test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class MyTest {

    RestTemplate restTemplate = new RestTemplate();

    @Test
    public void test() {
        Map<String, String> request = new HashMap<String, String>(16);
        Map<String, Integer> params = new HashMap<String, Integer>(16);
        params.put("redPacketId", 1);
        System.out.println("Start Time: "+ System.currentTimeMillis());
        for (int i=0; i<30000; i++){
            params.put("userId", i);
            restTemplate.postForEntity("http://localhost:8080/grapRedPacket?redPacketId={redPacketId}&userId={userId}", request, Map.class, params);
        }
        System.out.println("Start Time: "+ System.currentTimeMillis());
    }
}
