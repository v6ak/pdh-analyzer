package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import lombok.Data;
import lombok.NonNull;

@Data
public class ByteDependentLengthStrategy implements LengthStrategy {

	private final @NonNull PdhRecordElement<byte[]> arrayElement;
	
	private final int pos;
	
	@Override
	public int getLength(PdhRecord record) {
		return record.get(arrayElement)[pos];
	}

}
