package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.Closeable;
import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Data
@EqualsAndHashCode(exclude="inputByte")
public final class PdhStreamReader implements Closeable{

	@Getter(AccessLevel.NONE)
	private final @NonNull InputStream inputStream;
	
	@Getter(AccessLevel.NONE)
	@Setter(AccessLevel.NONE)
	private int inputByte = -1;
	
	@Override
	public void close() throws IOException {
		inputStream.close();
	}
	
	private int read() throws IOException{
		if(inputByte != -1){
			try{
				return inputByte;
			}finally{
				inputByte = -1;
			}
		}
		return inputStream.read();
	}
	
	public byte[] readBytes(int length) throws IOException{
		//System.out.println("b: "+length);
		final byte[] bytes = new byte[length];
		for (int i = 0; i < bytes.length; i++) {
			final int value = read();
			if(value == -1){
				throw new EOFException();
			}
			bytes[i] = (byte) value;
		}
		return bytes;
	}
	
	public boolean hasNext() throws IOException{
		final int next = read();
		inputByte = next;
		return next != -1;
	}
	
	public String readString() throws IOException{
		final int lb1 = read();
		final int lb2 = read();
		if( (lb1==-1) || (lb2==-1)){
			throw new EOFException();
		}
		//final byte[] lengthBytes = readBytes(2);
		//final int length = (((int)lengthBytes[0]) << 8) | (((int)0)|lengthBytes[1]);
		final int length = lb1 <<8 | lb2;
		//System.out.print("[len:"+length+"] ");
		/*final int[] stringInts = readInts(length);
		final byte[] stringBytes = new byte[length];
		for (int i = 0; i < stringInts.length; i++) {
			stringBytes[i] = (byte) stringInts[i];
		}*/
		final byte[] stringBytes = readBytes(length);
		return new String(stringBytes, Charset.forName("UTF-16"));
	}
	
}
