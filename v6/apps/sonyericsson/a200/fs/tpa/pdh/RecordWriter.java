package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;

public interface RecordWriter {

	public void renderStart() throws IOException;
	
	public void renderEnd() throws IOException;
	
	public void renderRecord(PdhRecord record) throws IOException;
	
}
