package com.training.service;

import com.training.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class MessagePublisher {

    @Autowired
    private KafkaTemplate<String, Object> template;

    public void sendMessageToTopic(String message) {
        CompletableFuture<SendResult<String, Object>> send = template.send("kafkatraining-topic4", message);
        send.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent Message = [" + message + "]" +
                        " with Offset : " + result.getRecordMetadata().offset() +
                        " in Partition : " + result.getRecordMetadata().partition());
            } else {
                System.out.println("Unable to send Message = [" + message + "]" +
                        " due to : " + ex.getMessage());
            }
        });

    }


    public void sendEventsToTopic(Customer customer) {
        try {
            CompletableFuture<SendResult<String, Object>> send = template.send("kafkatraining-topic5", customer);
            send.whenComplete((result, ex) -> {
                if (ex == null) {
                    System.out.println("Sent Message = [" + customer + "]" +
                            " with Offset : " + result.getRecordMetadata().offset() +
                            " in Partition : " + result.getRecordMetadata().partition());
                } else {
                    System.out.println("Unable to send Message = [" + customer + "]" +
                            " due to : " + ex.getMessage());
                }
            });
        } catch (Exception ex) {
            System.out.println("Error : " + ex.getMessage());
        }

    }
}
