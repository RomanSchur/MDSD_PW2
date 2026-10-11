package com.example.pw2_3.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.pw2_3.model.NodeStatus

@Composable
fun StatusBadge(status: NodeStatus) {
    val (bgColor, textColor) = when (status) {
        NodeStatus.ACTIVE -> Color(0xFFE8F5E9) to Color(0xFF2E7D32)
        NodeStatus.STANDBY -> Color(0xFFFFF3E0) to Color(0xFFE65100)
        NodeStatus.MAINTENANCE -> Color(0xFFFFEBEE) to Color(0xFFC62828)
    }

    Text(
        text = status.label,
        color = textColor,
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier
            .background(bgColor, shape = RoundedCornerShape(8.dp))
            .padding(horizontal = 8.dp, vertical = 4.dp)
    )
}