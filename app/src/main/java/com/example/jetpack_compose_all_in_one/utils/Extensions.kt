package com.example.jetpack_compose_all_in_one.utils

import android.content.Context
import android.widget.Toast

fun showToast(context: Context, message:String) = Toast.makeText(context,message, Toast.LENGTH_SHORT).show()

fun showLongToast(context: Context, message:String) = Toast.makeText(context,message, Toast.LENGTH_LONG).show()