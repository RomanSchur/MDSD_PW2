package com.example.pw2_4.model
data class SwitchingLog(
    val timestamp: String,
    val action: String,
    val activeSteps: Int,
    val resultingCosPhi: Double
)
data class CompensationUiState(
    val activePowerP: Double = 500.0,
    val reactivePowerQ: Double = 400.0,
    val stepPowerQ: Double = 50.0,
    val maxSteps: Int = 8,
    val activeSteps: Int = 0,
    val compensatedPowerQ: Double = 0.0,
    val remainingPowerQ: Double = 400.0,
    val initialCosPhi: Double = 0.78,
    val currentCosPhi: Double = 0.78,
    val lossReductionPercent: Double = 0.0,
    val history: List<SwitchingLog> = emptyList()
)