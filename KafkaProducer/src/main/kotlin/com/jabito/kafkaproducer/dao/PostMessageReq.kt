package com.jabito.kafkaproducer.dao

data class PostMessageReq(
    val topicName: String,
    val messageCount: Int,
    val uid: String
)