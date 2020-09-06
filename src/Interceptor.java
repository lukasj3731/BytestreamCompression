import java.io.IOException;
import java.io.InputStream;

public class Interceptor extends InputStream{

	private InputStream input;
	private int reads = 0;
	
	public Interceptor(InputStream input) {
		this.input = input;
	}
	
	@Override
	public int read() throws IOException {
		switch(input.read()) {
			case -1: return -1;
			case 0 : reads++; return 0;
			default: reads++; return 1;
		}
	}
	
	public int bitsRead() {	//returns how many bits were intercepted
		return reads;
	}

}
