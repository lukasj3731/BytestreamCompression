import java.io.IOException;
import java.io.InputStream;

public class Consumer{

	private InputStream input;
	private InputStream comparison;
	private int reads = 0;
	
	public Consumer(InputStream input) {
		this.input = input;
		comparison = new Producer();
	}
	
	public boolean read() throws IOException {	//returns if Consumed Bits match Produced Bits
		boolean ret = true;
		int r=0;
		while((r=input.read()) != -1) {
			reads++;
			ret = r == comparison.read()?ret:false;
		}
		return ret;
	}
	
	public int bitsRead() {	//returns how many bits were consumed
		return reads;
	}
}
