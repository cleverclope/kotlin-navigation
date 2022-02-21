package clever.arinda.kotlinlearnerenrollment.ui.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import clever.arinda.kotlinlearnerenrollment.data.RegisterRepository

class LoginViewModelFactory (
    private  val repository: RegisterRepository,
    private val application: Application
): ViewModelProvider.Factory{

    @Suppress("Unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(repository, application) as T
        }
        throw IllegalArgumentException("Unknown View Model Class")
    }
}