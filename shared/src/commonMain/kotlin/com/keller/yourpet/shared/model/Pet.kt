package com.keller.yourpet.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Pet(val id: Long, val name: String, val imageUrl: String, val gender: Gender) {
    override fun toString() = name
}
