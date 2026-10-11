package com.example.pw2_3.model
enum class NodeStatus(val label: String) {
    ACTIVE("В роботі"),
    STANDBY("В резерві"),
    MAINTENANCE("Технічне обслуговування")
}
data class CompensationNode(
    val id: Int,
    val name: String,
    val voltageKv: Double,
    val reactivePowerKvar: Int,
    val powerFactorCosPhi: Double,
    val stepsCount: Int,
    val status: NodeStatus
)