package com.mindyapps.fairytales.feature.profile.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.credentials.CredentialManager
import androidx.credentials.CustomCredential
import androidx.credentials.GetCredentialRequest
import androidx.credentials.GetCredentialResponse
import androidx.credentials.PasswordCredential
import androidx.credentials.PublicKeyCredential
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.google.android.libraries.identity.googleid.GetGoogleIdOption
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential
import com.google.android.libraries.identity.googleid.GoogleIdTokenParsingException
import com.mindyapps.fairytales.R
import com.mindyapps.fairytales.base.presentation.BaseFragment
import com.mindyapps.fairytales.core.observe
import com.mindyapps.fairytales.databinding.FragmentProfileBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception


class ProfileFragment : BaseFragment(R.layout.fragment_profile) {

    private val view: FragmentProfileBinding by viewBinding()

    private val viewModel: ProfileViewModel by viewModels()

    private var credentialManager: CredentialManager? = null

    override fun onViewCreated(v: View, savedInstanceState: Bundle?) {
        super.onViewCreated(v, savedInstanceState)
        observe(viewModel.events, ::onEvent)
        //observe(viewModel.data, ::addDataInList)
        viewModel.onViewCreated()

        credentialManager = CredentialManager.create(requireContext())

        view.btnSignIn.setOnClickListener {
            signIn()
        }
    }

    fun signIn(){
        val googleIdOption: GetGoogleIdOption = GetGoogleIdOption.Builder()
            .setFilterByAuthorizedAccounts(false)
            .setServerClientId(getString(R.string.server_client_id))
            .build()

        var request = GetCredentialRequest.Builder()
            .setCredentialOptions(listOf(googleIdOption))
            .build()


        CoroutineScope(Dispatchers.IO).launch {
            try {
                val result = credentialManager?.getCredential(
                    request = request,
                    context = requireContext(),
                )
                result?.let {
                    handleSignIn(result)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


    fun handleSignIn(result: GetCredentialResponse) {
        // Handle the successfully returned credential.
        val credential = result.credential

        var responseJson = ""

        when (credential) {
            is PublicKeyCredential -> {
                // Share responseJson such as a GetCredentialResponse on your server to
                // validate and authenticate
                responseJson = credential.authenticationResponseJson
                Log.d("qwwe", "resp: $responseJson")
            }

            is PasswordCredential -> {
                // Send ID and password to your server to validate and authenticate.
                val username = credential.id
                val password = credential.password
            }

            is CustomCredential -> {
                if (credential.type == GoogleIdTokenCredential.TYPE_GOOGLE_ID_TOKEN_CREDENTIAL) {
                    try {
                        // Use googleIdTokenCredential and extract id to validate and
                        // authenticate on your server.
                        val googleIdTokenCredential = GoogleIdTokenCredential
                            .createFrom(credential.data)
                        Log.d("qwwe", "google info $googleIdTokenCredential, name ${googleIdTokenCredential.displayName}")
                    } catch (e: GoogleIdTokenParsingException) {
                        Log.e("qwwe", "Received an invalid google id token response", e)
                    }
                } else {
                    // Catch any unrecognized custom credential type here.
                    Log.e("qwwe", "Unexpected type of credential")
                }
            }

            else -> {
                // Catch any unrecognized credential type here.
                Log.e("qwwe", "Unexpected type of credential")
            }
        }
    }
}