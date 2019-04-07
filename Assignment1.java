/**
 * Assignment1.java
 * @author swrajit
 * @version 1.0
 */
public class Assignment1 {
	
	public static void main(String[] args) {
		
		Integer[] array = new Integer[100];
		
		for(int i = 0; i < array.length; i++) {
			array[i] = (int) Math.floor(Math.random() * 10000) + 1;
		}
		

		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
		
		MergeSort mg = new MergeSort(array, 0, array.length-1);
		
		
		mg.start();
		/*
		try {
			mg.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
		System.out.println("-----------------");
		for(int i = 0; i < array.length; i++) {
			System.out.println(array[i]);
		}
	}
}
