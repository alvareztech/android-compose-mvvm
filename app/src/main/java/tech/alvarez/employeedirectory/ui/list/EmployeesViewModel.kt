package tech.alvarez.employeedirectory.ui.list

import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
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
            val response = repository.fetchEmployees()
            if (response.isSuccessful) {
                _isRefreshing.value = false
                _employees.postValue(response.body()?.employees!!)
            }
        }
    }

    fun refresh() {
        _isRefreshing.value = true
        GlobalScope.launch(context = Dispatchers.Main) {
            delay(3000)
            _isRefreshing.value = false
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

