package tech.alvarez.employeedirectory.model

import com.squareup.moshi.Json

data class Employee(
    @field:Json(name = "uuid") var uuid: String,
    @field:Json(name = "full_name") var fullName: String,
    @field:Json(name = "phone_number") var phoneNumber: String?,
    @field:Json(name = "email_address") var emailAddress: String,
    @field:Json(name = "biography") var biography: String?,
    @field:Json(name = "photo_url_small") var photoUrlSmall: String?,
    @field:Json(name = "photo_url_large") var photoUrlLarge: String?,
    @field:Json(name = "team") var team: String,
    @field:Json(name = "employee_type") var employeeType: EmployeeType,
)

enum class EmployeeType(val title: String) {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    CONTRACTOR("Contractor")
}

data class EmployeeList(
    val employees: List<Employee>
)