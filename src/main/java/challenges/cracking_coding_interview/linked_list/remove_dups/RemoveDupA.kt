package challenges.cracking_coding_interview.linked_list.remove_dups

import challenges.data_structure.LinkedListNode
import challenges.util.AssortedMethods


/*
Given two strings, write a method to decide if one is a permutation of the other.

 */
object RemoveDupA {
    private fun deleteDups(n: LinkedListNode?) {
        var n: LinkedListNode? = n
        val set = HashSet<Int>()
        var previous: LinkedListNode? = null
        while (n != null) {
            if (!set.contains(n.data)) {
                set.add(n.data)
                previous = n
            } else {
                previous?.next = n.next
            }
            n = n.next
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
//        var first = LinkedListNode(0, null, null)
        var first = AssortedMethods.randomLinkedList(1000, 0, 2);
        val head: LinkedListNode = first
        var second: LinkedListNode = first
        for (i in 1..7) {
            second = LinkedListNode(i % 2, null, null)
            first.next = second
            second.prev = first
            first = second
        }
        println(head.printForward())
        deleteDups(head)
        println(head.printForward())
    }
}