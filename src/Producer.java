import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Producer extends InputStream{

	private int reads = 0;
	boolean[] file;
	
	public Producer() {
		file = readFile();
	}
	
	boolean[] readFile() {	//reads the file from Compression_Files and puts it in byte array
		File file = new File("Compression_Files/f.tiff");
		//init array with file length
		byte[] byteArray = new byte[(int) file.length()];
		try {
			FileInputStream fis = new FileInputStream(file);
			fis.read(byteArray);
			fis.close();
		} catch (IOException e) {
			e.printStackTrace();
		} //read file into bytes[]
		
		return toBitArray(byteArray);
	}
	
	boolean[] toBitArray(byte[] in) {	//TODO @Melli
		return new boolean[10];
	}
	
	@Override
	public int read() throws IOException {	//gives back contents of the file
		if(reads >= file.length) {
			return -1;
		} else {
			return (file[reads++])?1:0;
		}
	}
	
	public int bitsRead() {	//returns how many bits were produced
		return reads;
	}
}
