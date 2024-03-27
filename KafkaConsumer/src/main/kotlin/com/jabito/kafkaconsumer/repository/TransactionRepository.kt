package com.jabito.kafkaconsumer.repository

import com.jabito.kafkaconsumer.dao.Transaction
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionRepository : CrudRepository<Transaction, String>