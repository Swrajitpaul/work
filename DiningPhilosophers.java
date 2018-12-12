import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class DiningPhilosophers implements DiningServer{
	 enum State { THINKING, HUNGRY, EATING };
	 State[] states = new State[5];
	 Condition[] self = new Condition[5];
	 public Lock lock = new ReentrantLock();
	 
	 public DiningPhilosophers(Philosophers[] phil){
		 for (int i = 0; i < 5; i++) {
			 self[i] = lock.newCondition();
			 states[i] = State.THINKING;
			 System.out.println("Philosopher "+ i + " is THINKING");
		 }
	 }

	public void takeChopsticks(int i){
		lock.lock();
		try {
			states[i] = State.HUNGRY;
			System.out.println("Philosopher "+ i +" is HUNGRY");
			 test(i);
			 if (states[i] != State.EATING)
				 self[i].await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			lock.unlock();
		}
	}
	 public void returnChopsticks(int i) {
		 states[i] = State.THINKING;
		 // test left and right neighbors
		 test((i + 4) % 5);
		 test((i + 1) % 5);
	 }
	
	 private void test(int i){
		 lock.lock();
		 if ((states[(i + 4) % 5] != State.EATING) &&
				 (states[i] == State.HUNGRY) &&
				 (states[(i + 1) % 5] != State.EATING)) {
					 states[i] = State.EATING;
					 System.out.println("Philosopher "+ i +" is EATING");
					 self[i].signal();
		 }
		 lock.unlock();
	 }
	       
}
