package v6.apps.sonyericsson.a200.fs.tpa.pdh;


public abstract class AbstractBytesElement implements PdhRecordElement<byte[]>{

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
	}
	
	@Override
	public final String convertValueToString(byte[] array) {
		final StringBuilder out = new StringBuilder("[");
		for (final byte i : array) {
			out.append(" "+toHexString(i));
		}
		out.append("]");
		return out.toString();
	}
	
	/*@Override
	public final boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	@Override
	public final int hashCode() {
		return super.hashCode();
	}*/
	
}
