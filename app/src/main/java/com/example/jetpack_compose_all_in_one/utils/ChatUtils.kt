package com.example.jetpack_compose_all_in_one.utils

import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.text.TextLayoutResult
import com.example.jetpack_compose_all_in_one.features.chatting.ChatRowData

/**
 * Measure text properties and update [chatRowData] based on text width, and line count.
 * Use this method to update if [TextLayoutResult] is not available in overloaded chat row
 * composable.
 */
fun measureText(chatRowData: ChatRowData, textLayoutResult: TextLayoutResult) {
    chatRowData.text = textLayoutResult.layoutInput.text.text
    chatRowData.lineCount = textLayoutResult.lineCount
    chatRowData.lastLineWidth =
        textLayoutResult.getLineRight(chatRowData.lineCount - 1)
    chatRowData.textWidth = textLayoutResult.size.width
}

/**
 * Calculate [message] and [status] positions based on [chatRowData].
 * Number of message text lines, last line width, width of the parent based on quote,
 * user name, images, videos,etc.
 * effect the position of [status] if it's not null
 */
internal fun calculateChatWidthAndHeight(
    chatRowData: ChatRowData,
    message: Placeable,
    status: Placeable?,
) {

    if (status != null) {

        val lineCount = chatRowData.lineCount
        val lastLineWidth = chatRowData.lastLineWidth
        val parentWidth = chatRowData.parentWidth

        val padding = (message.measuredWidth - chatRowData.textWidth) / 2
        println(
            "🌽 calculateChatWidthAndHeight() text: ${chatRowData.text}" +
                    "lineCount: $lineCount, parentWidth: $parentWidth, lastLineWidth: $lastLineWidth\n" +
                    "MESSAGE width: ${message.width}, measured: ${message.measuredWidth}," +
                    " textWidth: ${chatRowData.textWidth} padding: $padding\n" +
                    "STATUS width: ${status.width}, measured: ${status.measuredWidth}, " +
                    "(stat +last): ${lastLineWidth + status.measuredWidth}\n"
        )

        // Multiple lines and last line and status is longer than text size and right padding
        if (lineCount > 1 && lastLineWidth + status.measuredWidth >= chatRowData.textWidth + padding) {
            chatRowData.rowWidth = message.measuredWidth
            chatRowData.rowHeight = message.measuredHeight + status.measuredHeight
            chatRowData.measuredType = 0
//            println("🤔 calculateChatWidthAndHeight() 0 for ${chatRowData.textWidth + padding}")
        } else if (lineCount > 1 && lastLineWidth + status.measuredWidth < chatRowData.textWidth + padding) {
            // Multiple lines and last line and status is shorter than text size and right padding
            chatRowData.rowWidth = message.measuredWidth
            chatRowData.rowHeight = message.measuredHeight
            chatRowData.measuredType = 1
//            println("🔥 calculateChatWidthAndHeight() 1 for ${message.measuredWidth - padding}")
        } else if (lineCount == 1 && message.width + status.measuredWidth >= parentWidth) {
            chatRowData.rowWidth = message.measuredWidth
            chatRowData.rowHeight = message.measuredHeight + status.measuredHeight
            chatRowData.measuredType = 2
//            println("🎃 calculateChatWidthAndHeight() 2")
        } else {
            chatRowData.rowWidth = message.measuredWidth + status.measuredWidth
            chatRowData.rowHeight = message.measuredHeight
            chatRowData.measuredType = 3
//            println("🚀 calculateChatWidthAndHeight() 3")
        }
    } else {
        chatRowData.rowWidth = message.width
        chatRowData.rowHeight = message.height
    }
}
