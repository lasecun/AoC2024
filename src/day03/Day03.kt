package day03

import println
import readInputTotal

fun main() {
    fun part1(input: String): Int {
        return """mul\((\d+),(\d+)\)""".toRegex().findAll(input)
            .sumOf { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
    }

    fun part2(input: String): Int {
        var enabled = true
        return """(mul\((\d+),(\d+)\)|do\(\)|don't\(\))""".toRegex().findAll(input).sumOf {
            if ("mul" in it.value) {
                if (enabled) return@sumOf it.groupValues[2].toInt() * it.groupValues[3].toInt()
            } else if ("'" in it.value) enabled = false
            else enabled = true
            0
        }
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInputTotal("day03/Day03_test")
    part1(input).println()
    part2(input).println()
}