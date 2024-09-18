TestIterator.java
1. Does using a LinkedList make any difference?
Functionally, there is no difference between ArrayList and LinkedList - the implementation is change but the end result of using
either implementation of the list data structure is still the same. What changes is performance. When using an ArrayList, the tests finish in on average 70ms, while when
using a LinkedList, on average 15 ms.

2. What happens if you use list.remove(Integer.valueOf(77))?
list.remove(Integer.valueOf(77)) only removes the first occurrence of an integer with the value 77 in the list.
This on its own is not sufficient to pass the assertions set later in the test (only non-77 values left in the test).
Luckily, alongside removing the element, the method returns TRUE if the method was able to successfully remove an element,
FALSE otherwise. We then don't need to iterate through the list, but keep removing elements using this method until the method returns false,
then we can exit the loop. The discrepancy in runtime comes from the operations we are doing on the list - mostly additions/deletions.
The operation of addition/deletion in an ArrayList is (worst case) O(n), as the elements need to be shifted in response to an addition or deletion.
In a linked list, pointers between nodes only need to be changed, and so the operation takes much less time. Because most of the operations performed in this
set of tests is addition/deletion, a LinkedList implementation is preferred to the ArrayList (but in the case where fast random access is preferred,
we would go with the ArrayList, however because the tasks in these unit tests focus more on resizing/insertion/deletion tasks, the LinkedList implementation is preferred).

3. Which values are left in the list after using assertEquals and List.of?
What is left is 33, 44, 55, and 66. 

4. What is the average (mean) of the values in the list (using an iterator and while loop)?


5. Other Notes

TestList.java
1. Does using a LinkedList make any difference?


2. Other Notes