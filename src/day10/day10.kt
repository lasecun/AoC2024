package day10

import println
import readInput
import kotlin.math.absoluteValue

fun main() {

    fun nextStep(input: List<String>, pos: Pair<Int, Int>, visitedNines: MutableList<Pair<Int, Int>>): Int {
        if (input[pos.second][pos.first] == '9') {
            if (!visitedNines.contains(pos)) visitedNines.add(pos)
            else return 0
            return 1
        }

        var count = 0
        val height = input[pos.second][pos.first].digitToInt()
        if (pos.first + 1 in input[0].indices && input[pos.second][pos.first + 1].digitToInt() == height + 1)
            count += nextStep(input, pos.first + 1 to pos.second, visitedNines)
        if (pos.first - 1 in input[0].indices && input[pos.second][pos.first - 1].digitToInt() == height + 1)
            count += nextStep(input, pos.first - 1 to pos.second, visitedNines)
        if (pos.second + 1 in input.indices && input[pos.second + 1][pos.first].digitToInt() == height + 1)
            count += nextStep(input, pos.first to pos.second + 1, visitedNines)
        if (pos.second - 1 in input.indices && input[pos.second - 1][pos.first].digitToInt() == height + 1)
            count += nextStep(input, pos.first to pos.second - 1, visitedNines)
        return count
    }

    fun part1(input: List<String>): Int {
        val trailHeads = input.mapIndexed { y, line -> line.mapIndexed { x, c -> if (c == '0') x to y else null } }
            .flatten().filterNotNull().toMutableList()
        return trailHeads.sumOf {
            val visitedNines = mutableListOf<Pair<Int, Int>>()
            nextStep(input, it, visitedNines)
        }
    }

    fun nextStep(input: List<String>, pos: Pair<Int, Int>): Int {
        if (input[pos.second][pos.first] == '9') {
            return 1
        }

        var count = 0
        val height = input[pos.second][pos.first].digitToInt()
        if (pos.first + 1 in input[0].indices && input[pos.second][pos.first + 1].digitToInt() == height + 1)
            count += nextStep(input, pos.first + 1 to pos.second)
        if (pos.first - 1 in input[0].indices && input[pos.second][pos.first - 1].digitToInt() == height + 1)
            count += nextStep(input, pos.first - 1 to pos.second)
        if (pos.second + 1 in input.indices && input[pos.second + 1][pos.first].digitToInt() == height + 1)
            count += nextStep(input, pos.first to pos.second + 1)
        if (pos.second - 1 in input.indices && input[pos.second - 1][pos.first].digitToInt() == height + 1)
            count += nextStep(input, pos.first to pos.second - 1)
        return count
    }

    fun part2(input: List<String>): Int {
        val trailHeads = input.mapIndexed { y, line -> line.mapIndexed { x, c -> if (c == '0') x to y else null } }
            .flatten().filterNotNull().toMutableList()
        return trailHeads.sumOf {
            nextStep(input, it)
        }
    }

    val input = readInput("day10/Day10_test")
    part1(input).println()
    part2(input).println()
}