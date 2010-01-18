package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;
import java.io.PrintWriter;

public class TextRecordWriterFactory {

	private static final RecordWriterFactory instance = new RecordWriterFactory(){
		@Override
		public RecordWriter createRecordWriter(final PrintWriter writer) {
			return new RecordWriter(){
				
				@Override
				public void renderEnd() throws IOException {
				}
				
				@Override
				public void renderRecord(PdhRecord record) throws IOException {
					writer.println(record);
				}
				
				@Override
				public void renderStart() throws IOException {
				}
				
			};
		}
	};
	
	public static RecordWriterFactory getInstance(){
		return instance;
	}
	
}
