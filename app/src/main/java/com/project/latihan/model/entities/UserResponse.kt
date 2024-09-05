package com.project.latihan.model.entities

data class UserResponse(
    val accessToken: String,
    val user: User
)

data class User(
    val id: String,
    val fullname: String,
    val email: String,
    val photo: String?,
    val role_id: String,
    val isVerified: Boolean,
    val company: Company?,
    val nik: String?,
    val upliner_id: String?,
    val upliner2_id: String?,
    val upliner3_id: String?,
    val division_name: String?,
    val department_name: String?,
    val unit_name: String?,
    val employee_type_name: String?,
    val position_name: String?,
    val googleId: String?,
    val employee_type_id: String?,
    val upliner_bd: String?,
    val resetAttempts: String?,
    val verifyToken: String?,
    val verifyShortToken: String?,
    val is_enable_tracking: Boolean?,
    val is_user_project: Boolean?,
    val verifyExpires: String?,
    val resetToken: String?,
    val resetShortToken: String?,
    val resetExpires: String?,
    val location_point_id: String?,
    val shifting_id: String?,
    val job_level_id: String?,
    val company_id: String?,
    val division_id: String?,
    val leave_amount: String?,
    val is_mobile: Boolean?,
    val is_overtime: Boolean?,
    val is_active: Boolean,
    val shift_type: String?,
    val required_selfie: Boolean?,
    val registered_from: String?,
    val hris_branch: String?,
    val hris_join_date: String?,
    val created_at: String?,
    val updated_at: String?,
    val hierarchy_level: Int?,
    val job_position_id: String?,
    val project_id: String?,
    val job_level: JobLevel,
    val job_departement_id: String?,
)

data class Company(
    val id: String,
    val name: String,
    val industry: String,
    val company_code: String
)

data class JobLevel(
    val id: String,
    val level: Int,
    val name: String
)
