package com.keller.yourpet.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Pet(
    val id: String,
    val name: String,
    val imageUrl: String,
    val gender: Gender,
    val description: String
) {
    override fun toString() = name
}
