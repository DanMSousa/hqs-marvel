package com.daniellms.core.layout.items

import android.icu.text.ListFormatter.Width
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BoxItemBottomBorder(modifier: Modifier = Modifier ,content: @Composable BoxScope.() -> Unit ) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .drawBehind {
                val strokeWidth = 0.8.dp.toPx()
                val y = getThicknessLine(size.height, strokeWidth)
                drawLine(
                    color = Color.LightGray,
                    strokeWidth = strokeWidth,
                    start = getStartOfTheLine(y),
                    end = getEndOfTheLine(size.width, y)
                )
            }
    ) {
        content()
    }
}

private fun getThicknessLine(height: Float, strokeWidth: Float): Float {
    return (height - strokeWidth / 2)
}

private fun getStartOfTheLine(y: Float) : Offset {
    return Offset(0f, y)
}

private fun getEndOfTheLine(width: Float, y: Float) : Offset {
    return Offset(width, y)
}
