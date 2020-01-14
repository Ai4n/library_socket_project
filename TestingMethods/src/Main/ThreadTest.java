package Main;

public class ThreadTest extends Thread {

	private long n;
	private int number;

	public ThreadTest(int priority, int number, long n) {
		this.number = number;
		this.n = n;
		setPriority(priority);
	}

	public void run() {
		for(long i = 1; i <= n; i++) {
			if (n % i == 0) {
				System.out.println("Thread number " + number + ": " + i);
			}
		}
	}
	
}
