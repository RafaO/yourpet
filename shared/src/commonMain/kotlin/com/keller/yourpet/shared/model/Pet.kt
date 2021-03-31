package com.keller.yourpet.shared.model

data class Pet(val name: String, val imageUrl: String) {
    override fun toString() = name
}
