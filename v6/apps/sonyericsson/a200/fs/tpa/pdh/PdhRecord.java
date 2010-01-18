package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


//@EqualsAndHashCode
public abstract class PdhRecord {

	//private List<PdhRecordElement<?>> elements = new ArrayList<PdhRecordElement<?>>();
	
	private Map<PdhRecordElement<?>, Object> values = new HashMap<PdhRecordElement<?>, Object>();
	
	private Map<PdhRecordElement<?>, String> names = new HashMap<PdhRecordElement<?>, String>();
	
	public PdhRecord(PdhStreamReader reader) throws IOException{
		if (reader == null) {
			throw new NullPointerException("reader must not be null");
		}
		for (final MapEntry entry : getElements()) {
			final PdhRecordElement<?> element = entry.getKey();
			names.put(entry.getKey(), entry.getValue());
			values.put(element, element.parseValue(this, reader));
			//System.err.println(this);
		}
	}
	
	public abstract List<MapEntry> getElements();
	
	@SuppressWarnings("unchecked")
	public <T>T get(PdhRecordElement<T> element){
		return (T) values.get(element);
	}
	
	@Override
	public String toString() {
		final StringBuilder out = new StringBuilder("{\n");
		for (final MapEntry entry : getElements()) {
			final PdhRecordElement<?> key = entry.getKey();
			out.append('\t');
			out.append(names.get(key));
			out.append(": ");
			out.append(getFormattedValue(key));
			out.append('\n');
		}
		/*for (final PdhRecordElement<?> key : values.keySet()) {
			out.append('\t');
			out.append(names.get(key));
			out.append(": ");
			out.append(getFormattedValue(key));
			out.append('\n');
		}*/
		out.append('}');
		return out.toString();
	}
	
	public <T> String getFormattedValue(PdhRecordElement<T> key) {
		if (key == null) {
			throw new NullPointerException("key must not be null");
		}
		final T value = get(key);
		return value == null
			? null
			: key.convertValueToString(value);
	}
	
	@Override
	public final boolean equals(Object obj) {
		if (obj instanceof PdhRecord) {
			final PdhRecord other = (PdhRecord) obj;
			return other.values.equals(this.values);
		} else {
			return false;
		}
	}
	
	@Override
	public final int hashCode() {
		return 3851+values.hashCode();
	}
	
}
