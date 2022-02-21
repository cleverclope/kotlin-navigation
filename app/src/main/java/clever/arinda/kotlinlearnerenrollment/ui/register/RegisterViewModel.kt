package clever.arinda.kotlinlearnerenrollment.ui.register

import android.app.Application
import android.provider.SyncStateContract.Helpers.insert
import android.util.Log
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.room.ColumnInfo
import clever.arinda.kotlinlearnerenrollment.data.RegisterRepository
import clever.arinda.kotlinlearnerenrollment.database.entities.RegistrationEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class RegisterViewModel(private val repository: RegisterRepository, application: Application) :
    AndroidViewModel(application), Observable {

    init {
        Log.i("MYTAG", "init")
    }

    private var userdata: String? = null

    var userDetailsLiveData = MutableLiveData<Array<RegistrationEntity>>()

    @Bindable
    val inputEmail = MutableLiveData<String>()

    @Bindable
    val inputUsername = MutableLiveData<String>()

    @Bindable
    val inputFullname = MutableLiveData<String>()

    @Bindable
    val inputAddress = MutableLiveData<String>()

    @Bindable
    val inputPassword = MutableLiveData<String>()

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)


    private val _navigateto = MutableLiveData<Boolean>()

    val navigateto: LiveData<Boolean>
        get() = _navigateto

    private val _errorToast = MutableLiveData<Boolean>()

    val errotoast: LiveData<Boolean>
        get() = _errorToast

    private val _errorToastUsername = MutableLiveData<Boolean>()

    val errotoastUsername: LiveData<Boolean>
        get() = _errorToastUsername


    fun sumbitButton() {
        Log.i("MYTAG", "Inside SUBMIT BUTTON")
        if (inputEmail.value == null || inputUsername.value == null || inputFullname.value == null || inputAddress.value==null || inputPassword.value == null) {
            _errorToast.value = true
        } else {
            uiScope.launch {
//            withContext(Dispatchers.IO) {
                val userEmail = repository.getUserEmail(inputEmail.value!!)
                Log.i("MYTAG", userEmail.toString() + "------------------")
                if (userEmail != null) {
                    _errorToastUsername.value = true
                    Log.i("MYTAG", "Inside if Not null")
                } else {
                    Log.i("MYTAG", userDetailsLiveData.value.toString() + "ASDFASDFASDFASDF")
                    Log.i("MYTAG", "OK im in")
                    val email = inputEmail.value!!
                    val username = inputUsername.value!!
                    val fullname = inputFullname.value!!
                    val address = inputAddress.value!!
                    val password = inputPassword.value!!
                    Log.i("MYTAG", "insidi Sumbit")
                    insert(RegistrationEntity(0, email, username, fullname,address, password))
                    inputEmail.value = null
                    inputUsername.value = null
                    inputFullname.value = null
                    inputAddress.value = null
                    inputPassword.value = null
                    _navigateto.value = true
                }
            }
        }
        }

    override fun onCleared() {
        super.onCleared()
    }

    fun doneNavigating() {
        _navigateto.value = false
        Log.i("MYTAG", "Done navigating ")
    }

    fun donetoast() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting ")
    }

    fun donetoastUserName() {
        _errorToast.value = false
        Log.i("MYTAG", "Done taoasting  username")
    }

    private fun insert(user: RegistrationEntity): Job = viewModelScope.launch {
        repository.insert(user)
    }

//    fun clearALl():Job = viewModelScope.launch {
//        repository.deleteAll()
//    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback?) {

    }

}