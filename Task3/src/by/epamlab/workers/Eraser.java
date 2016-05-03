package by.epamlab.workers;

import java.util.Random;

public class Eraser implements Runnable {
	
	private StringBuffer canvas;
	private StringBuffer planningDraw;
	
	public Eraser(StringBuffer canvas, StringBuffer planningDraw) {
		this.canvas = canvas;
		this.planningDraw = planningDraw;
	}
	
	synchronized void erase() {
		while(Artist.isDrawing) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("... and after some bottle of beers he decide to erase some part --> " + canvas);
		
		Random r = new Random();
		
		/*
		 *  Detect safety indexes to erase a part of the canvas
		 *  Actually, left index can't be greater than right index, so check it
		 */
		int indToCutLeft 	= r.nextInt(canvas.length() / 2);
		int indToCutRight 	= r.nextInt(canvas.length());
		
		// swap them
		if (indToCutLeft > indToCutRight) {
			int tmp = indToCutLeft;
			
			indToCutLeft = indToCutRight;
			indToCutRight = tmp;
		}
		
		// Erase random part of the canvas
		canvas.delete(indToCutLeft, indToCutRight);
		
		System.out.println("He stopped and got the next result -> " + canvas);
		
		Artist.isDrawing = true;
		
		notifyAll();
	}

	@Override
	public void run() {
		while(planningDraw.length() != 0) {
			erase();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
