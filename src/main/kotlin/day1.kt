package day1
import java.io.File

fun exercise1 ()
{
    val filename: String = "./inputs/day1ex1.txt"
    val chars = Array<String>(10) { it.toString() }.toSet()
    var total: Int = 0
    for (line in File(filename).readLines())
    {
        var firstDigit: String = ""
        var lastDigit: String = ""
        for (i in 0 until line.length)
        {
            if (chars.contains(line[i].toString()))
            {
                if (firstDigit == "") {
                    firstDigit = line[i].toString()
                }
                lastDigit = line[i].toString()
            }
        }

        total += (firstDigit + lastDigit).toInt()

    }

    println("Total: ${total}")
}


fun exercise2 ()
{
    val filename: String = "./inputs/day1ex2.txt"
    val _digits = Array<String>(9) { (it + 1).toString() }
    val _wordDigits = arrayOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    var valmap = _wordDigits.zip((1..9)).toMap().toMutableMap()
    for (i in _digits) valmap[i] = i.toInt()
    var total: Int = 0

    for ((id, line) in File(filename).readLines().withIndex())
    {
        var firstDigit: Int? = null
        var lastDigit: Int? = null

        for (i in 0..line.length - 1)
        {
            for (j in i + 1..line.length)
            {
                val sub = line.substring(i, j)
                if (!valmap.contains(sub)) continue
                if (firstDigit == null) {
                    firstDigit = valmap[sub]
                }
                lastDigit = valmap[sub]
            }
        }
        total += (firstDigit!!.toString() + lastDigit!!.toString()).toInt()
    }
    println("Total: ${total}")
}