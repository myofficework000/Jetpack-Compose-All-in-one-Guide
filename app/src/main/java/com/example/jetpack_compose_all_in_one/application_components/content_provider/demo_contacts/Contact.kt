package com.example.jetpack_compose_all_in_one.application_components.content_provider.demo_contacts

/**
 * Represents a contact with an ID, name, and phone number.
 *
 * @property id The unique identifier of the contact.
 * @property name The name of the contact.
 * @property phoneNumber The phone number of the contact.
 */
data class Contact(
    val id: String,
    val name: String,
    val phoneNumber: String,
)
