package com.project.latihan.model.entities

data class ErrorResponse(
    val name: String,
    val message: String,
    val code: Int,
    val className: String,
    val errors: Map<String, Any>?
)