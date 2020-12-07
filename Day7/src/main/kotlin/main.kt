fun main(args: Array<String>) {
    val input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n").toMutableList()

    val newinput = input.map { it.split(" contain ") }
            .map{ it.map { bagString ->  bagString.replace("bags", "").replace("bag", "").trim().replace(".", "")}}
            .map { it[0] to  it[1].split(",")}.toMap()

    var usedColors = mutableSetOf<String>()
    for(key in newinput.keys) {
        countBags(key, key, "shiny gold", newinput, usedColors)
    }
    println(usedColors.size - 1)
}

fun countBags(rootRuleBag: String, currentBagColor: String, searchingBagColor: String, rules: Map<String, List<String>>, usedColors: MutableSet<String>) {
    if (currentBagColor.trim() in searchingBagColor) usedColors.add(rootRuleBag)
    if (currentBagColor.trim() == "no other") return

    for(rule in rules[currentBagColor] ?: error("no value for key $currentBagColor")) {
        val newRule = if (rule.trim()[0].toString().toIntOrNull() != null) rule.substring(2).trim() else rule
        countBags(rootRuleBag, newRule, searchingBagColor, rules, usedColors)
    }
}