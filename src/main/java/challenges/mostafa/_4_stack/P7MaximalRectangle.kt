package challenges.mostafa._4_stack

import java.util.Stack

/**
Given a rows x cols binary matrix filled with 0's and 1's, find the largest rectangle containing
only 1's and return its area.

Example 1:
Input: matrix = [
["1","0","1","0","0"],
["1","0","1","1","1"],
["1","1","1","1","1"],
["1","0","0","1","0"]]
Output: 6
Explanation: The maximal rectangle is shown in the above picture.

Example 2:

Input: matrix = [["0"]]
Output: 0

Example 3:

Input: matrix = [["1"]]
Output: 1


Constraints:

rows == matrix.length
cols == matrix[i].length
1 <= row, cols <= 200
matrix[i][j] is '0' or '1'.

https://leetcode.com/problems/maximal-rectangle/description/
 */

internal object P7MaximalRectangle {
    private fun maximalRectangle(matrix: Array<CharArray>): Int {
        if (matrix.isEmpty() || matrix[0].isEmpty()) return 0

        val rows = matrix.size
        val cols = matrix[0].size
        var maxArea = 0

        // Create a histogram-like array to store the height of each column
        val heights = IntArray(cols) { 0 }

        for (r in 0 until rows) {
            for (c in 0 until cols) {
                if (matrix[r][c] == '0') {
                    heights[c] = 0
                } else {
                    heights[c]++
                }
            }

            maxArea = maxOf(maxArea, largestRectangleInHistogram(heights))
        }

        return maxArea
    }

    private fun largestRectangleInHistogram(heights: IntArray): Int {
        val stack = ArrayDeque<Int>()
        var maxArea = 0

        heights.forEachIndexed { index, height ->
            while (stack.isNotEmpty() && heights[stack.last()] > height) {
                val lastHeight = heights[stack.removeLast()]
                val width = if (stack.isEmpty()) index else index - stack.last() - 1
                maxArea = maxOf(maxArea, lastHeight * width)
            }
            stack.addLast(index)
        }

        while (stack.isNotEmpty()) {
            val lastHeight = heights[stack.removeLast()]
            val width = if (stack.isEmpty()) heights.size else heights.size - stack.last() - 1
            maxArea = maxOf(maxArea, lastHeight * width)
        }

        return maxArea
    }

    @JvmStatic
    fun main(args: Array<String>) {
        var matrix = arrayOf(
            charArrayOf('1', '0', '1', '0', '0'),
            charArrayOf('1', '0', '1', '1', '1'),
            charArrayOf('1', '1', '1', '1', '1'),
            charArrayOf('1', '0', '0', '1', '0')
        )

        val result = maximalRectangle(matrix)
        println("The largest rectangle containing only 1's has an area of $result")

        matrix = arrayOf(
            charArrayOf('0')
        )
        val result2 = maximalRectangle(matrix)
        println("The largest rectangle containing only 1's has an area of $result2")

        matrix = arrayOf(
            charArrayOf('1')
        )
        val result3 = maximalRectangle(matrix)
        println("The largest rectangle containing only 1's has an area of $result3")
    }
}