package com.anowlbear

const val max_red = 12
const val max_green = 13
const val max_blue = 14

data class Game(val id: Int, val draws: List<Draw>)
data class Draw(val red: Int, val green: Int, val blue: Int)

fun main() {
    val input = object {}.javaClass.getResourceAsStream("/day2.txt")?.bufferedReader()?.readLines()
        ?: throw RuntimeException("Invalid input file")

    val result = input
        .map { line -> parseGame(line) }
        .filter { game -> validateGame(game) }
        .sumOf { game -> game.id }

    println(result)
}

fun validateGame(game: Game): Boolean {
    for (draw in game.draws) {
        if (draw.red > max_red || draw.green > max_green || draw.blue > max_blue)
            return false
    }

    return true;
}

fun parseGame(line: String): Game {
    val matches = Regex("Game ([0-9]+): (.+)").find(line)
        ?: throw RuntimeException("Invalid line $line")
    val (id, drawsString) = matches.destructured
    val draws = drawsString.split(';').map { segment -> parseDraw(segment.trim()) }
    return Game(id.toInt(), draws)
}

fun parseDraw(drawString: String): Draw {
    val colours = mutableMapOf("red" to 0, "blue" to 0, "green" to 0)
    for (segment in drawString.split(",")) {
        val partMatches = Regex("([0-9]+) ([a-z]+)").find(segment.trim())
            ?: throw RuntimeException("Invalid string $segment")
        val (amount, colour) = partMatches.destructured
        colours[colour] = amount.toInt()
    }
    return Draw(colours.getValue("red"), colours.getValue("green"), colours.getValue("blue"))
}
