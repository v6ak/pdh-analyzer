package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;

import lombok.Data;
import lombok.NonNull;

@Data
public class BytesElement extends AbstractBytesElement {

	private final @NonNull LengthStrategy lengthStrategy;
	
	public BytesElement(LengthStrategy lengthStrategy){
		if (lengthStrategy == null) {
			throw new NullPointerException("lengthStrategy must not be null");
		}
		this.lengthStrategy = lengthStrategy;
	}
	
	public BytesElement(final int length){
		this(new ConstantLengthStrategy(length));
	}
	
	
	@Override
	public byte[] parseValue(PdhRecord record, PdhStreamReader reader) throws IOException {
		return reader.readBytes(lengthStrategy.getLength(record));
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
