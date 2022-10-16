package tech.alvarez.employeedirectory.ui

import androidx.lifecycle.*
import com.squareup.moshi.JsonDataException
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tech.alvarez.employeedirectory.data.employees.EmployeesRepository
import tech.alvarez.employeedirectory.model.Employee

class EmployeesViewModel(private val repository: EmployeesRepository) : ViewModel() {
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

        viewModelScope.launch {
            delay(3000)
            try {
                val response = repository.fetchEmployees()
                if (response.isSuccessful) {
                    _employees.postValue(response.body()?.employees!!)
                }
            } catch (e: JsonDataException) {
                _employees.postValue(emptyList())
            } finally {
                _isRefreshing.value = false
            }
        }
    }
}

class EmployeesViewModelFactory(
    private val employeesRepository: EmployeesRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(EmployeesViewModel::class.java)) {
            return EmployeesViewModel(employeesRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

