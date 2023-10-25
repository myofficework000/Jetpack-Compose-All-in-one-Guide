package com.example.jetpack_compose_all_in_one

/* Given an array of integers, where all elements but one occur twice, find the unique element.

Example :- a = [1,2,3,4,3,2,1]
The unique element is 4.

### Function Description
Complete the lonelyinteger function in the editor below. lonelyinteger has the following parameter(s):

int a[n]: an array of integers

Returns
int: the element that occurs only once

Sample Input1 :- a = [1]
There is only one element in the array, thus it is unique.

Sample Input2 :- a = [1,1,2]
We have two 1's, and  2 is unique.

Sample Input3 :- a = [0 0 1 2 1]
We have two 1's, two 0's, and one 2.  2 is unique. */
class FindUniqueElements {
}

// a = [1,2,3,4,3,2,1]
fun finUniqueElements(intArray: IntArray): Int {
    var uniqueElement = -1

    if (intArray.isEmpty()) return -1
    if (intArray.size == 1) return intArray[0]

    for (i in intArray) {
        if (!intArray.contains(uniqueElement)) {
            uniqueElement = i
            break
        }
    }

    return uniqueElement
}


fun main() {
    finUniqueElements(intArrayOf(1, 2, 3, 4, 3, 2, 1))
}