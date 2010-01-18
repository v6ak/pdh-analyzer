package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;

public interface PdhRecordElement<T> {

	public T parseValue(PdhRecord record, PdhStreamReader reader) throws IOException;
	
	public String convertValueToString(T value);

}
