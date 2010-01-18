package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;

@Data
public class ConstantLengthStrategy implements LengthStrategy {

	@Getter(AccessLevel.NONE)
	private final int length;
	
	@Override
	public int getLength(PdhRecord record) {
		return length;
	}

}
