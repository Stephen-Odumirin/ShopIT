package com.stdev.shopit.presentation.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.stdev.shopit.R
import com.stdev.shopit.data.model.ValidationResult
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.data.util.Utils.validateLoginRequest
import com.stdev.shopit.databinding.FragmentLoginBinding
import com.stdev.shopit.domain.usecase.AuthUseCase
import com.stdev.shopit.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    @Inject
    lateinit var viewModel: LoginViewModel
    private lateinit var binding : FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentLoginBinding.bind(view)

        binding.loginUsername.setText("johnd")
        binding.loginPassword.setText("m38rmF$")

        binding.loginButton.setOnClickListener {

            val username = binding.loginUsername.editableText.toString()
            val password = binding.loginPassword.editableText.toString()

            val result = validateLoginRequest(username, password)

            if (result.successful){
                binding.loginProgress.visibility = View.VISIBLE
                binding.loginButton.isEnabled = false

                viewModel.loginUser(username, password)

                viewModel.successful.observe(viewLifecycleOwner){successful ->
                    if (successful == true){
                        binding.loginProgress.visibility = View.INVISIBLE
                        binding.loginButton.isEnabled = true
                        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
                        viewModel.navigated()
                    }else if(successful == false){
                        binding.loginProgress.visibility = View.INVISIBLE
                        binding.loginButton.isEnabled = true
                        Snackbar.make(binding.loginButton,"${viewModel.error.value}",Snackbar.LENGTH_SHORT).show()
                        viewModel.navigated()
                    }
                }
            }
            else{
                Snackbar.make(binding.loginButton,"${result.error}",Snackbar.LENGTH_SHORT).show()
            }

        }


        binding.loginSignup.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }

    }



}