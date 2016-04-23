import by.epamlab.bean.Car;

public class Runner {
	
	static final int DISQUALIFY_AFTER = 5000;

	public static void main(String[] args) {
		
		Thread[] cars = {
				new Thread(new Car("Bugatti", 100)),
				new Thread(new Car("Ferrari", 135)),
				new Thread(new Car("Batmobile", 50)),
				new Thread(new Car("Mercedes McLaren", 101))
		};
		
		System.out.println("Ready! Set! Go!");
		
		for(Thread car: cars) {
			car.start();
		}
		
		try {
			Thread.sleep(DISQUALIFY_AFTER);
			
			// Disqualify some cars
			cars[2].interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*
		 * Let's assume Runner is race thread, so it's can't be finished early than
		 * car threads are finished. Therefore, blocked him by calling join() 
		 * on not terminated (which is disqualified) threads while car threads aren't finished
		 * */
		for(Thread car: cars) {
			if (car.getState() != Thread.State.TERMINATED) {
				try {
					car.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("The race is over!");
		System.out.println("Winner is " + Car.winner);
	}

}
