package com.stdev.shopit.presentation.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.stdev.shopit.R
import com.stdev.shopit.data.util.Utils
import com.stdev.shopit.data.util.Utils.validateLoginRequest
import com.stdev.shopit.databinding.FragmentSignUpBinding
import com.stdev.shopit.presentation.viewmodel.RegisterViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    @Inject
    lateinit var viewModel : RegisterViewModel

    private lateinit var binding : FragmentSignUpBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSignUpBinding.bind(view)

        binding.registerButton.setOnClickListener {

            val username = binding.registerUsername.editableText.toString()
            val password = binding.registerPassword.editableText.toString()

            val result = validateLoginRequest(username, password)

            if (result.successful){
                binding.registerProgress.visibility = View.VISIBLE
                binding.registerButton.isEnabled = false

                viewModel.registerUser(username, password)

                viewModel.successful.observe(viewLifecycleOwner){successful->
                    if (successful == true){
                        binding.registerProgress.visibility = View.INVISIBLE
                        binding.registerButton.isEnabled = true
                        findNavController().navigate(R.id.action_signUpFragment_to_homeFragment)
                        viewModel.navigated()
                    }else if (successful == false){
                        binding.registerProgress.visibility = View.INVISIBLE
                        binding.registerButton.isEnabled = true
                        Snackbar.make(binding.registerButton,"${viewModel.error.value}",Snackbar.LENGTH_SHORT).show()
                        viewModel.navigated()
                    }
                }

            }else{
                Snackbar.make(binding.registerButton,"${result.error}",Snackbar.LENGTH_SHORT).show()
            }
        }

        binding.registerSignin.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
        }

    }

}