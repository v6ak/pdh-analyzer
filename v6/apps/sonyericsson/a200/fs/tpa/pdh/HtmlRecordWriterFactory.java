package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import lombok.Getter;

import static v6.HtmlUtils.htmlSpecialChars;

public class HtmlRecordWriterFactory {

	@Getter
	private static final RecordWriterFactory instance = new RecordWriterFactory(){
		@Override
		public RecordWriter createRecordWriter(final PrintWriter writer) {
			return new RecordWriter(){
				
				private List<MapEntry> order;
				
				//private boolean has
				
				@Override
				public void renderStart() throws IOException {
					writer.write("<table>");
				}
				
				@Override
				public void renderRecord(PdhRecord record) throws IOException {
					if(order == null){
						order = record.getElements();
						writer.println("<tr>");
						for (final MapEntry entry : order) {
							writer.println("<th>"+htmlSpecialChars(entry.getValue()));
						}
					}
					writer.println("<tr>");
					for (final MapEntry entry : order) {
						final PdhRecordElement<?> key = entry.getKey();
						writer.println("<td>"+htmlSpecialChars(record.getFormattedValue((key))));
					}
					
				}
				
				@Override
				public void renderEnd() throws IOException {
					writer.write("</table>");
				}
				
			};
		}
	};
	
	/*public static RecordWriterFactory getInstance(){
		return instance;
	}*/
	
}
