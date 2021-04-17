package com.keller.yourpet.shared.model

import kotlinx.serialization.Serializable

@Serializable
data class Pet(val name: String, val imageUrl: String) {
    override fun toString() = name
}
