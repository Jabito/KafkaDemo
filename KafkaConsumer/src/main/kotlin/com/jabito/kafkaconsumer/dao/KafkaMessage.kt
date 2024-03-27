package com.jabito.kafkaconsumer.dao


data class KafkaMessage(
    val op: String,
    val message: Transaction,
    val tz: String
)