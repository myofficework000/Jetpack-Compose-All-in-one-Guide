package com.example.jetpack_compose_all_in_one.lessons.lesson_23

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.TabRowDefaults
import androidx.compose.material3.TabRowDefaults.SecondaryIndicator
import androidx.compose.material3.TabRowDefaults.tabIndicatorOffset
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**
 * Composable function representing a screen with tabs.
 */
@ExperimentalMaterial3Api
@Composable
fun TabScreen() {
    // State to track the index of the selected tab
    var selectedTabIndex by remember { mutableStateOf(0) }

    // Scaffold composable for layout structure
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Tab Demo") } // Title for the top app bar
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding), // Apply padding to the column content
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // Display tabs with titles
                Tabs(
                    titles = listOf("Tab 1", "Tab 2", "Tab 3"), // Titles for the tabs
                    selectedTabIndex = selectedTabIndex, // Currently selected tab index
                    onTabSelected = { index ->
                        selectedTabIndex = index // Update the selected tab index
                    }
                )
                Spacer(modifier = Modifier.height(16.dp)) // Spacer for content separation
                Text(text = "Content for Tab ${selectedTabIndex + 1}") // Content based on the selected tab
            }
        }
    )
}

/**
 * Composable function for rendering tabs.
 */
@Composable
fun Tabs(
    titles: List<String>, // List of titles for the tabs
    selectedTabIndex: Int, // Index of the currently selected tab
    onTabSelected: (Int) -> Unit // Callback for tab selection
) {
    TabRow(
        selectedTabIndex = selectedTabIndex, // Index of the currently selected tab
        indicator = { tabPositions ->
            // Customizing tab indicator position
            SecondaryIndicator(
                modifier = Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex]), // Offset based on selected tab position
                color = Color.Black // Color of the indicator
            )
        }
    ) {
        // Iterate over titles to create individual tabs
        titles.forEachIndexed { index, title ->
            Tab(
                text = { Text(title) }, // Text for the tab
                selected = selectedTabIndex == index, // Check if the tab is currently selected
                onClick = { onTabSelected(index) } // Callback when the tab is clicked
            )
        }
    }
}

/**
 * Preview function for TabScreen composable.
 */
@Preview(showBackground = true)
@Composable
@ExperimentalMaterial3Api
fun DefaultPreview() {
    TabScreen() // Displaying a preview of the TabScreen composable
}


