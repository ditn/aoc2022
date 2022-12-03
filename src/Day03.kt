fun main() {

    fun part1(input: List<String>): Int = input
        .map {
            val left = it.subSequence(0, it.length / 2)
            val right = it.subSequence(it.length / 2, it.length)

            left.toSet() intersect right.toSet()
        }
        .map(Set<Char>::first)
        .map(Char::toPriority)
        .sum()

    fun part2(input: List<String>): Int = input
        .asSequence()
        .chunked(3)
        .map { it.map(String::toSet) }
        .map { it[0] intersect it[1] intersect it[2] }
        .map(Set<Char>::first)
        .map(Char::toPriority)
        .sum()

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 157)
    check(part2(testInput) == 70)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

private fun Char.toPriority() = if (!isUpperCase()) {
    this - 'a' + 1
} else {
    code - 38
}