package com.jabito.kafkaconsumer.dao

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.io.Serializable


@Entity
@Table(name="transactions")
data class Transaction(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: String = "",
    val item: String = "",
    val price: String = "",
    val createdAt: String = "",
    val identifier: String = ""
)