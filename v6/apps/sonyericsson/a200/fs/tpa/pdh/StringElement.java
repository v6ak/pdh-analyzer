package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;

import lombok.ToString;

@ToString
public class StringElement implements PdhRecordElement<String> {

	@Override
	public String parseValue(PdhRecord record, PdhStreamReader reader) throws IOException {
		return reader.readString();
	}

	@Override
	public String convertValueToString(String value) {
		if (value == null) {
			throw new NullPointerException("value must not be null");
		}
		return value;
	}
	
}
