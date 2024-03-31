package com.example.jetpack_compose_all_in_one.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpack_compose_all_in_one.R
import com.example.jetpack_compose_all_in_one.ui.theme.dp_15
import com.example.jetpack_compose_all_in_one.ui.theme.spaceSmall

@Composable
fun InputFields() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        SimpleTextField()
        Spacer(modifier = Modifier.padding(spaceSmall))
        LabelAndPlaceHolder()
        Spacer(modifier = Modifier.padding(spaceSmall))
        OutlineTextField()
        Spacer(modifier = Modifier.padding(spaceSmall))
        TextFieldWithIcons()
        Spacer(modifier = Modifier.padding(spaceSmall))
        PasswordTextField()
        Spacer(modifier = Modifier.padding(spaceSmall))
        TextFieldWithNumbers()
    }
}

@Composable
fun SimpleTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = text, onValueChange = { newText ->
        text = newText
    })
}

@Composable
fun LabelAndPlaceHolder() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    TextField(value = text, onValueChange = { text = it },
        label = { Text(text = "Username") },
        placeholder = { Text(text = "username") }
    )
}

@Composable
fun OutlineTextField() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        onValueChange = { text = it },
        label = { Text(text = "Enter Your Name") }
    )
}

@Composable
fun TextFieldWithIcons() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Email, contentDescription = "emailIcon") },
        onValueChange = { text = it },
        label = { Text(text = "Email") }
    )
}

@Composable
fun TextFieldWithNumbers() {
    var text by remember { mutableStateOf(TextFieldValue("")) }
    OutlinedTextField(
        value = text,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number
        ),
        onValueChange = { text = it },
        leadingIcon = { Icon(imageVector = Icons.Default.Phone, contentDescription = "phoneIcon") },
        label = { Text(text = "Mobile Number") }
    )
}

@Composable
private fun PasswordTextField() {
    var text by remember {
        mutableStateOf(TextFieldValue(""))
    }
    val showPassword by remember {
        mutableStateOf(false)
    }
    OutlinedTextField(
        value = text,
        leadingIcon = { Icon(imageVector = Icons.Default.Lock, contentDescription = "lockIcon") },
        onValueChange = { text = it },
        label = { Text(text = "Password") },
        visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation()
    )
}

@Composable
fun GradientTextField(
    value: String,
    gradient: Brush,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    textStyle: TextStyle = LocalTextStyle.current,
    label: @Composable (() -> Unit)? = null,
    placeholder: @Composable (() -> Unit)? = null,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    isError: Boolean = false,
    singleLine: Boolean = false,
    maxLines: Int = if (singleLine) 1 else Int.MAX_VALUE,
    minLines: Int = 1,
    shape: Shape = RoundedCornerShape(10),
    onValueChange: (String) -> Unit
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.then(Modifier.background(gradient, shape)),
        enabled = enabled,
        textStyle = textStyle,
        label = label,
        placeholder = placeholder,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        isError = isError,
        singleLine = singleLine,
        maxLines = maxLines,
        minLines = minLines,
        shape = shape,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
            errorContainerColor = Color.Transparent,
            focusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            disabledLeadingIconColor = MaterialTheme.colorScheme.onPrimary,
            errorLeadingIconColor = MaterialTheme.colorScheme.onError,
            focusedTextColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedTextColor = MaterialTheme.colorScheme.onPrimary,
            disabledTextColor = MaterialTheme.colorScheme.onPrimary,
            errorTextColor = MaterialTheme.colorScheme.onError,
            focusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            unfocusedLabelColor = MaterialTheme.colorScheme.onPrimary,
            disabledLabelColor = MaterialTheme.colorScheme.onPrimary,
            errorLabelColor = MaterialTheme.colorScheme.onError,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
        )
    )
}

@Composable
fun SimpleSearchBar(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    onChange: (String) -> Unit = {},
    onSearch: () -> Unit
) {

    ElevatedCard {
        OutlinedTextField(
            value = text,
            onValueChange = onChange,
            modifier = modifier,
            enabled = enabled,
            label = label?.run { { Text(this) } },
            trailingIcon = {
                SimpleIconButton(
                    iconResourceInt = R.drawable.baseline_search_24,
                    onClick = onSearch
                )
            }
        )
    }
}

@Composable
fun AutoCompleteSearchBar(
    text: String,
    suggestions: List<String>,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    label: String? = null,
    suggestionsComponent: (@Composable (String) -> Unit)? = null,
    maxItems: Int = 5,
    onChange: (String) -> Unit = {},
    onSuggestionClick: (Int) -> Unit = {},
    onSearch: () -> Unit
) {
    ElevatedCard(
        modifier = modifier.then(Modifier.width(IntrinsicSize.Min))
    ) {
        OutlinedTextField(
            value = text,
            onValueChange = onChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(dp_15),
            enabled = enabled,
            label = label?.run { { Text(this) } },
            trailingIcon = {
                SimpleIconButton(
                    iconResourceInt = R.drawable.baseline_search_24,
                    onClick = onSearch
                )
            }
        )

        for (i in 0 until minOf(suggestions.size, maxItems)) {
            suggestionsComponent?.invoke(text) ?: Text(
                suggestions[i],
                Modifier
                    .fillMaxWidth()
                    .clickable { onSuggestionClick(i) }
                    .padding(dp_15)
            )
            if (i < minOf(suggestions.size, maxItems)) Divider()
        }
    }
}


@Preview
@Composable
private fun SimpleSearchBarPreview() = SimpleSearchBar("Testing testing..."){}

@Preview
@Composable
private fun AutocompleteSearchBarPreview() {
    AutoCompleteSearchBar(
        "Testing testing...",
        listOf("1", "222222....", "3. Hey Bob the mic is not working.")
    ) {}
}