package tech.alvarez.employeedirectory.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tech.alvarez.employeedirectory.EmployeesService
import tech.alvarez.employeedirectory.RetrofitHelper
import tech.alvarez.employeedirectory.model.Employee

class DirectoryViewModel : ViewModel() {
    private val _isRefreshing = MutableLiveData(false)
    val isRefreshing: LiveData<Boolean> = _isRefreshing

    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>> = _employees

    fun getEmployee(uuid: String): Employee? {
        return _employees.value?.first { it.uuid == uuid }
    }

    fun loadEmployees() {
        _isRefreshing.value = true
        val network = RetrofitHelper.retrofit.create(EmployeesService::class.java)
        viewModelScope.launch {
            val result = network.getEmployees()
            Log.d("daniel", result.message())
            Log.d("daniel", result.body().toString())
            for (a in result.body()?.employees!!) {
                Log.d("daniel", a.toString())
            }
            _isRefreshing.value = false
            _employees.postValue(result.body()?.employees!!)
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


fun getMockEmployees() = List(30) { i ->
//    Employee(
//        uuid = "Name$i",
//        photoUrlSmall = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg"
//    )
}


