package com.example.pw2_4.viewmodel
import androidx.lifecycle.ViewModel
import com.example.pw2_4.model.CompensationUiState
import com.example.pw2_4.model.SwitchingLog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.math.atan
import kotlin.math.cos
import kotlin.math.max
import kotlin.math.min
import kotlin.math.pow
import kotlin.math.sqrt

class CompensationViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CompensationUiState())
    val uiState: StateFlow<CompensationUiState> = _uiState.asStateFlow()

    init {
        recalculateParameters(_uiState.value.activeSteps, "Ініціалізація системи моніторингу")
    }
    fun incrementStep() {
        val current = _uiState.value
        if (current.activeSteps < current.maxSteps) {
            val newSteps = current.activeSteps + 1
            recalculateParameters(newSteps, "Увімкнено ступінь №$newSteps")
        }
    }
    fun decrementStep() {
        val current = _uiState.value
        if (current.activeSteps > 0) {
            val disconnectedStep = current.activeSteps
            val newSteps = current.activeSteps - 1
            recalculateParameters(newSteps, "Вимкнено ступінь №$disconnectedStep")
        }
    }
    fun resetSteps() {
        if (_uiState.value.activeSteps > 0) {
            recalculateParameters(0, "Аварійне скидання всіх ступенів БСК")
        }
    }
    private fun recalculateParameters(newActiveSteps: Int, actionDescription: String) {
        _uiState.update { state ->
            val p = state.activePowerP
            val qInit = state.reactivePowerQ
            val qComp = newActiveSteps * state.stepPowerQ

            val qRem = max(0.0, qInit - qComp)

            val initCos = p / sqrt(p.pow(2) + qInit.pow(2))
            val curCos = min(1.0, p / sqrt(p.pow(2) + qRem.pow(2)))

            val lossReduction = if (curCos > initCos) {
                (1.0 - (initCos / curCos).pow(2)) * 100.0
            } else {
                0.0
            }

            val timeFormat = SimpleDateFormat("HH:mm:ss", Locale.getDefault())
            val currentTime = timeFormat.format(Date())

            val newLog = SwitchingLog(
                timestamp = currentTime,
                action = actionDescription,
                activeSteps = newActiveSteps,
                resultingCosPhi = String.format(Locale.US, "%.2f", curCos).toDouble()
            )

            state.copy(
                activeSteps = newActiveSteps,
                compensatedPowerQ = qComp,
                remainingPowerQ = qRem,
                initialCosPhi = String.format(Locale.US, "%.2f", initCos).toDouble(),
                currentCosPhi = String.format(Locale.US, "%.2f", curCos).toDouble(),
                lossReductionPercent = String.format(Locale.US, "%.1f", lossReduction).toDouble(),
                history = listOf(newLog) + state.history
            )
        }
    }
}