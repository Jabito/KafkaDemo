package com.jabito.kafkaproducer.dao

data class Transaction(
    val id: String,
    val item: String,
    val price: String,
    val createdAt: String,
    val uid: String
)
