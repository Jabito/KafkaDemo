package com.jabito.kafkaconsumer.service

import com.google.gson.Gson
import com.jabito.kafkaconsumer.dao.KafkaMessage
import mu.KotlinLogging
import org.apache.coyote.BadRequestException
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaService(val transactionService: TransactionService) {

    val logger = KotlinLogging.logger {  }

    @KafkaListener(topics = ["transactions"], groupId = "consumer-group-1")
    fun listenGroupFoo(message: String) {
        logger.info("Received message -> $message")
        var kafkaMessage: KafkaMessage
        try {
            kafkaMessage = Gson().fromJson(message, KafkaMessage::class.java)
        }catch (e: Exception){
            throw BadRequestException("Incorrect transaction message format.")
        }
        val transaction = kafkaMessage.message
        transactionService.saveTransaction(transaction)
        logger.info("Processed UID=${transaction.identifier}.")
    }
}