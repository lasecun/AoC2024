package day02

import println
import readInput

fun List<Int>.allIncreasingMax3() = windowed(2).fold(true) { acc, (a, b) ->
    acc && a < b && b - a <= 3
}

fun List<Int>.allDecreasingMax3() = windowed(2).fold(true) { acc, (a, b) ->
    acc && b < a && a - b <= 3
}

fun List<Int>.omit(index: Int): List<Int> = subList(0, index) + subList(index + 1, size)

fun main() {
    fun part1(input: List<String>): Int {
        val reports = input.map { it.split(" ").map(String::toInt) }
        return reports.count { nums ->
            if (nums[0] < nums[1]) {
                nums.allIncreasingMax3()
            } else {
                nums.allDecreasingMax3()
            }

        }
    }

    fun part2(input: List<String>): Int {
        val reports = input.map { it.split(" ").map(String::toInt) }
        return reports.count { nums ->
            nums.allIncreasingMax3() || nums.allDecreasingMax3() || nums.indices.any { i ->
                val omittedOne = nums.omit(i)
                omittedOne.allIncreasingMax3() || omittedOne.allDecreasingMax3()
            }

        }
    }

    val input = readInput("day02/Day02_test")
    part1(input).println()
    part2(input).println()
}

// Result 242