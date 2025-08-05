package com.example.jetpack_compose_all_in_one.lessons.lesson_15

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CheckCircle
import androidx.compose.material.icons.outlined.HighlightOff
import androidx.compose.material.icons.outlined.RadioButtonUnchecked
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.jetpack_compose_all_in_one.R
import kotlin.random.Random

@Composable
fun Lesson_15() {
    CustomRadioButtons()
}

@Composable
private fun CustomRadioButtons() {

    val dogsList = returnOptionList()

    var selectedItem by remember {
        mutableStateOf(dogsList[0].name)
    }

    Column(
        modifier = Modifier
            //.verticalScroll(state = rememberScrollState())
            .selectableGroup()
            .background(Color.White)
    ) {

        Text(
            text = "Who is the first actor?",
            modifier = Modifier.padding(6.dp),
            color = Color.Blue,
            fontSize = 32.sp,
            fontStyle = FontStyle.Italic
        )

            dogsList.forEach { dogDetails ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .selectable(
                            selected = (selectedItem == dogDetails.name),
                            onClick = { selectedItem = dogDetails.name },
                            role = Role.RadioButton,
                            interactionSource = remember { MutableInteractionSource() },
                            indication = null  // disables ripple correctly in new API
                        )
                        .padding(vertical = 8.dp)
                ) {
                    if (Random.nextInt() % 2 == 0) {
                        RadioButtonStyle(
                            selectedItem = selectedItem,
                            optionDetail = dogDetails,
                            Color.Green,
                            Icons.Outlined.CheckCircle
                        )
                    } else {
                        RadioButtonStyle(
                            selectedItem = selectedItem,
                            optionDetail = dogDetails,
                            Color.Red,
                            Icons.Outlined.HighlightOff
                            //https://fonts.google.com/icons
                        )
                    }
                }
            }
        }
    }


@Composable
private fun RadioButtonStyle(
    selectedItem: String,
    optionDetail: Option,
    color: Color,
    icon: ImageVector
) {
    Box(
        modifier = Modifier
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color =
                if (selectedItem == optionDetail.name)
                    color
                else
                    Color.LightGray,
                shape = RoundedCornerShape(percent = 10)
            )
            .padding(horizontal = 20.dp, vertical = 16.dp)
    ) {
        Row(modifier = Modifier.fillMaxWidth()) {

            // dog's image
            Image(
                modifier = Modifier
                    .size(size = 94.dp)
                    .clip(shape = CircleShape),
                painter = painterResource(id = optionDetail.image),
                contentDescription = "Dog Image"
            )

            // dog's details
            Column(
                modifier = Modifier
                    .padding(start = 16.dp, top = 6.dp, bottom = 6.dp)
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    Text(
                        text = optionDetail.name,
                        modifier = Modifier.padding(top = 6.dp),
                        color = Color.DarkGray,
                        fontSize = 22.sp
                    )

                    // check icon
                    Icon(
                        imageVector =
                        if (selectedItem == optionDetail.name)
                            icon
                        else
                            Icons.Outlined.RadioButtonUnchecked,
                        contentDescription = null,
                        tint =
                        if (selectedItem == optionDetail.name)
                            color
                        else
                            Color.Gray
                    )
                }

                // dog's age
                Text(
                    text = "${optionDetail.age}-old",
                    modifier = Modifier.padding(top = 6.dp),
                    color = Color.DarkGray,
                    fontSize = 22.sp
                )

                // dog's price
                Text(
                    text = "$${optionDetail.price}",
                    modifier = Modifier.padding(top = 6.dp),
                    color = Color.DarkGray,
                    fontSize = 22.sp
                )
            }
        }
    }
}

private fun returnOptionList(): ArrayList<Option> {
    val optionList = arrayListOf<Option>()

    optionList.add(
        Option(
            image = R.drawable.actor1,
            name = "Prince",
            age = "3-year",
            price = "250"
        )
    )

    optionList.add(
        Option(
            image = R.drawable.actor2,
            name = "Lucky",
            age = "2-year",
            price = "500"
        )
    )

    optionList.add(
        Option(
            image = R.drawable.actor3,
            name = "Frankie",
            age = "5-year",
            price = "300"
        )
    )

    return optionList
}
//deprecated
/*object NoRippleTheme : RippleTheme {
    @Composable
    override fun defaultColor(): Color = Color.Unspecified

    @Composable
    override fun rippleAlpha() = RippleAlpha(
        draggedAlpha = 0f,
        focusedAlpha = 0f,
        hoveredAlpha = 0f,
        pressedAlpha = 0f
    )
}*/


data class Option(val image: Int, val name: String, val age: String, val price: String)