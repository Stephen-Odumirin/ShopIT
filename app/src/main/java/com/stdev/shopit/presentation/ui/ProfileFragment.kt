package com.stdev.shopit.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.stdev.shopit.R
import com.stdev.shopit.data.model.User
import com.stdev.shopit.data.util.Resource
import com.stdev.shopit.databinding.FragmentProfileBinding
import com.stdev.shopit.presentation.viewmodel.ProfileViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    @Inject
    lateinit var viewModel : ProfileViewModel

    private lateinit var binding : FragmentProfileBinding

    private var user : User? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        viewModel.getUser(1)

        viewModel.user.observe(viewLifecycleOwner){result ->
            when(result){
                is Resource.Loading -> {
                    Log.i("ProfileFragment","Loading...")
                }
                is Resource.Success ->{
                    user = result.data
                    binding.profileName.text = "${result.data?.name?.firstname} ${result.data?.name?.lastname}"
                    binding.profileEmail.text = "${result.data?.email}"
                }
                is Resource.Error -> {
                    Log.i("ProfileFragment","Error ${result.message}")
                }
            }
        }

        binding.profileBack.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.profileEdit.setOnClickListener {
            val action = user?.let { user1 -> ProfileFragmentDirections.actionProfileFragmentToEditProfileFragment(user1) }
            if (action != null) findNavController().navigate(action)
        }

        binding.profileNotifications.setOnClickListener {
            Snackbar.make(binding.profileNotifications,"Coming soon...",Snackbar.LENGTH_SHORT).show()
        }

        binding.profileWishlist.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_wishlistFragment)
        }

        binding.profileTerms.setOnClickListener {
            Snackbar.make(binding.profileNotifications,"Coming soon...",Snackbar.LENGTH_SHORT).show()
        }

        binding.profilePrivacy.setOnClickListener {
            Snackbar.make(binding.profileNotifications,"Coming soon...",Snackbar.LENGTH_SHORT).show()
        }

        binding.profileReportBug.setOnClickListener {
            Snackbar.make(binding.profileNotifications,"Coming soon...",Snackbar.LENGTH_SHORT).show()
        }

        binding.profileLogout.setOnClickListener {
            viewModel.logoutUser()
            findNavController().navigate(R.id.action_profileFragment_to_splashFragment)
        }

    }

}