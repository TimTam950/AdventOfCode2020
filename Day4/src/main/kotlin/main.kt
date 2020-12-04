fun main(args: Array<String>) {
    val input = object {}.javaClass.getResource("input.txt").readText(Charsets.UTF_8).split("\n\n")

    val result1 = input.map { passport -> passport.replace("\n", " ").split(" ").map { it.substring(0..2) } }
        .filter { it.size == 8 || (it.size == 7 && "cid" !in it)}
        .count()

    println("part 1 $result1")

    val result2 = input.map { passport -> passport.replace("\n", " ").split(" ") }
        .filter { passport -> validatePassport(passport) }
        .count()

    println("part 2 $result2")
}

fun validatePassport(passport: List<String>): Boolean {
    var stillValidValues = true
    val requiredInputs = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")
    var fieldsInPassport = mutableListOf<String>()

    for(field in passport) {
        val passportEntry = field.split(":")
        fieldsInPassport.add(passportEntry[0])
        stillValidValues = when (passportEntry[0]) {
            "byr" -> passportEntry[1].toInt() in 1920..2002
            "iyr" -> passportEntry[1].toInt() in 2010..2020
            "eyr" -> passportEntry[1].toInt() in 2020..2030
            "hgt" -> when {
                    "in" in passportEntry[1] -> passportEntry[1].substringBefore("in").toInt() in 59..76
                    "cm" in passportEntry[1] -> passportEntry[1].substringBefore("cm").toInt() in 150..193
                    else -> false
                }
            "hcl" -> Regex("^#[0-9a-f]{6}$").matches(passportEntry[1])
            "ecl" -> passportEntry[1] in setOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
            "pid" -> Regex("^\\d{9}$").matches(passportEntry[1])
            "cid" -> true
            else -> false
        }
        if (!stillValidValues) break
    }
    fieldsInPassport.remove("cid")
    val requiredFieldsUsed = requiredInputs.containsAll(fieldsInPassport) && fieldsInPassport.containsAll(requiredInputs)
    return stillValidValues && requiredFieldsUsed
}