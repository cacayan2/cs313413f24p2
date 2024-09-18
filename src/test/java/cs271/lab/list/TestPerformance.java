package cs271.lab.list;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

// Added suppression to warnings for instantiation of arrays with generic/unchecked types.
// While this is not good coding in practice (it is preferable to store the lists to test in another list),
// this code exists for the sake of demonstration - the tests are able to pass and give meaningful results,
// and it would take much time to implement tests that utilize lists instead of arrays.
@SuppressWarnings({"unchecked", "rawtypes"})
public class TestPerformance {

  // (complete) TODO run test and record running times for SIZE = 10, 100, 1000, 10000, ...
  // (choose in conjunction with REPS below up to an upper limit where the clock
  // running time is in the tens of seconds)
  // (complete) TODO (optional) refactor to DRY
  // which of the two lists performs better as the size increases?
  // Changed SIZE to be an array of different values for different testing parameters.
  private final int[] SIZE = {10, 100, 1000, 10000, 100000};

  // (complete) TODO choose this value in such a way that you can observe an actual effect
  // for increasing problem sizes
  private final int REPS = 1000000;

  // Creation of an array of lists (the elements are generic type lists, warnings are generated but are suppressed
  // - see above comments for further explanation) which store lists of differing sizes.
  private List<Integer>[] arrayList = new List[SIZE.length];

  private List<Integer>[] linkedList = new List[SIZE.length];

  // Populates the array of lists with new <Integer> lists.
  @Before
  public void setUp() throws Exception {
    for(var i = 0; i < SIZE.length; i++) {
      arrayList[i] = new ArrayList<Integer>();
      linkedList[i] = new LinkedList<Integer>();
      for(var j = 0; j < SIZE[i]; j++) {
        arrayList[i].add(j);
        linkedList[i].add(j);
      }
    }
  }

  // Kept the original code.
  @After
  public void tearDown() throws Exception {
    arrayList = null;
    linkedList = null;
  }

  // The following tests were changed in order to allow for added functionality:
  // - The test now records and prints out its runtime cleanly.
  // - The test reiterates until it runs through all the elements of the SIZE array.
  // Documentation will only exist in this test, as the remaining tests generally have the same structure.
  @Test
  public void testLinkedListAddRemove() {
    // Arrays to record runtimes of the different tests.
    long[] start = new long[SIZE.length];
    long[] end = new long[SIZE.length];
    long[] runtime = new long[SIZE.length];
    // Runs the original code for each of the indices of the SIZE array.
    for(var i = 0; i < SIZE.length; i++) {
      start[i] = System.currentTimeMillis();
      // The original code for the test, modified to index through the array of lists.
      for (var r = 0; r < REPS; r++) {
        linkedList[i].add(0, 77);
        linkedList[i].remove(0);
      }
      end[i] = System.currentTimeMillis();
    }
    // Prints out runtimes of the tests.
    System.out.println("Results for testLinkedListAddRemove()");
    for(var s = 0; s < runtime.length; s++){
      System.out.println("SIZE = " + SIZE[s] + ": " + (end[s] - start[s]) + " ms");
    }
    System.out.println("-----");
  }

  @Test
  public void testArrayListAddRemove() {
    long[] start = new long[SIZE.length];
    long[] end = new long[SIZE.length];
    long[] runtime = new long[SIZE.length];
    for(var i = 0; i < SIZE.length; i++) {
      start[i] = System.currentTimeMillis();
      for(var r = 0; r < REPS; r++) {
        arrayList[i].add(0, 77);
        arrayList[i].remove(0);
      }
      end[i] = System.currentTimeMillis();
    }
    System.out.println("Results for testArrayListAddRemove()");
    for(var s = 0; s < runtime.length; s++) {
      System.out.println("SIZE = " + SIZE[s] + ": " + (end[s] - start[s]) + " ms");
    }
    System.out.println("-----");
  }

  @Test
  public void testLinkedListAccess() {
    long[] start = new long[SIZE.length];
    long[] end = new long[SIZE.length];
    long[] runtime = new long[SIZE.length];
    for(var i = 0; i < SIZE.length; i++) {
      start[i] = System.currentTimeMillis();
      var sum = 0L;
      for(var r = 0; r < REPS; r++) {
        sum += linkedList[i].get(r % SIZE[i]);
      }
      end[i] = System.currentTimeMillis();
    }
    System.out.println("Results for testLinkedListAccess()");
    for(var s = 0; s < runtime.length; s++) {
      System.out.println("SIZE = " + SIZE[s] + ": " + (end[s] - start[s]) + " ms");
    }
    System.out.println("-----");
  }

  @Test
  public void testArrayListAccess() {
    long[] start = new long[SIZE.length];
    long[] end = new long[SIZE.length];
    long[] runtime = new long[SIZE.length];
    for(var i = 0; i < SIZE.length; i++) {
      start[i] = System.currentTimeMillis();
      var sum = 0L;
      for(var r = 0; r < REPS; r++) {
        sum += arrayList[i].get(r % SIZE[i]);
      }
      end[i] = System.currentTimeMillis();
    }
    System.out.println("Results for testArrayListAccess()");
    for(var s = 0; s < runtime.length; s++) {
      System.out.println("SIZE = " + SIZE[s] + ": " + (end[s] - start[s]) + " ms");
    }
    System.out.println("-----");
  }
}

