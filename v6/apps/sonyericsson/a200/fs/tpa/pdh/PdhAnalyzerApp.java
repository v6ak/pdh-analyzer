package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;

import lombok.Cleanup;
import v6.args.parser.ArgumentsException;
import v6.args.parser.ArgumentsManager;
import v6.args.parser.BooleanParser;
import v6.args.parser.Parameter;
import v6.args.parser.StringParser;
import v6.args.parser.Values;

public class PdhAnalyzerApp {


	public static void main(String[] args) throws ArgumentsException, IOException {
		final ArgumentsManager argumentsManager = new ArgumentsManager();
		final Parameter<String> fileNameParameter = argumentsManager.addOption('f', "file", new StringParser());
		final Parameter<Boolean> htmlOutputParameter = argumentsManager.addOption("html", new BooleanParser());
		//final Pa
		final Values values = argumentsManager.parse(args);
		final boolean html = values.get(htmlOutputParameter);
		final File file = new File(values.get(fileNameParameter));
		final @Cleanup PdhStreamReader input = new PdhStreamReader(new FileInputStream(file));
		final RecordWriterFactory writerFactory = html
			? HtmlRecordWriterFactory.getInstance()
			: TextRecordWriterFactory.getInstance();
		final PrintWriter writer = new PrintWriter(System.out);
		try{
			final RecordWriter recordWriter = writerFactory.createRecordWriter(writer);
			recordWriter.renderStart();
			while(input.hasNext()){
				final PdhRecord record = new Pdh004Record(input);
				recordWriter.renderRecord(record);
			}
			recordWriter.renderEnd();
		}finally{
			writer.flush();
		}
		
	}
	
	/*private static void printArray(int[] array){
		System.out.print(array.length+" [");
		for (final int i : array) {
			System.out.print(" "+Integer.toHexString(i));
		}
		System.out.println("]");
	}*
	
	private static String addZero(String to){
		return (to.length()>1)
			? to
			: "0"+to;
	}
	
	private static String toHexString(byte b){
		return addZero(Integer.toHexString((b < 0)
				? b+256
				: b
			));
	}*/
	
	/*private static void printArray(byte[] array){
		System.out.print(array.length+" [");
		for (final byte i : array) {
			System.out.print(" "+toHexString(i));
		}
		System.out.println("]");
	}*/
	
}
