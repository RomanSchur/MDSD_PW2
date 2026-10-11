package com.example.pw2_3.screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.pw2_3.components.NodeCard
import com.example.pw2_3.components.TopSearchBar
import com.example.pw2_3.model.CompensationNode
import com.example.pw2_3.model.NodeStatus

@Composable
fun NodeListScreen() {
    val nodes = remember {
        listOf(
            CompensationNode(1, "ПС 110/10 кВ «Північна»", 10.0, 450, 0.94, 6, NodeStatus.ACTIVE),
            CompensationNode(2, "ТП-402 «Заводська»", 0.4, 150, 0.88, 3, NodeStatus.STANDBY),
            CompensationNode(3, "ПС 35/10 кВ «Західна»", 10.0, 600, 0.96, 8, NodeStatus.ACTIVE),
            CompensationNode(4, "ТП-115 «Мікрорайон»", 0.4, 80, 0.91, 2, NodeStatus.ACTIVE),
            CompensationNode(5, "ПС 110/35 кВ «Магістраль»", 35.0, 1200, 0.85, 12, NodeStatus.MAINTENANCE),
            CompensationNode(6, "ТП-78 «Промислова-2»", 0.4, 200, 0.93, 4, NodeStatus.ACTIVE)
        )
    }

    var searchQuery by remember { mutableStateOf("") }
    var selectedNodeName by remember { mutableStateOf<String?>(null) }
    val filteredNodes = remember(searchQuery, nodes) {
        if (searchQuery.isBlank()) {
            nodes
        } else {
            nodes.filter {
                it.name.contains(searchQuery, ignoreCase = true) ||
                        it.voltageKv.toString().contains(searchQuery)
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
    ) {
        Text(
            text = "Реєстр вузлів компенсації (КРП)",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Всього об'єктів під моніторингом: ${nodes.size}",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        Spacer(modifier = Modifier.height(12.dp))

        // ОСЬ ТУТ ДОДАЄТЬСЯ РЯДОК ПОШУКУ:
        TopSearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it }
        )

        Spacer(modifier = Modifier.height(8.dp))

        if (searchQuery.isNotBlank()) {
            Text(
                text = "Знайдено об'єктів: ${filteredNodes.size}",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
            Spacer(modifier = Modifier.height(8.dp))
        }

        if (selectedNodeName != null) {
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 12.dp)
            ) {
                Text(
                    text = "Обрано для керування: $selectedNodeName",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                    modifier = Modifier.padding(12.dp)
                )
            }
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(filteredNodes, key = { it.id }) { node ->
                NodeCard(
                    node = node,
                    onClick = {
                        selectedNodeName = "${node.name} (${node.reactivePowerKvar} квар)"
                    }
                )
            }
        }
    }
}