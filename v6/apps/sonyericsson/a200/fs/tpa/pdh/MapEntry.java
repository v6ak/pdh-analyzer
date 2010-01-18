package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import lombok.Data;

@Data
public final class MapEntry{//TODO: name

	private final PdhRecordElement<?> key;
	
	private final String value;
	
}
