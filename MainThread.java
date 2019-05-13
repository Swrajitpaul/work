import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

public class MainThread extends Thread {
	
	private static int numTellers;
	private static int avgArrivalTime;
	private static int avgServiceTime;
	private static int simLength;
	private static int time = 0;
	private static int waitTime = 0;
	private static int numberOfCustomers = 1;
	private static ArrayList<Customer> customerList = new ArrayList<>();
	private static Semaphore semaphore;
	
	public MainThread() {};
	
	public MainThread(int numTellers, int avgArrivalTime, int avgServiceTime, int simLength ) {
		this.numTellers = numTellers;
		this.avgArrivalTime = avgArrivalTime;
		this.avgServiceTime = avgServiceTime;
		this.simLength = simLength;
		semaphore = new Semaphore(numTellers, true);
	}
	
	public synchronized int getTime() {
		return time;
	}
	
	public synchronized void setNumberOfCustomers(int customer) {
		numberOfCustomers = customer;
	}
	
	public synchronized int getNumberOfCustomers(int customer) {
		return numberOfCustomers;
	}
	
	public void setTime(int time) {
		waitTime = time;
	}
	
	public int getServiceTime() {
		return avgServiceTime;
	}
	
	public void run() {
		Random_Int_Mean randInt =  new Random_Int_Mean();
		boolean someThreadsAreAlive = true;
		while(time <= simLength || someThreadsAreAlive) {
			try {
				time += 10;
				Thread.sleep(Random_Int_Mean.random_int(avgArrivalTime/10)*1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
			if(time <= simLength) {
				Customer customer = new Customer(time, semaphore);
				customerList.add(customer);
				customer.start();
			}
			
			if(time >= simLength) {
				someThreadsAreAlive = false;
				for(Customer c : customerList) {
					try {
						if(!c.isAlive())
							c.join();
						if(c.isAlive())
							someThreadsAreAlive = true;
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		} 
		
		System.out.println();
		System.out.println("Simulation terminated after " + numberOfCustomers + " customers served");
		System.out.println("Average waiting time = " + waitTime/numberOfCustomers);
		
	}
	
}
