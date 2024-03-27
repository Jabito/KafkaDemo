package com.jabito.kafkaconsumer.service

import com.google.gson.Gson
import com.jabito.kafkaconsumer.dao.KafkaMessage
import com.jabito.kafkaconsumer.dao.Transaction
import mu.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class KafkaService(val transactionService: TransactionService) {

    val logger = KotlinLogging.logger {  }

    @KafkaListener(topics = ["transactions"], groupId = "foo")
    fun listenGroupFoo(message: String) {
        logger.info("Received message -> $message")
        val kafkaMessage = Gson().fromJson(message, KafkaMessage::class.java)
        val transaction = kafkaMessage.message
        transactionService.saveTransaction(transaction)
        logger.info("Processed UID=${transaction.identifier}.")
    }
}