package day3
import java.io.File
import java.util.PriorityQueue


val nums = Array<String>(10){ it.toString() }.toSet()


fun exercise1 ()
{
    val filename = "./inputs/day3ex1.txt"
    var total: Int = 0
    val allLines = File(filename).readLines().toList()

    for ((row, line) in allLines.withIndex())
    {
        var cnum: String = ""
        var toAdd: Boolean = false

        for (col in 0..line.length - 1)
        {
            val x: String = line[col].toString()
            if (!nums.contains(x)) {
                if (toAdd)
                    total += cnum.toInt()
                cnum = ""
                toAdd = false
                continue
            }

            cnum = cnum.plus(line[col])

            extloop@ for (i in row-1..row+1)
            {
                if (i < 0 || i >= allLines.size) continue
                for (j in col-1..col+1)
                {
                    if (j < 0 || j >= line.length) continue
                    val str = allLines[i][j].toString()
                    if (str != "." && !nums.contains(str)) {
                        toAdd = true
                        break@extloop
                    }
                }
            }
        } // end line
        if (toAdd) {
            total += cnum.toInt()
        }
    }
    println("Total: ${total}")
}

data class Symbol(val name: String, val row: Int, val col: Int)

fun exercise2 ()
{
    val filename = "./inputs/day3ex2.txt"
    var total: Int = 0
    val allLines = File(filename).readLines().toList()
    for ((row, line) in allLines.withIndex())
    {
        for (col in 0..line.length - 1)
        {
            if (line[col].toString() != "*") continue
            var neighbourNums = ArrayList<Int>()
            // Read the neighbourhood
            var explored = HashSet<Symbol>()
            var neighbours = ArrayList<Symbol>()
            for (i in row - 1..row + 1)
                for (j in col - 1..col + 1)
                    if (nums.contains(allLines[i][j].toString())) {
                        neighbours.add(Symbol(name = allLines[i][j].toString(), row = i, col = j))
                    }

            if (neighbours.size == 0) continue

            for (neighbour in neighbours)
            {
                if (explored.contains(neighbour)) continue
                var cnum: String = neighbour.name
                // Left numbers
                var pointer: Symbol = neighbour
                while (nums.contains(pointer.name) && pointer.col > 0)
                {
                    pointer = Symbol(
                        name=allLines[pointer.row][pointer.col - 1].toString(),
                        row=pointer.row,
                        col=pointer.col - 1
                    )
                    if (nums.contains(pointer.name)) {
                        cnum = pointer.name.plus(cnum)
                        explored.add(pointer)
                    }
                }

                // RIGHT numbers
                pointer = neighbour
                while (nums.contains(pointer.name) && pointer.col < line.length - 1)
                {
                    pointer = Symbol(
                        name=allLines[pointer.row][pointer.col + 1].toString(),
                        row=pointer.row,
                        col=pointer.col + 1
                    )
                    if (nums.contains(pointer.name)) {
                        cnum = cnum.plus(pointer.name)
                        explored.add(pointer)
                    }
                }

                neighbourNums.add(cnum.toInt())

            }

            if (neighbourNums.size < 2) continue
            total += neighbourNums.reduce { acc, i ->  acc * i }

        }
    }

    println("Total: ${total}")
}