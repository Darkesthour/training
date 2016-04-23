package by.epamlab.bean;

import java.util.logging.Level;
import java.util.logging.Logger;

// (1), (2) - Just a little bit modified this line for workable
public class Car implements Runnable {
	private static final long MAX_DISTANCE = 10000;
	public static String winner = null;

	// (1)
	Logger log = Logger.getLogger(this.getClass().getName());

	private long friction; 
	private long distance; 
	private String name;
	
	public Car(String name, long friction) {
		this.name = name;
		this.friction = friction;
	}

	@Override
	public void run() {
		try {
			while (distance < MAX_DISTANCE) {
				Thread.sleep(friction);
				distance += 100;
				log.info(name + " " + distance);
			}
			
			// Remember a winner
			if (distance == MAX_DISTANCE && winner == null) {
				winner = name;
			}
		} catch (InterruptedException e) {
			// (2)
			log.log(Level.WARNING, "Well, well. There is a cheater. " + name + " is disqualified!");
		}
	}
}