import kotlin.math.ceil
import kotlin.system.measureTimeMillis

fun main(args: Array<String>) {
    val input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n")
    val target = input[0].toDouble()
    val inputs = input[1].split(",")

    val answer = inputs.filter { it != "x" }
        .map { it.toInt() }
        .map { it to (ceil(target / it) * it) - target }
        .filter { it.second >= 0 }
        .minBy { it.second }

    if (answer != null) {
        println("Part1 ${answer.first * answer.second}")
    }

    
}