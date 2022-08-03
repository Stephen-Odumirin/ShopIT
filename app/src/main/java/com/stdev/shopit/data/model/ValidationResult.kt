package com.stdev.shopit.data.model

data class ValidationResult(
    val successful: Boolean,
    val error: String? = null
)
