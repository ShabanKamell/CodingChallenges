/*
Given a string str consisting of lowercase letters only and an integer k,
the task is to replace every character of the given string by character whose ASCII value is k times more than it.
If ASCII value exceeds ‘z’, then start checking from ‘a’ in a cyclic manner.

Examples:

Input: str = “abc”, k = 2
Output: cde
a is moved by 2 times which results in character c
b is moved by 2 times which results in character d
c is moved by 2 times which results in character e

Input: str = “abc”, k = 28
Output: cde
a is moved 25 times, z is reached. Then 26th character will be a, 27-th b and 28-th c.
b is moved 24 times, z is reached. 28-th is d.
b is moved 23 times, z is reached. 28-th is e.

 */
object ReplaceCharactersWithBiggerAscii {

    private fun replace(input: String, _k: Int) {
        var k = _k
        var result = ""

        for (element in input) {
            val ascii = element.toInt()
            val duplicate = k

            if (ascii + k > 122) {
                k -= 122 - ascii
                k %= 26
                result += (96 + k).toChar()
            } else {
                result += (ascii + k).toChar()
            }
            k = duplicate
        }

        println(result)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        replace("abc", 2) // cde
        replace("abc", 28) // cde
        replace("def", 3) // ghi
    }
}

/*
Time Complexity: O(N), where N is the length of the string.
What is the disadvantage of using the ASCII value of the letters to solve this problem?
 It's easier to use ASCII for two reasons:
 1 - We need to check if the next character exceeds 'z' or not. In this case, we'll check using (ascii + k > 122).
 2 - We can get the the next char with simple operations: (96 + k).toChar(), (ascii + k).toChar().
 */
