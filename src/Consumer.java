import java.io.IOException;
import java.io.InputStream;

public class Consumer{

	private InputStream input;
	private int reads = 0;
	
	public Consumer(InputStream input) {
		this.input = input;
	}
	
	public void read() throws IOException {
		int r=0;
		while((r=input.read()) != -1) {
			reads++;
		}
	}
	
	public int bitsRead() {	//returns how many bits were consumed
		return reads;
	}
}
