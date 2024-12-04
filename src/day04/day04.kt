package template

import println
import readInput
import java.awt.Point

fun main() {
    fun part1(input: List<String>): Int {

        var count = 0L

        fun charAt(point: Point): Char? {
            return input.getOrNull(point.y)?.getOrNull(point.x)
        }

        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == 'X') {
                    val allDirections = listOf(
                        Point(1, -1),
                        Point(1, 0),
                        Point(1, 1),
                        Point(0, -1),
                        Point(0, 1),
                        Point(-1, -1),
                        Point(-1, 0),
                        Point(-1, 1),
                    )
                    val from = Point(x, y)

                    allDirections.map { direction ->
                        val charList = generateSequence(from) {
                            Point(it.x + direction.x, it.y + direction.y)
                        }.take(4)
                            .mapNotNull { charAt(it) }
                            .toList()
                        val result = charList.joinToString("")
                        //   println(result)
                        if (result == "XMAS") {
                            count++
                        }
                    }

                    allDirections.map {

                    }

                    listOf(Point(x, y), Point(x + 1, y), Point(x + 2, y), Point(x + 3, y))
                }
            }
        }

        return count.toInt()
    }

    fun part2(input: List<String>): Int {
        var count = 0L
        fun charAt(point: Point): Char? {
            return input.getOrNull(point.y)?.getOrNull(point.x)
        }
        input.forEachIndexed { y, line ->
            line.forEachIndexed { x, char ->
                if (char == 'A') {
                    val allDirections = listOf(
                        Point(1, -1),
                        Point(1, 1),
                        Point(-1, -1),
                        Point(-1, 1),
                    )
                    val from = Point(x, y)

                    val m = listOf(
                        listOf(Point(x - 1, y - 1), Point(x+1,y+1)),
                        listOf(Point(x - 1, y + 1), Point(x+1,y-1)),
                    ).all {
                        val s =  it.mapNotNull { charAt(it) }.joinToString("")
                        //    println(s)
                        s=="SM" || s=="MS"
                    }
                    if(m)count++


                    allDirections.map { direction ->
                        val charlist = generateSequence(from) {
                            Point(it.x + direction.x, it.y + direction.y)
                        }.take(2)
                            .mapNotNull { charAt(it) }
                            .toList()
                        val result = charlist.joinToString("")
                        //   println(result)
                        if (result == "MAS" || result == "SAM") {
                            //    count++
                        }
                    }

                    allDirections.map {

                    }

                    listOf(Point(x, y), Point(x + 1, y), Point(x + 2, y), Point(x + 3, y))
                }
            }
        }
        return count.toInt()
    }

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("day04/Day04_test")
    part1(input).println()
    part2(input).println()
}