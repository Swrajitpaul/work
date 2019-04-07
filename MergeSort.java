/**
 * MergeSort.java
 * @author swrajit paul
 * @version 1.0
 * CS 340 Fall 2019
 * 
 */
public class MergeSort <T extends Comparable <? super T>> extends Thread {
	
	private T[] v;
	private int startIndex;
	private int endIndex;
	
	public MergeSort(T[] arr, int st, int en) {
		v = arr;
		startIndex = st;
		endIndex = en;
	}
	
	public void run() {
		mergeSort(v, startIndex, endIndex);
	}
	
	public void mergeSort(T[] a, int start, int end) {
		
		if( start < end ) {
			int mid = (start + end)/2;
			MergeSort Left = new MergeSort(a, start, mid);
			Left.start();
			try {
				Left.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			MergeSort Right = new MergeSort(a, mid+1, end);
			Right.start();
			try {
				Right.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			merge(a, start, mid, end);
		}
		
	}
	
	public void merge(T A[ ] , int start, int mid, int end) {
		 //stores the starting position of both parts in temporary variables.
		int p = start ,q = mid+1;

		Integer[] Arr = new Integer[end-start+1]; 
		int k=0;

		for(int i = start ;i <= end ;i++) {
		    if(p > mid)      //checks if first part comes to an end or not .
		       Arr[ k++ ] = (int) A[ q++] ;

		   else if ( q > end)   //checks if second part comes to an end or not
		       Arr[ k++ ] = (int) A[ p++ ];

		   else if( A[ p ].compareTo( A[ q ]) < 0)     //checks which part has smaller element.
		      Arr[ k++ ] = (int) A[ p++ ];

		   else
		      Arr[ k++ ] = (int) A[ q++];
		 }
		  for (int p1=0 ; p1< k ;p1 ++) {
		     A[ start++ ] = (T) Arr[ p1 ] ;                          
		  }
		}
}
