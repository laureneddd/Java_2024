
class WaiterThread extends Thread {

	static int total = 0;

	public void run() {

		if (Thread.currentThread().getName().equals("abc1")) {
			for (int i = 0; i < 10; i++) {
		    
				total = total + i;
			}
			System.out.println("in abc1 run()" + total);
		}
		else {
			try {
				Thread.sleep(200);
				// abc1.join();
			} catch (InterruptedException ex) {
			}
			System.out.println("in abc2 run() : " + total);
		}
	}
}
public class MutilThreading {

	public static void main(String[] args) throws InterruptedException {

		WaiterThread abc1 = new WaiterThread();    
		WaiterThread abc2 = new WaiterThread();

		abc1.setName("abc1");
		abc2.setName("abc2");
		
		abc1.start();
		abc2.start();
		
	}
}