package challenges.mostafa._5_queue

import java.util.PriorityQueue

/**
Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane
and an integer k, return the k closest points to the origin (0, 0).

The distance between two points on the X-Y plane is the Euclidean distance
(i.e., √(x1 - x2)2 + (y1 - y2)2).

You may return the answer in any order. The answer is guaranteed to be
unique (except for the order that it is in).


Example 1:

Input: points = [[1,3],[-2,2]], k = 1
Output: [[-2,2]]
Explanation:
The distance between (1, 3) and the origin is sqrt(10).
The distance between (-2, 2) and the origin is sqrt(8).
Since sqrt(8) < sqrt(10), (-2, 2) is closer to the origin.
We only want the closest k = 1 points from the origin, so the answer is just [[-2,2]].
Example 2:

Input: points = [[3,3],[5,-1],[-2,4]], k = 2
Output: [[3,3],[-2,4]]
Explanation: The answer [[-2,4],[3,3]] would also be accepted.


Constraints:

1 <= k <= points.length <= 10^4
-10^4 <= xi, yi <= 10^4

https://leetcode.com/problems/k-closest-points-to-origin/
 */

internal object P2KClosestPointsToOrigin {

    private fun kClosest(points: Array<IntArray>, k: Int): Array<IntArray> {
        // Create a min-heap to store the (distance, x, y) tuples
        val minHeap = PriorityQueue<Triple<Int, Int, Int>> { a, b ->
            a.first - b.first
        }

        // Calculate the Euclidean distance for each point and add it to the min-heap
        for (point in points) {
            val distance = point[0] * point[0] + point[1] * point[1]
            minHeap.offer(Triple(distance, point[0], point[1]))
        }

        // Retrieve the k closest points from the min-heap
        val result = Array(k) { IntArray(2) }
        for (i in 0 until k) {
            val (_, x, y) = minHeap.poll()
            result[i] = intArrayOf(x, y)
        }

        return result
    }

    @JvmStatic
    fun main(args: Array<String>) {
        // Example 1
        var points = arrayOf(intArrayOf(1, 3), intArrayOf(-2, 2))
        var k = 1
        var result = kClosest(points, k)
        println(result.contentDeepToString()) // [[[-2, 2]]]

        // Example 2
        points = arrayOf(intArrayOf(3, 3), intArrayOf(5, -1), intArrayOf(-2, 4))
        k = 2
        result = kClosest(points, k)
        println(result.contentDeepToString()) // [[3, 3], [-2, 4]]
    }
}