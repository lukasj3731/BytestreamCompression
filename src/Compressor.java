import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

public class Compressor extends InputStream{ //Huffman with variable length
	
	InputStream input;
	HuffmanFixedLength f = new HuffmanFixedLength();
	Queue<Boolean> buffer = new LinkedList<Boolean>();
	
	int PRIMERSIZE = 255;
	
	public Compressor(InputStream in) {		//first byte is length of primer, then primer is sent as is, then the compression starts
		addToBuffer((byte)PRIMERSIZE);
		input = in;
		byte[] primer = new byte[PRIMERSIZE];
		try {			//reads the first few bytes of the inputstream
			for(int i=0;i<PRIMERSIZE;i++) {	
				int tmp = input.read();
				if(tmp == -1) {
					throw new IOException();
				} else {
					primer[i] = (byte)tmp;
				}
			}
		} catch (IOException e) {
			System.out.println("message too short for compression");	//not enough primer
			e.printStackTrace();
		}
		prime(primer);
		addToBuffer(primer);
	}

	void prime(byte[] primer) {
		f.prime(primer);
	}
	
	boolean[] compress(byte input) {
		return f.compress(input);
	}

	@Override
	public int read() throws IOException {
		if(buffer.peek()==null) {	//nothing in buffer -> read then return
			int in = input.read();
			if(in == -1) {
				return -1;
			} else {
				addToBuffer(compress((byte)in));
				return buffer.poll()?1:0;
			}
		} else {
			return buffer.poll()?1:0;
		}
	}
	
	void addToBuffer(byte[] in) {
		for(int l=0;l<in.length;l++) {
			for(int i=7;i>=0;i--) {
				buffer.add((in[l]&(int)Math.pow(2,i))!=0);
			}
		}
	}
	
	void addToBuffer(boolean[] in) {
		for(int i=0;i<in.length;i++) {
			buffer.add(in[i]);
		}
	}
	
	void addToBuffer(byte in) {
		for(int i=7;i>=0;i--) {
			buffer.add((in&(int)Math.pow(2,i))!=0);
		}
	}
	
	void addToBuffer(boolean in) {
		buffer.add(in);
	}
	
}
