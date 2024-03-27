package com.jabito.kafkaproducer.controller

import com.jabito.kafkaproducer.dao.PostMessageReq
import com.jabito.kafkaproducer.service.KafkaService
import org.springframework.web.bind.annotation.*

@RestController
class MainController(val kafkaService: KafkaService){

    @PostMapping("/post")
    fun post(@RequestBody req: PostMessageReq): String {
        kafkaService.sendMessage(req)

        return "${req.uid}: ${req.messageCount} message/s posted."
    }
}