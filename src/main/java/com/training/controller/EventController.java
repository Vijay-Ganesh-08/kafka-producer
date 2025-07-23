package com.training.controller;

import com.training.model.Customer;
import com.training.service.MessagePublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer-app")
public class EventController {

    @Autowired
    private MessagePublisher messagePublisher;

    @GetMapping("/publish/{message}")
    public ResponseEntity<?> publishMessage(@PathVariable String message) {

        try {
            for (int i = 0; i < 10000; i++) {
                messagePublisher.sendMessageToTopic(message + " : " + i);
            }

            return ResponseEntity.ok("Message Published Successfully");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/publish")
    public ResponseEntity<?> publishEventMessage(@RequestBody Customer customer) {
        messagePublisher.sendEventsToTopic(customer);
        return ResponseEntity.ok("Message Published Successfully");
    }

}
