package tech.alvarez.employeedirectory.model

import com.google.gson.annotations.SerializedName

data class Employee(
    var uuid: String,
    @SerializedName("full_name")
    var fullName: String,
    @SerializedName("phone_number")
    var phoneNumber: String,
    @SerializedName("email_address")
    var emailAddress: String,
    var biography: String,
    @SerializedName("photo_url_small")
    var photoUrlSmall: String,
    @SerializedName("photo_url_large")
    var photoUrlLarge: String,
    var team: String,
    @SerializedName("employee_type")
    var employeeType: String,
)

data class EmployeeList(
    val employees: List<Employee>
)