package tech.alvarez.employeedirectory.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tech.alvarez.employeedirectory.model.Employee
import tech.alvarez.employeedirectory.network.EmployeesApi
import tech.alvarez.employeedirectory.network.RetrofitHelper

class EmployeesViewModel : ViewModel() {
    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean>
        get() = _isRefreshing

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>>
        get() = _employees

    fun getEmployee(uuid: String): Employee? {
        return _employees.value?.first { it.uuid == uuid }
    }

    fun loadEmployees() {
        _isRefreshing.value = true
        val network = RetrofitHelper.retrofit.create(EmployeesApi::class.java)
        viewModelScope.launch {
            delay(3000)
            try {
                val response = network.getEmployees()
                if (response.isSuccessful) {
                    _employees.postValue(response.body()?.employees ?: emptyList())
                } else {
                    _employees.postValue(emptyList())
                }
            } catch (e: JsonDataException) {
                _employees.postValue(emptyList())
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}
