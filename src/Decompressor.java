import java.io.IOException;
import java.io.InputStream;

public class Decompressor extends InputStream{

	InputStream input;
	
	public Decompressor(InputStream input) {
		this.input = input;
	}
	
	@Override
	public int read() throws IOException {
		return input.read();
	}

}
