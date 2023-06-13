package com.example.jetpack_compose_all_in_one.features.notes.data

import androidx.compose.ui.text.input.TextFieldValue
import androidx.room.TypeConverter

class TextFieldValueConverter {

    @TypeConverter
    fun fromTextFieldValue(value: TextFieldValue) : String {
        return value.text
    }

    @TypeConverter
    fun toTextFieldValue(vale: String) : TextFieldValue {
        return TextFieldValue(vale)
    }
}