import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

public class Decompressor extends InputStream {

	InputStream input;
	HuffmanFixedLength f = new HuffmanFixedLength();
	Queue<Boolean> buffer = new LinkedList<Boolean>();
	
	int j=0;
	
	public Decompressor(InputStream input) {
		this.input = input;
		int PRIMERSIZE=0;
		try {
			for(int i=0;i<8;i++) {
				PRIMERSIZE *= 2;
				PRIMERSIZE += input.read(); j++;
			}
			byte[] primer = new byte[PRIMERSIZE];
			for(int i=0;i<PRIMERSIZE;i++) {
				primer[i] = readByte();
			}
			f.prime(primer);
		} catch (IOException e) {
			System.out.println("input stream not supported");
			e.printStackTrace();
		}
	}
	
	private byte readByte() throws IOException {
		int ret = 0;
		for(int i=0;i<8;i++) {
			ret *= 2;
			ret += input.read(); j++;
			buffer.add(ret%2==1);
		}
		return (byte) ret;
	}
	
	@Override
	public int read() throws IOException {
		while(buffer.size()<8) {
			int in = input.read(); j++;
			if(in == -1) {
				return -1;
			}
			int dec = f.decompress(in==1);
			if(dec != -1) {
				for(int i=3;i>=0;i--) {
					buffer.add((dec & (int)Math.pow(2, i))>0);
				}
			}
		}
		
		int ret = 0;
		for(int i=0;i<8;i++) {
			ret *= 2;
			ret += (buffer.poll()?1:0);
		}
		return ret;
	}
}
