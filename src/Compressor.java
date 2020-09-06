import java.io.IOException;
import java.io.InputStream;

public class Compressor extends InputStream{

	InputStream input;
	
	public Compressor(InputStream input) {
		this.input = input;
	}
	
	@Override
	public int read() throws IOException {
		return input.read();
	}
}
