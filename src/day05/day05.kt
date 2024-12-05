package template

import mapToInts
import println
import readInputTotal
import swap

fun main() {
    fun part1(input: String): Int {
        val (r,u) = input.split("\n\n").map { it.lines() }
        val rules = r.map { it.split("|").mapToInts() }
        val updates = u.map { it.split(",").mapToInts() }
        val result = updates.filter { update ->
            rules.filter { it.all { it in update } }.all { (a, b) ->
                update.indexOf(a) < update.indexOf(b)
            }
        }.sumOf { it[it.size / 2] }
        return result
    }

    fun part2(input: String): Int {
        val (r, u) = input.split("\n\n").map { it.lines() }
        val rules = r.map { it.split("|").mapToInts() }
        val updates = u.map { it.split(",").mapToInts() }

        val result = updates.filterNot { update ->
            rules.filter { it.all { it in update } }.all { (a, b) -> update.indexOf(a) < update.indexOf(b) }
        }.sumOf { update ->
            val new = update.toMutableList()
            for (i in 0..(new.size / 2)) {
                for (j in i..new.lastIndex) {
                    if (rules.any { (first, second) -> first == new[j] && second == new[i] }) new.swap(i, j)
                }
            }
            new[new.size / 2]
        }
        return result
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInputTotal("/day05/Day05_test")
    part1(input).println()
    part2(input).println()
}