package com.jabito.kafkaconsumer.service

import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaService() {
    @KafkaListener(topics = ["my-topic"], groupId = "foo")
    fun listenGroupFoo(message: String) {
        println("Received Message: $message")
    }
}