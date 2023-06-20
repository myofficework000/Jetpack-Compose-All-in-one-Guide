package com.example.jetpack_compose_all_in_one.application_components.content_provider.demo_contacts

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.jetpack_compose_all_in_one.ui.theme.dp_8
import com.example.jetpack_compose_all_in_one.utils.requestReadContacts
import com.google.accompanist.permissions.ExperimentalPermissionsApi

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ContactList() {
    val ctx = LocalContext.current
    val contacts = remember { mutableStateListOf<Contact>() }
    var permissionGranted by remember{ mutableStateOf(false) }
    val readContactsPermission = requestReadContacts{ permissionGranted = it }

    LaunchedEffect(permissionGranted) {
        when {
            permissionGranted -> {
                fetchContacts(ctx).let {
                    contacts.addAll(it)
                }
            }

            else -> {
                readContactsPermission.launchPermissionRequest()
            }
        }
    }

    when {
        permissionGranted -> {
            DisplayContactList(ctx, contacts)
        }
        else -> {
            // Show UI to inform the user that the permission was denied and handle the scenario
        }
    }
}

@Composable
fun DisplayContactList(context: Context, contacts: List<Contact>){

    LazyColumn{
        items(contacts){ contact ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(dp_8),
                elevation = dp_8,
                shape = RoundedCornerShape(dp_8)
            ) {

                Column (modifier = Modifier.fillMaxSize()){
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Name: ")
                        Text(text = contact.name)
                        Box{
                            IconButton(
                                modifier = Modifier.align(Alignment.TopEnd),
                                onClick = { shareContact(context,contact) }) {
                                Icon(
                                    imageVector = Icons.Default.Share,
                                    contentDescription = "Share",
                                    tint = Color.Black
                                )
                            }
                        }
                    }
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceEvenly,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Phone Number : ")
                        Text(text = contact.phoneNumber)
                    }
                }
            }
        }
    }
}

fun fetchContacts(context: Context): List<Contact> {
    val contacts = mutableListOf<Contact>()
    val uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI
    val projection = arrayOf(
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID,
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER
    )
    val selection = null
    val selectionArgs = null
    val sortOrder = null

    val contentResolver = context.contentResolver

    val cursor = contentResolver.query(
        uri,
        projection,
        selection,
        selectionArgs,
        sortOrder
    )

    cursor?.use {
        if (it.moveToFirst()) {
            val idColumnIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.CONTACT_ID)
            val nameColumnIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME)
            val phoneColumnIndex = it.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER)

            do {
                val id = it.getString(idColumnIndex)
                val name = it.getString(nameColumnIndex)
                val phoneNumber = it.getString(phoneColumnIndex)
                contacts.add(Contact(id, name, phoneNumber))
            } while (it.moveToNext())
        }
    }

    cursor?.close()

    return contacts
}

fun shareContact(context: Context, contact: Contact) {
    val text = "Name: ${contact.name}\nPhone: ${contact.phoneNumber}"
    val intent = Intent(Intent.ACTION_SEND)
    intent.type = "text/plain"
    intent.putExtra(Intent.EXTRA_TEXT, text)
    context.startActivity(intent)
}

