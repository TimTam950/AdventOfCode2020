fun main(args: Array<String>) {
    val inputStrings = object {}.javaClass.getResourceAsStream("input.txt").bufferedReader().readLines()

    println("part1: ${countTrees(3, 1, inputStrings)}")
    val part2Inputs = listOf(Pair(1,1), Pair(3,1), Pair(5,1), Pair(7, 1), Pair(1,2))
    var multipleResult = 1L
    for (pair in part2Inputs) {
        multipleResult *= countTrees(pair.first, pair.second, inputStrings)
    }
    println("par2: $multipleResult")
}

fun countTrees(xDelta: Int, yDelta: Int, input: List<String>): Int {
    var trees = 0
    var x = 0
    var y = 0
    while (y < input.size) {
        if (input[y][x].toString() == "#") trees++

        x = (x + xDelta) % input[0].length
        y += yDelta
    }
    return trees
}