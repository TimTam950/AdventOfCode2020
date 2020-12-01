fun main(args: Array<String>) {
    val inputStrings = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n")
    val input = inputStrings.mapNotNull { it.toIntOrNull() }

    val pairSolution = pairs(input).filter { (a,b) -> a + b == 2020 }
        .map { (a, b) -> a * b }
        .first()

    val tripleSolution = triples(input).filter { (a,b,c) -> a + b + c == 2020 }
        .map { (a, b, c) -> a * b * c}
        .first()

    println("pair solution: $pairSolution\ttriple solution: $tripleSolution")
}

fun <T> pairs(input: List<T>): Sequence<Pair<T, T>> = sequence {
        for (i in 0 until input.size - 1) {
            for (j in i+1 until input.size) {
                yield(input[i] to input[j])
            }
        }
    }

fun <T> triples(input: List<T>): Sequence<Triple<T,T,T>> = sequence {
    for (i in 0 until input.size - 2) {
        for (j in i + 1 until input.size - 1) {
            for (k in j + 1 until input.size) {
                yield(Triple(input[i], input[j], input[k]))
            }
        }
    }
}
