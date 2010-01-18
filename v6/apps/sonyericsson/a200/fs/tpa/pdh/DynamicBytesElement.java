package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class DynamicBytesElement extends AbstractBytesElement {

	@Getter(AccessLevel.NONE)
	private final byte endByte;

	@Override
	public byte[] parseValue(PdhRecord record, PdhStreamReader reader)
			throws IOException {
		final ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] bs;
		while( (bs=reader.readBytes(1))[0] != endByte ){
			out.write(bs);
		}
		return out.toByteArray();
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public int hashCode() {
		return super.hashCode();
	}
	
}
