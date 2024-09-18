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


TestList.java
1. Does using a LinkedList make any difference?


2. What does list.remove(5) do?
The functional result of this method is the same for both the ArrayList and LinkedList implementations. This method simply
removes the element at index 5. In the ArrayList implementation, this method removes the element at index 5 and shuffles the rest of the list into the remaining
space accordingly. In the LinkedList implementation, this method removes the element at index 5 and adjust pointers from the previous and next element accordingly.
For this operation, the LinkedList implementation is faster. There are two operations at work here - accessing the element and deleting the element. For accessing the list, the ArrayList
implementation would be faster and preferred, however, the bottleneck occurs in the actual deletion of the element. For the ArrayList implementation, the worst-case complexity would be O(n) since if we deleted the first element
instead, the entire list would need to be reshuffled, whereas in the LinkedList implementation we would only need to move pointers, resulting in a worst-case complexity of O(1) or constant time for this operation.

3. What does list.remove(Integer.valueOf(5)) do?
The functional result of this method is the same for both the ArrayList and LinkedList implementations. This method simply
removes the first occurrence of the element containing an integer with the value of 5.
In the ArrayList implementation, this method removes the first occurrence and shuffles the rest of the list into the remaining space accordingly.
In the LinkedList implementation, this method removes the first occurrence and adjusts pointers from the previous and next element accordingly.
For this operation, the LinkedList implementation is faster, since the worst-case complexity for the ArrayList implementation of this method would be O(n) (removing the first element would cause the entire list
to have to be rearranged), while in the case of the LinkedList implementation, the worst-case complexity would be O(1) or constant time since only the pointers would need to be rearranged.

4. Other Notes