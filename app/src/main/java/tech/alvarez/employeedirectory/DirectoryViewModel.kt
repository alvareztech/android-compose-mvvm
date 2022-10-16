package tech.alvarez.employeedirectory

import android.util.Log
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import tech.alvarez.employeedirectory.model.Employee

class DirectoryViewModel : ViewModel() {
    private val _employees = MutableLiveData<List<Employee>>()
    val employees: LiveData<List<Employee>>
        get() = _employees

    fun getEmployee(uuid: String) : Employee? {
        return _employees.value?.first { it.uuid == uuid }
    }

    fun loadEmployees() {
        val network = RetrofitHelper.retrofit.create(EmployeesApi::class.java)
        viewModelScope.launch {
            val result = network.getEmployees()
            Log.d("daniel", result.message())
            Log.d("daniel", result.body().toString())
            for (a in result.body()?.employees!!) {
                Log.d("daniel", a.toString())
            }
            _employees.postValue(result.body()?.employees!!)
        }
    }
}


fun getMockEmployees() = List(30) { i ->
//    Employee(
//        uuid = "Name$i",
//        photoUrlSmall = "https://s3.amazonaws.com/sq-mobile-interview/photos/16c00560-6dd3-4af4-97a6-d4754e7f2394/small.jpg"
//    )
}


