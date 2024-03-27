package com.jabito.kafkaconsumer.service

import com.jabito.kafkaconsumer.dao.Transaction
import com.jabito.kafkaconsumer.repository.TransactionRepository
import mu.KotlinLogging
import org.springframework.stereotype.Service

@Service
class TransactionService(val transactionRepository: TransactionRepository) {

    val logger = KotlinLogging.logger {  }

    fun saveTransaction(transaction: Transaction){
        logger.info("Saving to DB -> UID:${transaction.identifier}")
        transactionRepository.save(transaction)
    }

}