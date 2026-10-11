package com.example.pw2_4.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.pw2_4.components.HistoryList
import com.example.pw2_4.components.MetricCard
import com.example.pw2_4.components.StepControlRow
import com.example.pw2_4.viewmodel.CompensationViewModel

@Composable
fun ControlPanelScreen(
    viewModel: CompensationViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = "Диспетчерський пульт КРП (MVVM)",
            style = MaterialTheme.typography.headlineSmall
        )
        Text(
            text = "Керування підстанцією 110/10 кВ • БСК-400 квар",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MetricCard(
                title = "Поточний cos φ",
                value = "${state.currentCosPhi}",
                subtitle = "Базовий: ${state.initialCosPhi}",
                valueColor = if (state.currentCosPhi >= 0.95) Color(0xFF2E7D32) else Color(0xFFE65100),
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                title = "Зниження втрат",
                value = "${state.lossReductionPercent}%",
                subtitle = "В лініях мережі",
                valueColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            MetricCard(
                title = "Скомпенсовано Q",
                value = "${state.compensatedPowerQ.toInt()} квар",
                subtitle = "Потужність БСК",
                modifier = Modifier.weight(1f)
            )
            MetricCard(
                title = "Залишок Q в мережі",
                value = "${state.remainingPowerQ.toInt()} квар",
                subtitle = "Початкове: ${state.reactivePowerQ.toInt()} квар",
                valueColor = if (state.remainingPowerQ <= 50.0) Color(0xFF2E7D32) else MaterialTheme.colorScheme.onSurface,
                modifier = Modifier.weight(1f)
            )
        }
        StepControlRow(
            activeSteps = state.activeSteps,
            maxSteps = state.maxSteps,
            onIncrement = { viewModel.incrementStep() },
            onDecrement = { viewModel.decrementStep() },
            onReset = { viewModel.resetSteps() }
        )

        Spacer(modifier = Modifier.height(4.dp))
        HistoryList(logs = state.history)
    }
}