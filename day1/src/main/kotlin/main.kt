fun main(args: Array<String>) {
    val inputStrings = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n")
    val input = inputStrings.mapNotNull { it.toIntOrNull() }

    // find 2 numbers that when summed equals 2020
    println("2 numbers:")
    for(i in input.indices) {
        var j = i + 1
        while (j < input.size - 1) {
            j++
            if (input[i] + input [j] == 2020) {
                println("${input[i]} * ${input[j]} = ${input[i] * input[j]}")
            }
        }
    }

    println("3 numbers:")
    for (i in input.indices) {
        var j = i + 1
        while (j < input.size - 1){
            var k = j + 1
            while (k < input.size - 2) {
                k++
                if (input[i] + input [j] + input[k] == 2020) {
                    println("${input[i]} * ${input[j]} * ${input[k]} = ${input[i] * input[j] * input[k]}")
                }
            }
            j++
        }
    }
}