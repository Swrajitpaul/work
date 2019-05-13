public class Assignment2 {

	public static void main(String[] args) {
		int numberOfTellers = Integer.parseInt(args[0]);
		int meanArrivalTime = Integer.parseInt(args[1]);
		int meanServiceTime = Integer.parseInt(args[2]);
		int lengthOfSimulation = Integer.parseInt(args[3]);
		
		System.out.println("Mean inter-arrival time: " + meanArrivalTime);
		System.out.println("Mean service time: " + meanServiceTime);
		System.out.println("Number of tellers: " + numberOfTellers);
		System.out.println("Length of simulation: " + lengthOfSimulation);
		System.out.println();
		
		MainThread originalThread = new MainThread(numberOfTellers, meanArrivalTime, meanServiceTime, lengthOfSimulation);
		originalThread.start();
	}

}
