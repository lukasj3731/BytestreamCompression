import java.io.IOException;

public class BytestreamCompression {

	public static void main(String[] args) {
		Producer prd = new Producer();
		Compressor cpr = new Compressor(prd);
		Interceptor itc = new Interceptor(cpr);
		Decompressor dcp = new Decompressor(itc);
		Consumer con = new Consumer(dcp);
		
		boolean b=false;
		try {
			b = con.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(	"Produced Bits:\t\t"+prd.bitsRead()+
							"\nIntercepted Bits:\t"+itc.bitsRead()+
							"\nConsumed Bits:\t\t"+con.bitsRead()+
							"\nData Compressed:\t"+String.format("%.2f", ((prd.bitsRead()-itc.bitsRead())*100.0)/prd.bitsRead())+"%"+
							"\nTransmission Correct:\t"+b);
	}

}
