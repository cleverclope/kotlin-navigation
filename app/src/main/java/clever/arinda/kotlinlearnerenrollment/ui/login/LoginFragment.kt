package clever.arinda.kotlinlearnerenrollment.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import clever.arinda.kotlinlearnerenrollment.R
import clever.arinda.kotlinlearnerenrollment.data.RegisterRepository
import clever.arinda.kotlinlearnerenrollment.database.KotlinLearnerEnrollDatabase
import clever.arinda.kotlinlearnerenrollment.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {
    private lateinit var loginViewModel: LoginViewModel
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        login()
        register()

        val application = requireNotNull(this.activity).application

        val dao = KotlinLearnerEnrollDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = LoginViewModelFactory(repository, application)

        loginViewModel = ViewModelProvider(this, factory).get(LoginViewModel::class.java)

        binding.myLoginViewModel = loginViewModel

        binding.lifecycleOwner = this

        loginViewModel.navigatetoRegister.observe(this, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                displayUsersList()
                loginViewModel.doneNavigatingRegiter()
            }
        })

        loginViewModel.errotoast.observe(this, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                loginViewModel.donetoast()
            }
        })

        loginViewModel.errotoastUsername .observe(this, { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "User doesnt exist,please Register!", Toast.LENGTH_SHORT).show()
                loginViewModel.donetoastErrorUsername()
            }
        })

        loginViewModel.errorToastInvalidPassword.observe(this, { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please check your Password", Toast.LENGTH_SHORT).show()
                loginViewModel.donetoastInvalidPassword()
            }
        })

        loginViewModel.navigatetoUserDetails.observe(this, { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                navigateUserDetails()
                loginViewModel.doneNavigatingUserDetails()
            }
        })

        return binding.root
    }

   private fun login() {
//       loginViewModel.loginButton()
       val loginButton = binding.loginNow
       loginButton.setOnClickListener {
           loginViewModel.loginButton()
           Toast.makeText(context,"Logged IN NOW",Toast. LENGTH_SHORT).show();
//           this.findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
//OR
//            val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
//            NavHostFragment.findNavController(this).navigate(action)
//OR
//            view?.findNavController()?.navigate(R.id.homeFragment)
       }
    }

   private fun register() {
//       loginViewModel.signUP()
       val registerButton = binding.btnRegister
       registerButton.setOnClickListener {
           this.findNavController()
               .navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())

       }
   }

    private fun displayUsersList() {
        Log.i("MYTAG","insidisplayUsersList")
        val action = LoginFragmentDirections.actionLoginFragmentToHomeFragment()
        NavHostFragment.findNavController(this).navigate(action)

    }

    private fun navigateUserDetails() {
        Log.i("MYTAG","insidisplayUsersList")
//        val action = LoginFragmentDirections.actionLoginFragmentToUserDetailsFragment()
//        NavHostFragment.findNavController(this).navigate(action)
    }

}