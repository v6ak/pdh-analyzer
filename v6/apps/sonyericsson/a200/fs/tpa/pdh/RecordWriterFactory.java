package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.PrintWriter;

public interface RecordWriterFactory {

	public RecordWriter createRecordWriter(PrintWriter writer);
	
}
