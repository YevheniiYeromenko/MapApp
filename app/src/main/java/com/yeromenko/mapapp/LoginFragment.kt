package com.yeromenko.mapapp

import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.yeromenko.mapapp.rest.RetrofitModule
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

const val EMAIL_EXTRA = "EMAIL"

class LoginFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as MainActivity).supportActionBar?.title = resources.getString(R.string.authorization)

        bLogin.setOnClickListener {
            val email =  etEmail.text.toString();
            val password =  etPasswrod.text.toString();

            if (!checkEmail(email)){
                tilEmail.error = resources.getString(R.string.email_error)
                tilEmail.isErrorEnabled = true
            } else if (!checkPassword(password)){
                tilEmail.isErrorEnabled = false
                tilEmail.error = null
                tilPassword.error = resources.getString(R.string.password_error)
            } else {
                tilEmail.isErrorEnabled = false
                tilEmail.error = null
                tilPassword.isErrorEnabled = false
                tilPassword.error = null
                val bundle = Bundle()
                bundle.putString(EMAIL_EXTRA, email)
                Navigation.findNavController(it)
                    .navigate(R.id.action_loginFragment_to_mapsFragment, bundle)
            }
        }
    }

    private fun checkEmail(email: String): Boolean {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
    }
    private fun checkPassword(password: String): Boolean{
        return password.length >= 8
    }
}