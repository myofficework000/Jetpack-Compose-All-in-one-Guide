package com.example.jetpack_compose_all_in_one.lessons.lesson_9

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun LessonContent() {
    val content = """
        This is the article content.
        
        # This is a heading
        
        - Bullet point 1
        - Bullet point 2
        - Bullet point 3
        
        This is some additional content.
    """.trimIndent()

    val onClick: () -> Unit = {
        // Handle click event here
    }

    ArticleContent(
        content = content,
        onClick = onClick
    )
}

@Composable
fun ArticleContent(content: String, onClick: () -> Unit = {}) {
    Column(modifier = Modifier.padding(16.dp)) {
        if (content.isEmpty()) {
            Text(
                text = "No content available",
                style = MaterialTheme.typography.subtitle1
            )
        } else {
            val paragraphs = content.split("\n")
            for (paragraph in paragraphs) {
                val trimmedParagraph = paragraph.trim()
                if (trimmedParagraph.startsWith("# ")) {
                    Text(
                        text = trimmedParagraph.removePrefix("# "),
                        style = MaterialTheme.typography.h5,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                } else if (trimmedParagraph.startsWith("- ")) {
                    Text(
                        text = trimmedParagraph.removePrefix("- "),
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                } else {
                    Text(
                        text = trimmedParagraph,
                        style = MaterialTheme.typography.body1,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
        Text(
            text = "Click here",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.clickable { onClick() }
        )
    }
}

@Preview
@Composable
fun PreviewArticleContent() {
    ArticleContent(
        content = "This is the article content.\n\n" +
                "# This is a heading\n\n" +
                "- Bullet point 1\n" +
                "- Bullet point 2\n" +
                "- Bullet point 3\n\n" +
                "This is some additional content."
    )
}
