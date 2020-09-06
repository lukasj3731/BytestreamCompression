import java.io.IOException;

public class BytestreamCompression {

	public static void main(String[] args) {
		Producer prd = new Producer();
		Compressor cpr = new Compressor(prd);
		Interceptor itc = new Interceptor(cpr);
		Decompressor dcp = new Decompressor(itc);
		Consumer con = new Consumer(dcp);
		
		try {
			con.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(con.bitsRead());
	}

}
