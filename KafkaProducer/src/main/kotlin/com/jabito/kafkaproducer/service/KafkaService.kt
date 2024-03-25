package com.jabito.kafkaproducer.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service

@Service
class KafkaService(@Autowired val kafkaTemplate: KafkaTemplate<String, String>) {

    fun sendMessage(topic: String, msg: String){
        kafkaTemplate.send(topic, msg)
    }
}