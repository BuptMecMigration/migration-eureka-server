package com.msgqueue;

//import com.alibaba.fastjson.JSON;
//import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
//import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
//import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
//import org.apache.rocketmq.client.exception.MQClientException;
//import org.apache.rocketmq.common.message.Message;
//import org.apache.rocketmq.common.message.MessageExt;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//import java.util.List;
//import java.util.Map;

//@Component
public class MqConsumer {

//    private DefaultMQPushConsumer consumer;
//
//    //读取全局配置变量
//    @Value("${mq.nameserver.addr}")
//    private String nameAddr;
//
//    @Value("${mq.topicname}")
//    private String topicName;
//
//    @Autowired
//    private LogDOMapper logDOMapper;
//
//    @PostConstruct
//    public void init() throws MQClientException {
//        consumer = new DefaultMQPushConsumer("stock_consumer_group");
//        consumer.setNamesrvAddr(nameAddr);
//        consumer.subscribe(topicName, "*");
//
//        consumer.registerMessageListener(new MessageListenerConcurrently() {
//            @Override
//            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
//                Message message = list.get(0);
//                String jsonString  = new String(message.getBody());
//                Map<String, Object> map = JSON.parseObject(jsonString, Map.class);
//                Integer logId = (Integer) map.get("logId");
//                Integer amount = (Integer) map.get("amount");
//                logDOMapper.decreaseStock(logId, amount);
//                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
//            }
//        });
//        consumer.start();
//    }
}

