package by.epamlab.workers;

public class Artist implements Runnable {
	
	public static boolean isDrawing = true;
	private static final String DEFAULT_DRAWING = "^^,-_-0_0V_V";
	
	private StringBuffer canvas;
	private StringBuffer planningDraw;
		
	private Eraser eraser;
	private Pencil pencil;
	
	public Artist(String drawing) {
		this.planningDraw = new StringBuffer(drawing);
		this.canvas = new StringBuffer("");
		this.pencil = new Pencil(canvas, planningDraw);
		this.eraser = new Eraser(canvas, planningDraw);
	}
	
	public Artist() {
		this(DEFAULT_DRAWING);
	}
	
	private void action() {
		Thread eraserThread = new Thread(eraser);
		Thread pencilThread = new Thread(pencil);
		
		pencilThread.start();
		eraserThread.start();
		
		try {
			pencilThread.join();
			eraserThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("Well, well. Seems Artist start to draw something unimaginable");
		
		action();
		
		System.out.println("\nSo, finally Artist has been finished his work. Oh, look at this -> " + canvas);
	}

}
