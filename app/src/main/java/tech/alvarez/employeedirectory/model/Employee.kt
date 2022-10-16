package tech.alvarez.employeedirectory.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Employee(
    @Json(name = "uuid") var uuid: String,
    @Json(name = "full_name") var fullName: String,
    @Json(name = "phone_number") var phoneNumber: String?,
    @Json(name = "email_address") var emailAddress: String,
    @Json(name = "biography") var biography: String?,
    @Json(name = "photo_url_small") var photoUrlSmall: String?,
    @Json(name = "photo_url_large") var photoUrlLarge: String?,
    @Json(name = "team") var team: String,
    @Json(name = "employee_type") var employeeType: EmployeeType,
)

enum class EmployeeType(val title: String) {
    FULL_TIME("Full Time"),
    PART_TIME("Part Time"),
    CONTRACTOR("Contractor")
}

data class EmployeeList(
    val employees: List<Employee>
)