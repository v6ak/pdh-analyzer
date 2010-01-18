package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;

public class Pdh004Record extends AutoPdhRecord{

	public Pdh004Record(PdhStreamReader reader) throws IOException {
		super(reader);
	}

	// ?? 02|01 ff ff ff ff 0f 00 ??
	// public static final PdhRecordElement<byte[]> unknownBytes1 = new BytesElement(0x09);
	
	// this is maybe dirty; TODO: analyze these bytes
	public static final PdhRecordElement<byte[]> unknownBytes8 = new DynamicBytesElement((byte)0xff);
	
	// ff ff ff 0f 00 ??
	public static final PdhRecordElement<byte[]> unknownBytes1 = new BytesElement(0x06);
	
	public static final PdhRecordElement<byte[]> unknownBytes7 = new BytesElement(new LengthStrategy(){
		@Override
		public int getLength(PdhRecord record) {
			return record.get(unknownBytes1)[1] == (byte)0x82
				? 2
				: 0;
		}
	});
	
	public static final PdhRecordElement<String> midletName = new StringElement();
	
	public static final PdhRecordElement<byte[]> unknownBytes2 = new BytesElement(0x15);
	
	public static final PdhRecordElement<String> version = new StringElement();
	
	public static final PdhRecordElement<String> vendor = new StringElement();
	
	public static final PdhRecordElement<String> certificateFile = new StringElement();
	
	public static final PdhRecordElement<String> applicationId = new StringElement();
	
	public static final PdhRecordElement<String> iconFile = new StringElement();
	
	public static final PdhRecordElement<String> jadFile = new StringElement();
	
	// "" or "h"
	public static final PdhRecordElement<String> unknownString1 = new StringElement();
	
	public static final PdhRecordElement<String> jarFile = new StringElement();
	
	public static final PdhRecordElement<byte[]> unknownBytes3 = new BytesElement(0x19);
	
	// 0 bytes or [ 00 68]
	public static final PdhRecordElement<byte[]> unknownBytes6 = new BytesElement(new ByteDependentLengthStrategy(unknownBytes3, 0x19-1));
	
	public static final PdhRecordElement<String> aotCacheFile = new StringElement();
	
	public static final PdhRecordElement<byte[]> unknownBytes4 = new BytesElement(0x1D);

	public static final PdhRecordElement<String> internetConnectionName = new StringElement();
	
	public static final PdhRecordElement<byte[]> unknownBytes5 = new BytesElement(0x1C);
	
}
