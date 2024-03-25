package com.jabito.kafkaproducer.controller

import com.jabito.kafkaproducer.constant.Topics
import com.jabito.kafkaproducer.service.KafkaService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class MainController(val kafkaService: KafkaService){

    @PostMapping("/post")
    fun post(): String {
        val topic = Topics.MAIN_TOPIC
        kafkaService.sendMessage(topic, UUID.randomUUID().toString())

        return "Message posted."
    }
}