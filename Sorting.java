//Lab Partners:

//Xiaoxiang "Steven" Liu
//xliu102@u.rochester.edu
//MW 6:15PM - 7:30PM

//Grant Yap
//gyap@u.rochester.edu
//MW 2:00 - 3:15PM

//Lab 6
//(Analysis of Sorting Algorithms)
//Due 0331


/******************************************************************************
 *  Compilation:  javac Sorting.java
 *  Execution:    java Sorting input.txt AlgorithmUsed
 *  Dependencies: StdOut.java In.java Stopwatch.java
 *  Data files:   http://algs4.cs.princeton.edu/14analysis/1Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/2Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/4Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/8Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/16Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/32Kints.txt
 *                http://algs4.cs.princeton.edu/14analysis/1Mints.txt
 *
 *  A program to play with various sorting algorithms. 
 *
 *
 *  Example run:
 *  % java Sorting 2Kints.txt  2
 *
 ******************************************************************************/
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Sorting {

	//Selection Sort
	//Code from https://www.geeksforgeeks.org/selection-sort/
	public static int[] selectionSort(int arr[]) 
    { 
        int n = arr.length; 
  
        // One by one move boundary of unsorted subarray 
        for (int i = 0; i < n-1; i++) 
        { 
            // Find the minimum element in unsorted array 
            int min_idx = i; 
            for (int j = i+1; j < n; j++) 
                if (arr[j] < arr[min_idx]) 
                    min_idx = j; 
  
            // Swap the found minimum element with the first 
            // element 
            int temp = arr[min_idx]; 
            arr[min_idx] = arr[i]; 
            arr[i] = temp; 
        } 
        return arr;
    } 
	
	//Bubble Sort
	//Code from https://www.geeksforgeeks.org/bubble-sort/
	public static int[] bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j+1]) 
                { 
                    // swap arr[j+1] and arr[i] 
                    int temp = arr[j]; 
                    arr[j] = arr[j+1]; 
                    arr[j+1] = temp; 
                } 
			}
		}
		return arr;
	}

	//Insertion Sort
	//Code from https://www.geeksforgeeks.org/insertion-sort/
	public static int[] insertionSort(int[] arr) {
		int temp, j;
		for (int i = 1; i < arr.length; i++) {
			temp = arr[i];
			j = i - 1;
			while (j >= 0 && arr[j] > temp) {
				arr[j + 1] = arr[j];
				j--;
			}
			arr[j + 1] = temp;
		}

		return arr;
	}

	//Merge Sort: merge and mergesort
	//Code from https://www.geeksforgeeks.org/merge-sort/
	public static void merge(int arr[], int l, int m, int r) 
    { 
        // Find sizes of two subarrays to be merged 
        int n1 = m - l + 1; 
        int n2 = r - m; 
  
        /* Create temp arrays */
        int L[] = new int [n1]; 
        int R[] = new int [n2]; 
  
        /*Copy data to temp arrays*/
        for (int i=0; i<n1; ++i) 
            L[i] = arr[l + i]; 
        for (int j=0; j<n2; ++j) 
            R[j] = arr[m + 1+ j]; 
  
  
        /* Merge the temp arrays */
  
        // Initial indexes of first and second subarrays 
        int i = 0, j = 0; 
  
        // Initial index of merged subarry array 
        int k = l; 
        while (i < n1 && j < n2) 
        { 
            if (L[i] <= R[j]) 
            { 
                arr[k] = L[i]; 
                i++; 
            } 
            else
            { 
                arr[k] = R[j]; 
                j++; 
            } 
            k++; 
        } 
  
        /* Copy remaining elements of L[] if any */
        while (i < n1) 
        { 
            arr[k] = L[i]; 
            i++; 
            k++; 
        } 
  
        /* Copy remaining elements of R[] if any */
        while (j < n2) 
        { 
            arr[k] = R[j]; 
            j++; 
            k++; 
        } 
    } 
    public static void mergeSort(int arr[], int l, int r) 
    { 
        if (l < r) 
        { 
            // Find the middle point 
            int m = (l+r)/2; 
  
            // Sort first and second halves 
            mergeSort(arr, l, m); 
            mergeSort(arr , m+1, r); 
  
            // Merge the sorted halves 
            merge(arr, l, m, r); 
        } 
    } 
    
    //Quick Sort: Sort, qsort, partition, findpivot, and swap
    //Code from book
    static int[] Sort(int[] A) {
    	  qsort(A, 0, A.length-1);
    	  return A;
    }
    static void qsort(int[] A, int i, int j) {      // Quicksort
    	  int pivotindex = findpivot(A, i, j); // Pick a pivot
    	  swap(A, pivotindex, j);       // Stick pivot at end
    	  // k will be the first position in the right subarray
    	  int k = partition(A, i-1, j, A[j]);
    	  swap(A, k, j);              // Put pivot in place
    	  if ((k-i) > 1) qsort(A, i, k-1);   // Sort left partition
    	  if ((j-k) > 1) qsort(A, k+1, j);   // Sort right partition
    }
    static int partition(int[] A, int l, int r, int pivot) {
    	  do {                 // Move bounds inward until they meet
    	    while (A[++l] < pivot);
    	    while ((r!=0) && (A[--r] > pivot));
    	    swap(A, l, r);       // Swap out-of-place values
    	  } while (l < r);              // Stop when they cross
    	  swap(A, l, r);         // Reverse last, wasted swap
    	  return l;      // Return first position in right partition
    } 	
    static int findpivot(int[] A, int i, int j)
    	  { return (i+j)/2; }
    public static void swap(int[] A, int p1, int p2) {
        int temp = A[p1];
    	A[p1] = A[p2];
    	A[p2] = temp;
    }
    
    //Method for generating file
    private static void genFile(int[] a, String output) {
		PrintWriter writer = null; 
		try {
			writer = new PrintWriter(output);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for(int i: a) {
			writer.println(i );
		}
		writer.close();
	}

    //Main method for reading input and carry on operations
    public static void main(String[] args)  { 
    	//First argument, file name
        In in = new In(args[0]);
        
		// a stores file input in an array
        int[] a = in.readAllInts();
        
        //Second argument, type of sort
        Integer sortType = new Integer(args[1]);

        // b contains sorted integers from a
        int[] b = a;
        Arrays.sort(b);
        
        // c contains all integers stored in reverse order 
        int[] c = new int[b.length];
        int j = 0;
        for(int i = b.length; i > 0; i--) {
        	//System.out.println(i);
        	c[j] = b[i - 1];
        	j++;
        }
        
        // d contains almost sorted array 
        int[] d = b;
        for(int i = 0; i < (0.1 * d.length); i++) {
        	int rand = StdRandom.uniform(d.length - 1);
        	int temp = d[i];
        	d[i] = d[rand];
        	d[rand] = temp;
        }
       
        //Existing code
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        String netID = "xliu102/gyap";
        String algorithmUsed;
        String arrayUsed;
        
        //The output: sorting type + array + Runtime + date + NetID + Input File name
        
        //Default sort
        if(sortType.equals(0)) {
        	algorithmUsed = "Arrays.sort()";
        	//Array a
        	arrayUsed = "a";
        	Stopwatch timer = new Stopwatch();
        	Arrays.sort(a);
            double time = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
            
            //Array b
        	arrayUsed = "b";
        	timer = new Stopwatch();
        	Arrays.sort(b);
            double time2 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time2, timeStamp, netID, args[0]);
            
            //Array c
        	arrayUsed = "c";
        	timer = new Stopwatch();
        	Arrays.sort(c);
            double time3 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time3, timeStamp, netID, args[0]);
            
            //Array d
        	arrayUsed = "d";
        	timer = new Stopwatch();
        	Arrays.sort(d);
            double time4 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time4, timeStamp, netID, args[0]);
            
        //Bubble Sort
        }else if(sortType.equals(1)) {
        	algorithmUsed = "Bubble Sort";
        	//Array a
        	arrayUsed = "a";
        	Stopwatch timer = new Stopwatch();
        	bubbleSort(a);
            double time = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
            
            //Array b
        	arrayUsed = "b";
        	timer = new Stopwatch();
        	bubbleSort(b);
            double time2 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time2, timeStamp, netID, args[0]);
            
            //Array c
        	arrayUsed = "c";
        	timer = new Stopwatch();
        	bubbleSort(c);
            double time3 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time3, timeStamp, netID, args[0]);
            
            //Array d
        	arrayUsed = "d";
        	timer = new Stopwatch();
        	bubbleSort(d);
            double time4 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time4, timeStamp, netID, args[0]);
        
        //Selection Sort
        }else if(sortType.equals(2)) {
        	algorithmUsed = "Selection Sort";
        	//Array a
        	arrayUsed = "a";
        	Stopwatch timer = new Stopwatch();
        	selectionSort(a);
            double time = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
            
            //Array b
        	arrayUsed = "b";
        	timer = new Stopwatch();
        	selectionSort(b);
            double time2 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time2, timeStamp, netID, args[0]);
            
            //Array c
        	arrayUsed = "c";
        	timer = new Stopwatch();
        	selectionSort(c);
            double time3 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time3, timeStamp, netID, args[0]);
            
            //Array d
        	arrayUsed = "d";
        	timer = new Stopwatch();
        	selectionSort(d);
            double time4 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time4, timeStamp, netID, args[0]);
            
       	
        //Insertion Sort    
        }else if(sortType.equals(3)) {
        	algorithmUsed = "Insertion Sort";
        	//Array a
        	arrayUsed = "a";
        	Stopwatch timer = new Stopwatch();
        	insertionSort(a);
            double time = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
            
            //Array b
        	arrayUsed = "b";
        	timer = new Stopwatch();
        	insertionSort(b);
            double time2 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time2, timeStamp, netID, args[0]);
            
            //Array c
        	arrayUsed = "c";
        	timer = new Stopwatch();
        	insertionSort(c);
            double time3 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time3, timeStamp, netID, args[0]);
            
            //Array d
        	arrayUsed = "d";
        	timer = new Stopwatch();
        	insertionSort(d);
            double time4 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time4, timeStamp, netID, args[0]);
            
        //Merge Sort
        }else if(sortType.equals(4)) {
        	algorithmUsed = "Merge Sort";
        	//Array a
        	arrayUsed = "a";
        	Stopwatch timer = new Stopwatch();
        	mergeSort(a, 0, a.length - 1);
            double time = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
            
            //Array b
        	arrayUsed = "b";
        	timer = new Stopwatch();
        	mergeSort(b, 0, b.length - 1);
            double time2 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time2, timeStamp, netID, args[0]);
            
            //Array c
        	arrayUsed = "c";
        	timer = new Stopwatch();
        	mergeSort(c, 0, c.length - 1);
            double time3 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time3, timeStamp, netID, args[0]);
            
            //Array d
        	arrayUsed = "d";
        	timer = new Stopwatch();
        	mergeSort(d, 0, d.length - 1);
            double time4 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time4, timeStamp, netID, args[0]);
            
        //Quick Sort    
        }else if(sortType.equals(5)) {
        	algorithmUsed = "Quick Sort";
        	//Array a
        	arrayUsed = "a";
        	Stopwatch timer = new Stopwatch();
        	Sort(a);
            double time = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time, timeStamp, netID, args[0]);
            //Output file
            
            //Array b
        	arrayUsed = "b";
        	timer = new Stopwatch();
        	Sort(b);
            double time2 = timer.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time2, timeStamp, netID, args[0]);
            //Output file
            
            //Array c
        	arrayUsed = "c";
        	Stopwatch timer3 = new Stopwatch();
        	Sort(c);
            double time3 = timer3.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time3, timeStamp, netID, args[0]);
            //Output file
            
            //Array d
        	arrayUsed = "d";
        	Stopwatch timer4 = new Stopwatch();
        	Sort(d);
            double time4 = timer4.elapsedTimeMillis(); 
            StdOut.printf("%s %s %8.1f   %s  %s  %s\n", algorithmUsed, arrayUsed, time4, timeStamp, netID, args[0]);
        }
        
        //Generate a, b, c, d that contain the sorted output
        genFile(a, "a");
        genFile(b, "b");
        genFile(c, "c");
        genFile(d, "d");
        
        
        
  } 
}