import java.text.NumberFormat;
import java.util.Arrays;

@SuppressWarnings({"rawtypes", "unchecked"})
public class SearchSortHomeworkDriver {

	public static double sortedness(Comparable[] array) {
		if (array.length<=1)
			return 1.0;
		else {				
			int sortedCounter = 0;
			for (int i=0; i < array.length-1; i++) {
				if (array[i].compareTo(array[i+1]) <= 0)
					sortedCounter++;				
			}	
			return sortedCounter*1.0/(array.length-1);
		}
	}

	private static int sortedCounter = 0;
	private static int lengthCounter = 0;
	
	public static double sortedness(Node<Comparable> node) {
		sortedCounter = 0; //reset static level variables
		lengthCounter = 0; //reset static level variables
		if (node == null || node.next == null) { //handles null and singleton
			return 1.0;
		}
		else //2 or more nodes
			sortednessNodeHelper(node);
			return sortedCounter*1.0/lengthCounter;
	}
	
	private static void sortednessNodeHelper(Node<Comparable> node) {	
		 if (node.next != null) {
			 lengthCounter++;
			 if ((node.data).compareTo(node.next.data) <= 0) {
					sortedCounter++;
					sortednessNodeHelper(node.next);				
				}
			 else 
				sortednessNodeHelper(node.next);
			}				 
		}
	
/*
 space = 8/2 = 4 -> 5

begin = 0
[30, -, -, -, 10, -, -, -] becomes [10, -, -, -, 30, -, -, -]
current contents of array: [10, 20, 24, 16, 30, 25, 12, 14]


begin = 1
[-, 20, -, -, -, -, 12, -] becomes [-, 12, -, -, -, -, 20, -] 
current contents of array: [10, 12, 24, 16, 30, 25, 20, 14]


begin = 2
[-, -, 24, -, -, - -, 14] becomes [-, -, 14, -, -, - -, 24]
current contents of array: [10, 12, 14, 16, 30, 25, 20, 24]


begin = 3
[-, -, -, 16, -, -, -, -] becomes [-, -, -, 16, -, -, -, -]
current contents of array: [10, 12, 14, 16, 30, 25, 20, 24]

begin = 4
[-, -, -, -, 30, -, -, -] becomes [-, -, -, -, 30, -, -, -]
current contents of array: [10, 12, 14, 16, 30, 25, 20, 24]

no more swaps for this spacing.

space = 5/2 = 2.5 -> 2 -> 3


begin = 0
[10, -, -, 16, -, -, 20, -] becomes [10, -, -, 16, -, -, 20, -]
current contents of array: [10, 12, 14, 16, 30, 25, 20, 24]


begin = 1
[-, 12, -, -, 30, -, -, 24] becomes [-, 12, -, -, 24, -, -, 30]
current contents of array: [10, 12, 14, 16, 24, 25, 20, 30]


begin = 2
[-, -, 14, -, -, 25, -, -] becomes [-, -, 14, -, -, 25, -, -]
current contents of array: [10, 12, 14, 16, 24, 25, 20, 30]


no more swaps for this spacing

space = 3/2 = 1.5 -> 1
this is not insertion sort
[10, 12, 14, 16, 24, 25, 20, 30] becomes [10, 12, 14, 16, 20, 25, 24, 30]


[37, 23, 28, 21, 12, 34, 15, 19]

[37, 23, 28, 21]    [12, 34, 15, 19]

[37, 23] [28, 21]    [12, 34] [15, 19]

[37] [23] [28] [21]    [12] [34] [15] [19]

[23, 37] [21, 28]    [12, 34] [15, 19]

[21, 23, 28, 37]    [12, 15, 19, 34]

[12, 15, 19, 21, 23, 28, 34, 37]
 */
	
//	private static void sortednessNodeHelper(Node<Comparable> node, int sortedCounter, int lengthCounter) {
//		Node<Comparable> currentNode = node;
//		
//		while (currentNode != null) {//base case
//			if (currentNode.next == null) {
//				return sortedCounter*1.0/lengthCounter;
//			}
//			else if ((currentNode.data).compareTo(currentNode.next.data) <= 0) {
//				return sortednessNodeHelper(node.next, sortedCounter+1, lengthCounter+1);
//			}
//			else
//				return sortednessNodeHelper(node.next, sortedCounter, lengthCounter+1);
//		}
//	}

	private static boolean allTestsPassed = true;
	
	public static void main(String[] args) {

		
		System.out.println("-----------------------------Array Sortedness Tester-----------------------------");
		// parameter 1: the array
		// parameter 2: the expected sortedness, as a double (e.g., 1 means 100% sorted; 0.5 means 50% sorted)
		// parameter 3: a description of the test
		testArraySortedness(new Integer[]{1, 2, 3, 3, 5}, 		1,   "sorted odd length of integers");
		testArraySortedness(new Integer[]{1, 2, 3, 4, 5, 5}, 	1,   "sorted even length of integers");
		testArraySortedness(new Integer[]{10, 8, 5, 3, 1}, 		0,   "unsorted odd length of integers");
		testArraySortedness(new Integer[]{12, 11, 9, 3, 2, 1}, 	0,   "unsorted even length of integers");
		testArraySortedness(new Integer[]{2, 8, 3, 9, 6}, 		0.5, "partially sorted odd length of integers");
		testArraySortedness(new Integer[]{7, 1, 6, 8, 5, 2}, 	0.4, "partially sorted even length of integers");
		testArraySortedness(new String[]{"abc", "def", "ghi"}, 	1,   "sorted odd length of Strings");
		testArraySortedness(new String[]{"cat", "ant", "dog", "egg"}, 0.66667, "partially sorted even length of Strings");
		testArraySortedness(new String[]{"blue"}, 				1,   "singleton array");
		testArraySortedness(new String[]{}, 					1,   "empty array");

		System.out.println("\n-----------------------------Node Sortedness Tester-----------------------------");
		// parameter 1: the contents of the chain
		//              note: these are the same values as the arrays above
		//              note: the array {1, 2, 3} represents the chain 1->2->3
		// parameter 2: the expected sortedness, as a double (e.g., 1 means 100% sorted; 0.5 means 50% sorted)
		// parameter 3: a description of the test
		testLinkedNodeSortedness(new Integer[]{1, 2, 3, 3, 5}, 			1,   "sorted odd length of integers");
		testLinkedNodeSortedness(new Integer[]{1, 2, 3, 4, 5, 5}, 		1,   "sorted even length of integers");
		testLinkedNodeSortedness(new Integer[]{10, 8, 5, 3, 1}, 		0,   "unsorted odd length of integers");
		testLinkedNodeSortedness(new Integer[]{12, 11, 9, 3, 2, 1}, 	0,   "unsorted even length of integers");
		testLinkedNodeSortedness(new Integer[]{2, 8, 3, 9, 6}, 			0.5, "partially sorted odd length of integers");
		testLinkedNodeSortedness(new Integer[]{7, 1, 6, 8, 5, 2}, 		0.4, "partially sorted even length of integers");
		testLinkedNodeSortedness(new String[]{"abc", "def", "ghi"}, 	1,   "sorted odd length of Strings");
		testLinkedNodeSortedness(new String[]{"cat", "ant", "dog", "egg"}, 0.66667, "partially sorted even length of Strings");
		testLinkedNodeSortedness(new String[]{"blue"}, 					1,   "singleton array");
		testLinkedNodeSortedness(new String[]{}, 						1,   "empty array");
		
		System.out.println("\n\n-----------------------------TESTING COMPLETE-----------------------------");
		if(allTestsPassed) {
			System.out.println("----------Summary---------- \nAll automated tests have passed.\nBe sure to manually review your code for style and efficiency.");
		} else {
			System.out.flush();
			System.err.println("**********Summary********** ERROR: There is failure in at least one automated test. Review the output above for details.");
		}
	}


	
	/*----------------------------------------------------------------------------------------------------------*/
	/* TESTER METHODS */
	/*----------------------------------------------------------------------------------------------------------*/
	/*
	 * The methods below are designed to help support the tests cases run from main. You don't
	 * need to use, modify, or understand these methods. You can safely ignore them. :) 
	 * 
	 * Also, you can ignore the use of generics in the tester methods. These methods use
	 * generics at a level beyond which we use in our class. I only use them here to make this a robust 
	 * and useful testing file. You are NOT required to understand the use of generics in this way.
	 */

	public static <T extends Comparable<? super T>> void testArraySortedness(T[] array, double expectedSortedness, String testDescription) {
		NumberFormat formatter = NumberFormat.getPercentInstance();
		System.out.println("\nSortedness of " + Arrays.toString(array));
		double actualSortedness = sortedness(array);
		System.out.println("Expected sortedness = " + formatter.format(expectedSortedness));
		System.out.println("  Actual sortedness = " + formatter.format(actualSortedness));
		if(Math.abs(expectedSortedness - actualSortedness) > 0.00001) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: " + testDescription);
		}
	}

	public static <T extends Comparable<? super T>> void testLinkedNodeSortedness(T[] array, double expectedSortedness, String testDescription) {
		NumberFormat formatter = NumberFormat.getPercentInstance();
		Node<Comparable> chain = null;
		Node<Comparable> previous = null;
		for(int i=array.length-1; i>=0; i--) {
			Node<Comparable> node;
			if(i==array.length-1) {
				node = new Node<Comparable>(array[i]);
			} else {
				node = new Node<Comparable>(array[i], previous);
			}
			previous = node;
		}
		chain = previous;
		
		System.out.print("\nSortedness of ");
		for(int i=0; i<array.length; i++) {
			T value = array[i];
			if(i < array.length-1) {
				System.out.print(value + "->");
			} else {
				System.out.println(value);
			}
		}
		if(array.length==0) {
			System.out.println("<empty chain>");
		}
		double actualSortedness = sortedness(chain);
		System.out.println("Expected sortedness = " + formatter.format(expectedSortedness));
		System.out.println("  Actual sortedness = " + formatter.format(actualSortedness));
		if(Math.abs(expectedSortedness - actualSortedness) > 0.00001) {
			allTestsPassed = false;
			System.out.println("*****TEST FAILED: " + testDescription);
		}
	}
	
	
}