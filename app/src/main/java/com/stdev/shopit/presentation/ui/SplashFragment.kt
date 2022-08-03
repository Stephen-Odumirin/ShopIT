package com.stdev.shopit.presentation.ui

import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.HandlerCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.stdev.shopit.R
import com.stdev.shopit.presentation.viewmodel.SplashViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment : Fragment() {

    @Inject
    lateinit var viewModel : SplashViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Handler().postDelayed({
            //check if the user is logged in
            if (viewModel.loggedIn){
                //navigate to the home fragment
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }else{
                //navigate to the login fragment
                findNavController().navigate(R.id.action_splashFragment_to_loginFragment)
            }
        },2000L)
    }

}