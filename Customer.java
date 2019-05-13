import java.util.concurrent.Semaphore;

public class Customer extends Thread{
	MainThread m = new MainThread();
	private static int customerId = 1;
	private static int arrivalTime = 0;
	private final Semaphore semaphore;
	private static Random_Int_Mean rInt = new Random_Int_Mean();
		
	public Customer(int time, Semaphore s) {
			arrivalTime = time;
			semaphore = s;
		}
		
		public synchronized int getNextCustomer() {
			int cId = customerId;
			customerId++;
			return cId;
		}
		
		public void run() {
			
			int customer = getNextCustomer();
			System.out.println("At time "+ arrivalTime + ", customer " + customer +" arrives in line"
);
			
			try {
				
				semaphore.acquire();
				
				try {
					System.out.println("At time "+ m.getTime() + ", customer " + customer +" starts being served"
							);
					//
					System.out.println(m.getTime() - arrivalTime);
					m.setTime(m.getTime() - arrivalTime);
					Thread.sleep(Random_Int_Mean.random_int(m.getServiceTime()/10)*1000);
				} finally {
					System.out.println("At time "+ m.getTime() + ", customer " + customer +" leaves the bank"
							);
					semaphore.release();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
		}
	
}

