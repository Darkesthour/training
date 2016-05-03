package by.epamlab.workers;

public class Pencil implements Runnable {
	public static final int BUFFER_SIZE = 3;
	
	private StringBuffer canvas;
	private StringBuffer planningDraw;
	
	public Pencil(StringBuffer canvas, StringBuffer planningDraw) {
		this.canvas = canvas;
		this.planningDraw = planningDraw;
	}
	
	synchronized void draw() {
		while(!Artist.isDrawing) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// Detect available safety length of buffer to cut
		int ind = planningDraw.length() < BUFFER_SIZE ? planningDraw.length() : BUFFER_SIZE;
		
		// Save a part of drawing and put it to the canvas
		String drawing = planningDraw.substring(0, ind);
		
		canvas.append(drawing);
		
		// And delete drawing part
		planningDraw.delete(0, ind);
		
		System.out.println("\nArtist draw a new part --> " + canvas);
		
		Artist.isDrawing = false;
		
		notifyAll();
	}
	
	@Override
	public void run() {
		while(planningDraw.length() != 0) {
			draw();
			
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
