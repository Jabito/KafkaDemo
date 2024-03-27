package com.jabito.kafkaproducer.dao

data class KafkaMessage(
    val op: String,
    val message: Any,
    val tz: String
)