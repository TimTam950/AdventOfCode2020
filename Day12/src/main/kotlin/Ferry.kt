import java.lang.IllegalArgumentException
import kotlin.math.absoluteValue

class Ferry(var bearing: Int, var eastLocation: Int = 0, var northLocation: Int = 0) {

    fun performNavInstruction(navInstruction: String) {
        val action: String = navInstruction.substring(0, 1)
        val value: Int = navInstruction.substring(1).toInt()

        when(action) {
            "N" -> northLocation += value
            "E" -> eastLocation += value
            "S" -> northLocation -= value
            "W" -> eastLocation -= value
            "L" -> bearing += value
            "R" -> bearing -= value
            "F" -> moveForward(value)
        }
    }

    private fun moveForward(value: Int) {
        val currentDirection: String =  when(bearing % 360) {
            0 -> "E"
            90, -270 -> "N"
            180, -180 -> "W"
            270, -90 -> "S"
            else -> throw IllegalArgumentException("Invalid bearing $bearing")
        }
        performNavInstruction(currentDirection + value)
    }

    fun printManhattanDistance() {
        val distance = eastLocation.absoluteValue + northLocation.absoluteValue
        println("Manhattan Distance Part 1: $distance")
    }
}