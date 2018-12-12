import java.util.Random;
public class Philosophers extends Thread{
    
	private DiningPhilosophers dp;
    private int PhilNumber;
    
    public Philosophers (DiningPhilosophers diningp, int i){
        dp = diningp;
        PhilNumber = i;
    }
    
    public void run(){
        while (true){
            
            waitPhil();
            dp.takeChopsticks(PhilNumber);
            waitPhil();
            dp.returnChopsticks(PhilNumber);
        }
    }
    
    public void waitPhil() {
        try{
            Random wait = new Random();
            int min = 1000, max = 3000; 
            int waitNum = wait.nextInt((max - min) + 1) + min;
            Thread.sleep(waitNum);
        }catch (InterruptedException e){}
    }
}

/*
 * import java.util.ArrayList;
import java.util.concurrent.locks.*;
public class Dserver implements DiningServer{
	
    ArrayList<Philosophers> philosopher = new ArrayList<>();
    public Lock lock = new ReentrantLock();
    private Condition eat = lock.newCondition();
    
    public void initialize(Philosophers[] phil){
        philosopher=phil;
    }
    //Signals that the philosopher is HUNGRY and wants to eat.
    
    public void takeChopsticks(int philNum){
        lock.lock();
        try {
            philosopher.get(philNum).state=State.HUNGRY;
            System.out.println("Philosopher "+philNum+" is HUNGRY");
            test(philNum);
            if (philosopher.get(philNum).state != State.EATING) eat.await(); //if the philosopher was not allowed to eat, wait
        }catch (InterruptedException e){}
        finally {
            lock.unlock();
        }
    }
    // After eating the philosopher must return the chopstick.
    public void returnChopsticks(int philNum){
        philosopher.get(philNum).state=State.THINKING;
        System.out.println("Philosopher "+philNum+" is THINKING");
        test((philNum+4)%5);
        test((philNum+1)%5);
        System.out.println();
    }
    // tests if the Philosopher [philNum] can eat or not.
    public void test(int philNum){
        if((philosopher.get((philNum+4)%5).state !=State.EATING)&&(philosopher.get(philNum).state==State.HUNGRY)&&(philosopher.get((philNum+1)%5).state !=State.EATING)){
            philosopher.get(philNum).state=State.EATING; //let philosopher eat
            System.out.println("Philosopher "+philNum+" is EATING");
            eat.signal(); //signal
        }
    }
}

*/