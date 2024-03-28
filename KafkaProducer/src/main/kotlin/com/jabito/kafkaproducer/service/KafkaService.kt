package com.jabito.kafkaproducer.service

import com.google.gson.Gson
import com.jabito.kafkaproducer.constant.OpType
import com.jabito.kafkaproducer.dao.KafkaMessage
import com.jabito.kafkaproducer.dao.PostMessageReq
import com.jabito.kafkaproducer.dao.Transaction
import mu.KotlinLogging
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*

@Service
class KafkaService(@Autowired val kafkaTemplate: KafkaTemplate<String, String>) {

    val logger = KotlinLogging.logger {  }

    fun sendMessage(req: PostMessageReq){
        val start = System.currentTimeMillis()
        for(i in 1..req.messageCount){

            val message = Transaction(UUID.randomUUID().toString(),
                UUID.randomUUID().toString(),
                getRandomPrice().toString(),
                LocalDateTime.now().minusMinutes(5).toString(),
                req.uid + "-$i")
            val gson = Gson()
            kafkaTemplate.send(req.topicName, gson.toJson(KafkaMessage(OpType.INSERT, message, LocalDateTime.now().toString())))
        }
        logger.info("${req.uid}: Sent ${req.messageCount} messages in ${System.currentTimeMillis() - start}ms")
    }

    fun getRandomPrice(): Double{
        val r = Random()
        return 10000 * r.nextDouble()
    }
}