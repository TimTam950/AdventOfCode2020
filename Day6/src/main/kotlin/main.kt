fun main(args: Array<String>) {
    val input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n")

    val part1 = input.map { getYesCountForGroup(it) }
        .sum()

    println(part1)
}

fun getYesCountForGroup(answers: String): Int {
    val groupAnswers = answers.split("\n")
    var yesses = mutableSetOf<String>()
    for(answer in groupAnswers) {
        yesses + answer
    }
    return yesses.size
}