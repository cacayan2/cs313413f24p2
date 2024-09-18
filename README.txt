**TestIterator.java**
1. Does using a LinkedList make any difference?
Functionally, there is no difference between ArrayList and LinkedList - the implementation is change but the end result of using
either implementation of the list data structure is still the same. What changes is performance. When using an ArrayList, the tests finish in on average 70ms, while when
using a LinkedList, on average 15 ms (for the entire test suite). Because this test suite is truly only bottlnecked by the dynamic resizing of the list (using the list.add() method,
the iteration through the list will cost about the same for both implementations in terms of runtime complexity), the runtime of the completion of all tests
is a robust enough metric for performance. But behaviorally, there is no difference - the interface for both lists are the same and the only changes that needed to be made to the code were in the
creation of the List object (implementation does not matter).


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

In terms of behavior, both lists perform the same - they have identical interfaces, and so their usage through the test cases did not need to change (i.e. the code in the tests did not need to be changed
to account for the switch between an ArrayList or LinkedList).

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

**TestPerformance.java**
The value for REPS which allowed for observable effects as problem size increased is REPS = 1000000.

Note on refactoring to DRY: The process of changing test parameters and recording the runtime of each test manually is tedious. To solve this, the tests in TestPerformance.java werwe changed such that:
- SIZE is no longer a single integer value but an array of integer values that can be changed to fine tune the testing (rather than manually changing SIZE).
- The tests themselves iterate through SIZE and print out the runtimes of the original test code given the value of the element at some index for SIZE.

There is one major caveat to this code which has not been overcome in this project - in order to run the test, multiple versions of arrayList and linkedList have to be created
accommodating the different values within the SIZE array. This was achieved by storing the lists in an array of lists.
Because arrays do not support for the storage of type lists in Java, generic lists were stored in the array. The code still runs, but in practice this should be avoided.
Because this code exists for the sake of demonstration, this is overlooked, but again in practice is a large oversight of the non-functional requirements of this code.

1. Running times for different values of SIZE (these results were copied and pasted from the printed output of the tests):
Results for testArrayListAccess()
SIZE = 10: 19 ms
SIZE = 100: 20 ms
SIZE = 1000: 8 ms
SIZE = 10000: 8 ms
SIZE = 100000: 8 ms
-----
Results for testLinkedListAddRemove()
SIZE = 10: 38 ms
SIZE = 100: 55 ms
SIZE = 1000: 39 ms
SIZE = 10000: 29 ms
SIZE = 100000: 32 ms
-----
Results for testLinkedListAccess()
SIZE = 10: 26 ms
SIZE = 100: 47 ms
SIZE = 1000: 362 ms
SIZE = 10000: 6548 ms
SIZE = 100000: 91498 ms
-----
Results for testArrayListAddRemove()
SIZE = 10: 108 ms
SIZE = 100: 96 ms
SIZE = 1000: 174 ms
SIZE = 10000: 1474 ms
SIZE = 100000: 21649 ms
-----

In general, we see the trend that ArrayListAccess() generally does not change (and in fact decreases) with
the size of the list involved. This is because ArrayListAccess() involves accessing an ArrayList, in which access is done through
indices, which has O(1) runtime complexity (just accessing the index). We see that runtime testLinkedListAccess() increases dramatically with
list size, and this is because accessing a specific index in a linked list is a linear process (each node in the list must be iterated through) with a worst-case
runtime complexity of O(n).

We also see the same trend for testLinkedListAddRemove(). While we don't note the decrease in runtime with an increase in size,
we see that runtime is generally unaffected by the size of the list. LinkedLists are ideal for dynamic resizing, and because adding/deleting members
of a linked list only involves moving pointers around (regardless of the size of the list), the worst-case runtime complexity for this operation is O(1).
We again see the opposite is true for testArrayListAddRemove(), and this is because whenever an index is removed/added, the entire list needs to be shifted element by element to fill
the empty space. This causes a dramatic increase in runtime as size of the list increases, with a worst-case runtime complexity of O(n), as potentially every
element of the list might need to be shifted.