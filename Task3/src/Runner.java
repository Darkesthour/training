import by.epamlab.workers.Artist;


public class Runner {

	public static void main(String[] args) {
		// You can specify a text which will be drawing
		// See Artist class for more details
		
		(new Thread(new Artist())).start();
	}

}
