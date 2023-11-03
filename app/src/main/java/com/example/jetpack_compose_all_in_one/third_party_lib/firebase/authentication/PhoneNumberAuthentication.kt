package com.example.jetpack_compose_all_in_one.third_party_lib.firebase.authentication

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.firebase.FirebaseException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthOptions
import com.google.firebase.auth.PhoneAuthProvider
import java.util.concurrent.TimeUnit


private lateinit var firebaseAuth: FirebaseAuth
private lateinit var callback: PhoneAuthProvider.OnVerificationStateChangedCallbacks
private lateinit var resendToken: PhoneAuthProvider.ForceResendingToken

private lateinit var mobileNumber: MutableState<String>
private lateinit var otp: MutableState<String>
private lateinit var storedVerificationId: MutableState<String>
private lateinit var message: MutableState<String>

@Composable
fun PhoneNumberAuthentication(context: Context) {

    mobileNumber = remember {
        mutableStateOf("")
    }
    otp = remember {
        mutableStateOf("")
    }

    storedVerificationId = remember {
        mutableStateOf("")
    }

    message = remember {
        mutableStateOf("")
    }

    setUp(context)



    Column(
        modifier = Modifier
            .fillMaxHeight()
            .fillMaxWidth()
            .background(Color.White),

        verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = mobileNumber.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { mobileNumber.value = it },
            placeholder = { Text(text = "Enter your phone number") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (TextUtils.isEmpty(mobileNumber.value)) {
                    toast(context, "Please enter phone number..")
                } else {

                    val number = "+1${mobileNumber.value}"
                    sendVerificationCode(number, context as Activity)
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Send verification code", modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = otp.value,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            onValueChange = { otp.value = it },
            placeholder = { Text(text = "Enter your otp") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = true,
        )

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                if (TextUtils.isEmpty(otp.value)) {
                    toast(context, "Please enter otp..")
                } else {
                    val credential: PhoneAuthCredential = PhoneAuthProvider.getCredential(
                        storedVerificationId.value, otp.value
                    )
                    signInWithPhoneAuthCredential(
                        credential,
                        context as Activity,
                        context
                    )
                }
            },

            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(text = "Verify OTP", modifier = Modifier.padding(8.dp))
        }

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = message.value,
            style = TextStyle(color = Color.Green, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        )

    }

}

private fun setUp(context: Context) {
    firebaseAuth = FirebaseAuth.getInstance()

    callback = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
        override fun onVerificationCompleted(p0: PhoneAuthCredential) {
            message.value = "Verification successful"
            toast(context, "Verification successful..")
        }

        override fun onVerificationFailed(p0: FirebaseException) {
            message.value = "Fail to verify user : \n" + p0.message
            toast(context, "Verification failed..")
        }

        override fun onCodeSent(
            verificationId: String,
            reSend: PhoneAuthProvider.ForceResendingToken
        ) {
            super.onCodeSent(verificationId, reSend)
            storedVerificationId.value = verificationId
            resendToken = reSend
        }
    }
}

@Preview
@Composable
fun PhoneNumberAuthenticationPreview() {

}


private fun signInWithPhoneAuthCredential(
    credential: PhoneAuthCredential, activity: Activity,
    context: Context,
) {
    firebaseAuth.signInWithCredential(credential)
        .addOnCompleteListener(activity) { task ->

            if (task.isSuccessful) {
                message.value = "Verification successful"
                toast(context, "Verification successful..")
            } else {
                if (task.exception is FirebaseAuthInvalidCredentialsException) {
                    toast(
                        context,
                        "Verification failed.." + (task.exception as FirebaseAuthInvalidCredentialsException).message
                    )
                }
            }
        }
}


private fun sendVerificationCode(
    number: String,
    activity: Activity,
) {
    val options = PhoneAuthOptions.newBuilder(firebaseAuth)
        .setPhoneNumber(number)
        .setTimeout(60L, TimeUnit.SECONDS)
        .setActivity(activity)
        .setCallbacks(callback)
        .build()
    PhoneAuthProvider.verifyPhoneNumber(options)
}


private fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_LONG).show()
}