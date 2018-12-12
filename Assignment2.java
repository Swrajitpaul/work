import java.util.ArrayList;

/**
 * @author swrajit 
 *
 */
public class Assignment2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        
		Philosophers[] Phil = new Philosophers[5];
		DiningPhilosophers dp = new DiningPhilosophers(Phil);
		
		for (int i = 0 ; i < 5; i++) {
			Phil[i] = new Philosophers(dp, i);
		}
        
		for (int i = 0 ; i < 5; i++) {
			Phil[i].start();
		}
		
		
        try{
            while (true){
            	for (int i = 0 ; i < 5; i++) {
        			Phil[i].join();
        		}
            }
        }catch (InterruptedException e){
            e.printStackTrace();
        }


	}

}
