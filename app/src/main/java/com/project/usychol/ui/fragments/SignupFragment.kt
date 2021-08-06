package com.project.usychol.ui.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.edit
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.google.gson.Gson
import com.project.usychol.R
import com.project.usychol.databinding.FragmentSignupBinding
import com.project.usychol.domain.entities.Psychologist
import com.project.usychol.viewMolder.SignupViewModel

class SignupFragment : Fragment() {

    private lateinit var viewModel: SignupViewModel
    private lateinit var binding: FragmentSignupBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignupBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(SignupViewModel::class.java)

        val view: View = binding.root

        val sharedPreferences = requireActivity().getSharedPreferences("User", Context.MODE_PRIVATE)
        var gson = Gson()

        binding.btnSignup.setOnClickListener {
            val psychologist: Psychologist = getDataFromPsychologist()

            val jsonUser = gson.toJson(psychologist)

            sharedPreferences.edit {
                putInt("id", psychologist.id!!)
                putString("user", jsonUser)
            }

            viewModel.registerPsychologist(psychologist)

            Navigation.findNavController(view).navigate(R.id.signupToApproval)
        }

        binding.tvSiginupAlreadyAccount.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.signupToSignin)
        }

        return view
    }

    private fun getDataFromPsychologist(): Psychologist {

        val psychologistName = binding.inputName.findViewById<EditText>(R.id.textInput).text
        val psychologistBirthday = binding.inputBirthday.findViewById<EditText>(R.id.textInput).text
        val psychologistCrp = binding.inputIdNumber.findViewById<EditText>(R.id.textInput).text
        val psychologistCpf = binding.inputDocument.findViewById<EditText>(R.id.textInput).text
        val psychologistEmail = binding.inputEmail.findViewById<EditText>(R.id.textInput).text
        val psychologistPassword = binding.inputPassword.findViewById<EditText>(R.id.textInput).text

        return Psychologist(
            null,
            null,
            psychologistName.toString(),
            psychologistBirthday.toString(),
            psychologistCrp.toString().toInt(),
            psychologistCpf.toString(),
            psychologistEmail.toString(),
            psychologistPassword.toString(),
            null
        )
    }

}