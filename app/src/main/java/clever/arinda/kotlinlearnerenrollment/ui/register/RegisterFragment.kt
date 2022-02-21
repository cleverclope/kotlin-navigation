package clever.arinda.kotlinlearnerenrollment.ui.register

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import clever.arinda.kotlinlearnerenrollment.R
import clever.arinda.kotlinlearnerenrollment.data.RegisterRepository
import clever.arinda.kotlinlearnerenrollment.database.KotlinLearnerEnrollDatabase
import clever.arinda.kotlinlearnerenrollment.databinding.FragmentLoginBinding
import clever.arinda.kotlinlearnerenrollment.databinding.FragmentRegisterBinding
import clever.arinda.kotlinlearnerenrollment.ui.login.LoginViewModel


class RegisterFragment : Fragment() {

    private lateinit var registerViewModel: RegisterViewModel
    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

//        val binding: FragmentRegisterBinding = DataBindingUtil.inflate(
//            inflater,
//            R.layout.fragment_register, container, false


        register()

        val application = requireNotNull(this.activity).application

        val dao = KotlinLearnerEnrollDatabase.getInstance(application).registerDatabaseDao

        val repository = RegisterRepository(dao)

        val factory = RegisterViewModelFactory(repository, application)

        registerViewModel = ViewModelProvider(this, factory).get(RegisterViewModel::class.java)

        binding.myRegisterViewModel = registerViewModel

        binding.lifecycleOwner = this

        registerViewModel.navigateto.observe(this, Observer { hasFinished->
            if (hasFinished == true){
                Log.i("MYTAG","insidi observe")
                displayUsersList()
                registerViewModel.doneNavigating()
            }
        })

        registerViewModel.userDetailsLiveData.observe(this, Observer {
            Log.i("MYTAG",it.toString()+"000000000000000000000000")
        })


        registerViewModel.errotoast.observe(this, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "Please fill all fields", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoast()
            }
        })

        registerViewModel.errotoastUsername.observe(this, Observer { hasError->
            if(hasError==true){
                Toast.makeText(requireContext(), "UserName Already taken", Toast.LENGTH_SHORT).show()
                registerViewModel.donetoastUserName()
            }
        })
        return binding.root
    }

    private fun register() {
        val registerButton = binding.btnRegisterNow
        registerButton.setOnClickListener {
            Toast.makeText(context,"Registering ....",Toast.LENGTH_LONG).show()
            registerViewModel.sumbitButton()
        }
    }

    private fun displayUsersList() {
        Log.i("MYTAG","insidisplayUsersList")
        val action = RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
        NavHostFragment.findNavController(this).navigate(action)
    }

}