fun main(args: Array<String>) {
    val input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n").map { it.toLong() }

    val brokenNumber = findNumberThatBreaksPreamble(input, 25)
    println("part1 $brokenNumber")

    val part2 = findSet(input, brokenNumber)
    println("part2 $part2")
}

fun generateAllSums(numbers: List<Long>): Set<Long> {
    val sums = mutableSetOf<Long>()
    for(i in 0 until numbers.size - 1) {
        for (j in i+1 until numbers.size) {
            sums.add(numbers[i] + numbers[j])
        }
    }
    return sums
}

fun findNumberThatBreaksPreamble(inputs: List<Long>, preambleSize: Int): Long {
    var preambleStart = 0
    var preambleEnd = preambleSize
    for(i in preambleSize until inputs.size) {
        val sums = generateAllSums(inputs.subList(preambleStart,preambleEnd))
        if(inputs[i] !in sums) {
            return inputs[i]
        }
        preambleStart++
        preambleEnd++
    }
    return -1
}

fun findSet(input: List<Long>, magicNumber: Long): Long? {
    val setSizes = 2..input.size
    for(setSize in setSizes) {
        for(i in 0 until input.size - setSize) {
            val set = input.subList(i, i + setSize)
            if (set.sum() == magicNumber) {
                return (set.minOrNull()?.plus(set.maxOrNull()!!))
            }
        }
    }
    return -1
}