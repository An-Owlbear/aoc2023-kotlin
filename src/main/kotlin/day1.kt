package com.anowlbear

val numbers = mapOf(
    "one" to "1",
    "two" to "2",
    "three" to "3",
    "four" to "4",
    "five" to "5",
    "six" to "6",
    "seven" to "7",
    "eight" to "8",
    "nine" to "9"
)

fun main() {
    val input = object {}.javaClass.getResourceAsStream("/day1.txt")?.bufferedReader()?.readLines()
    val result = input?.sumOf { line -> getFirstLastSum(line) }
    println(result)
}

fun getFirstLastSum(line: String): Int {
    val digits = getAsDigits(line)
    return (digits.first() + digits.last()).toInt()
}

fun getAsDigits(input: String): List<String> {
    if (input.isEmpty()) return emptyList()
    else {
        val returnList = mutableListOf<String>()

        if (input[0] in '1'..'9')
            returnList.addLast(input[0].toString())
        else {
            var foundMatch = false
            val keyIterator = numbers.keys.iterator()
            while (!foundMatch && keyIterator.hasNext()) {
                val key = keyIterator.next()
                if (input.startsWith(key)) {
                    foundMatch = true
                    returnList.addLast(numbers[key])
                }
            }
        }

        returnList += getAsDigits(input.substring(1))
        return returnList
    }
}