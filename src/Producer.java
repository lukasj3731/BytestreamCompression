import java.io.IOException;
import java.io.InputStream;

public class Producer extends InputStream{

	private int reads = 0;
	
	public Producer() {
	}
	
	@Override
	public int read() throws IOException {	//produces 10 ones and then exits with -1
		if(reads < 10) {
			reads ++;
			return 1;
		}
		return -1;
	}
	
	public int bitsRead() {	//returns how many bits were produced
		return reads;
	}
}
