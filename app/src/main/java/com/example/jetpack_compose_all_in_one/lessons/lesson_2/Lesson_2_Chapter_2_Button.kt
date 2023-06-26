package com.example.jetpack_compose_all_in_one.lessons.lesson_2

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExtendedFloatingActionButton
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.IconToggleButton
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_all_in_one.ui.components.FullWidthRow
import com.example.jetpack_compose_all_in_one.ui.components.LessonHeader
import com.example.jetpack_compose_all_in_one.ui.components.LessonText2
import com.example.jetpack_compose_all_in_one.ui.views.chat.IndicatingIconButton

@Composable
fun Lesson_2_Chapter_2_Screen() {
    LessonContent()
}

@Composable
private fun LessonContent() {

    LazyColumn(Modifier.fillMaxSize()) {

        item {

            val paddingModifier = Modifier.padding(8.dp)

            LessonHeader(text = "Button")

            LessonText2(text = "Button")
            ButtonExample(paddingModifier)
            DisabledButtonExample(paddingModifier)
            ButtonWithIconExample(paddingModifier)
            ButtonBackgroundExample(paddingModifier)
            GradientButtonExample(paddingModifier)

            LessonText2(text = "Icon Button")
            IconButtonExample(paddingModifier)

            LessonText2(text = "Custom Ripple Icon Button")
            CustomIconButtonExample(paddingModifier)

            LessonHeader(text = "Floating Action Button")
            FloatingActionButtonExample(paddingModifier)
        }
    }
}

@Composable
private fun ButtonExample(modifier: Modifier) {
    FullWidthRow {

        Button(onClick = {}, modifier = modifier) {
            Text(text = "Button")
        }

        TextButton(onClick = {}, modifier = modifier) {
            Text(text = "TextButton")
        }

        OutlinedButton(
            onClick = {},
            modifier = modifier,
        ) {
            Text(text = "Outlined")
        }
    }

    FullWidthRow {
        Button(
            onClick = {},
            modifier = modifier,
            shape = RoundedCornerShape(15.dp)
        ) {
            Text(text = "Rounded")
        }

        Button(
            onClick = {},
            modifier = modifier,
            shape = RoundedCornerShape(
                topStartPercent = 30,
                topEndPercent = 0,
                bottomStartPercent = 0,
                bottomEndPercent = 0
            )
        ) {
            Text(text = "Rounded")
        }

        Button(
            onClick = { },
            modifier = modifier,
            shape = CutCornerShape(20)
        ) {
            Text(text = "CutCorner")
        }
    }
}

@Composable
private fun DisabledButtonExample(modifier: Modifier) {
    FullWidthRow {
        Button(
            onClick = {},
            modifier = modifier,
            enabled = false
        ) {
            Text(text = "Button")
        }

        TextButton(
            onClick = {},
            modifier = modifier,
            enabled = false
        ) {
            Text(text = "TextButton")
        }

        OutlinedButton(
            onClick = {},
            modifier = modifier,
            enabled = false
        ) {
            Text(text = "Outlined")
        }
    }
}

@Composable
private fun ButtonWithIconExample(modifier: Modifier) {
    FullWidthRow {
        Button(
            onClick = {},
            modifier = modifier
        ) {
            Row {
                Icon(
                    imageVector = Icons.Default.FavoriteBorder,
                    modifier = Modifier.padding(end = 4.dp),
                    contentDescription = null
                )
                Text(text = "Icon+Text")
            }
        }

        Button(
            onClick = {},
            modifier = modifier
        ) {
            Text(text = "Text+Icon")
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                modifier = Modifier.padding(start = 4.dp),
                contentDescription = null
            )
        }

        Button(
            onClick = {},
            shape = RoundedCornerShape(20),
            modifier = modifier
        ) {
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                contentDescription = null
            )
        }
    }

    FullWidthRow {
        OutlinedButton(
            onClick = {},
            modifier = Modifier.padding(8.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Call,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp),
                contentDescription = null
            )
            Text(text = "Icon+Text+Icon")
            Icon(
                imageVector = Icons.Default.FavoriteBorder,
                modifier = Modifier.padding(start = 4.dp),
                contentDescription = null
            )
        }
    }
}

@Composable
private fun ButtonBackgroundExample(modifier: Modifier) {
    FullWidthRow {

        Button(
            onClick = {},
            modifier = modifier,
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xffF57C00),
                contentColor = Color(0xffB2EBF2)
            )
        ) {
            Text(text = "Button")
        }

        TextButton(
            onClick = {},
            modifier = modifier,
            colors = ButtonDefaults.textButtonColors(
                contentColor = Color(0xff8BC34A)
            )
        ) {
            Text(text = "TextButton")
        }

        OutlinedButton(
            onClick = {},
            modifier = modifier,
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color(0xff795548)
            )
        ) {
            Text(text = "Outlined")
        }
    }
}

@Composable
private fun GradientButtonExample(modifier: Modifier) {

    val horizontalGradientBrush = Brush.horizontalGradient(
        colors = listOf(
            Color(0xffF57F17),
            Color(0xffFFEE58),
            Color(0xffFFF9C4)
        )
    )

    val verticalGradientBrush = Brush.verticalGradient(
        colors = listOf(
            Color(0xff4E342E),
            Color(0xff8D6E63),
            Color(0xffD7CCC8)
        )
    )

    FullWidthRow {
        Column(
            modifier = modifier
                .clip(RoundedCornerShape(20))
                .background(brush = horizontalGradientBrush)
                .clickable(onClick = { })
                .then(modifier)
        ) {
            Text(text = "Horizontal Gradient")
        }

        Column(
            modifier = modifier
                .clip(RoundedCornerShape(20))
                .background(brush = verticalGradientBrush)
                .clickable(onClick = { })
                .then(modifier)
        ) {
            Text(text = "Vertical Gradient")
        }
    }
}

@Composable
private fun IconButtonExample(modifier: Modifier) {

    FullWidthRow {
        IconButton(onClick = {}, modifier = modifier) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null
            )
        }

        var checked by remember { mutableStateOf(false) }

        IconToggleButton(
            checked = checked,
            onCheckedChange = { checked = it },
            modifier = modifier
        ) {

            val tint by animateColorAsState(
                targetValue = if (checked) Color(0xffE91E63) else Color(0xffB0BEC5),
                animationSpec = tween(durationMillis = 400)
            )

            Icon(
                Icons.Filled.Favorite, tint = tint,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun CustomIconButtonExample(modifier: Modifier) {

    // rememberRipple of this custom button defines ripple radius, color and if it will be bounded

    FullWidthRow(modifier.padding(horizontal = 30.dp)) {
        IndicatingIconButton(
            onClick = { /*TODO*/ },
            indication = rememberRipple(
                bounded = false,
                radius = 40.dp,
                color = Color(
                    0xff42A5F5
                )
            )
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color(0xffE91E63)
            )
        }

        Spacer(modifier = Modifier.width(40.dp))

        IndicatingIconButton(
            onClick = { /*TODO*/ },
            indication = rememberRipple(
                bounded = false,
                radius = 30.dp,
                color = Color(0xffBF360C)
            )
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color(0xffE91E63)
            )
        }

        Spacer(modifier = Modifier.width(40.dp))

        // this one's ripple is bounded
        IndicatingIconButton(
            onClick = { /*TODO*/ },
            indication = rememberRipple(
                bounded = true,
                radius = 30.dp,
                color = Color(0xff311B92)
            )
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color(0xffE91E63),
                modifier = Modifier.size(36.dp)
            )
        }

        Spacer(modifier = Modifier.width(40.dp))

        IndicatingIconButton(
            onClick = { /*TODO*/ },
            indication = rememberRipple(
                bounded = false,
                radius = 50.dp,
                color = Color(0xff43A047)
            )
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null,
                tint = Color(0xffE91E63),
                modifier = Modifier.size(36.dp)
            )
        }
    }
}

@Composable
private fun FloatingActionButtonExample(modifier: Modifier) {

    FullWidthRow {

        FloatingActionButton(
            onClick = {},
            modifier = modifier,
        ) {
            Icon(
                Icons.Filled.Favorite,
                contentDescription = null
            )
        }

        FloatingActionButton(
            onClick = {},
            modifier = modifier,
            backgroundColor = Color(0xffFFA000)
        ) {
            Icon(
                Icons.Filled.Done, tint = Color.White,
                contentDescription = null
            )
        }

        ExtendedFloatingActionButton(
            text = { Text("Extended") },
            onClick = {},
            modifier = modifier
        )

        ExtendedFloatingActionButton(
            icon = {
                Icon(
                    imageVector = Icons.Filled.Favorite,
                    tint = Color.White,
                    contentDescription = null
                )
            },
            text = { Text("Like", color = Color.White) },
            backgroundColor = Color(0xffEC407A),
            onClick = {},
            modifier = modifier
        )
    }
}
