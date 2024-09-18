**TestIterator.java**
1. Does using a LinkedList make any difference?
Functionally, there is no difference between ArrayList and LinkedList - the implementation is change but the end result of using
either implementation of the list data structure is still the same. What changes is performance. When using an ArrayList, the tests finish in on average 70ms, while when
using a LinkedList, on average 15 ms (for the entire test suite). Because this test suite is truly only bottlnecked by the dynamic resizing of the list (using the list.add() method,
the iteration through the list will cost about the same for both implementations in terms of runtime complexity), the runtime of the completion of all tests
is a robust enough metric for performance.


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


**TestList.java**
1. Does using a LinkedList make any difference?
Running the entire test suite causes only a marginal difference in runtime, 13 ms in the LinkedList case and 11 ms in the ArrayList case, not enough to say with certainty
that the LinkedList outperforms the ArrayList. This is because each of the tests run have a combination of list access and dynamic resizing. The ArrayList is better for list access, while
the LinkedList is better for dynamic resizing. Because the test suite contains tests that have a combination of both, having the runtimes of each of the cases individually could help us elaborate further on
the performance of the two lists.
LinkedList: 13 ms
testSet - 5 ms
testAddMultiple - 0 ms
testRetainAll - 0 ms
testContainsAll - 1 ms
testAddMultiple2 - 1 ms
testRemoveObject - 0 ms
testSizeNonEmpty - 1 ms
testContains - 0 ms
testSizeEmpty - 1 ms
testAddAll - 0 ms
testRemoveAll - 2 ms
testSubList - 2 ms

ArrayList: 11 ms
testSet - 5 ms
testAddMultiple - 0 ms
testRetainAll - 0 ms
testContainsAll - 2 ms
testAddMultiple2 - 1 ms
testRemoveObject - 0 ms
testSizeNonEmpty - 0 ms
testContains - 1 ms
testSizeEmpty - 0 ms
testAddAll - 1 ms
testRemoveAll - 0 ms
testSubList - 1 ms

Even after getting a breakdown of the runtime for each of these lists, not much can be said about the performance difference between the two,
which could be a function of the tests not being robust/complex enough, or in the nature of the fact that the tests themselves do not test the lists specifically on their performance. It is of note that
functions that are generally associated with access to elements of the list perform better in the ArrayList implementation, and tests which largely involve dynamic resizing of the list perform better in the LinkedList implementation
(the effect size between the lists and runtimes of the individual size cannot be accurately measured because the time to complete the tests is so small).
These results are not meaningful, and within the context of these tests, either type of list implementation will produce similar performance results.

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

test2
