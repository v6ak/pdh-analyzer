package v6.apps.sonyericsson.a200.fs.tpa.pdh;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;

import lombok.SneakyThrows;

public abstract class AutoPdhRecord extends PdhRecord{

	public AutoPdhRecord(PdhStreamReader reader) throws IOException {
		super(reader);
	}

	@Override
	@SneakyThrows(IllegalAccessException.class)
	public List<MapEntry> getElements() {
		final List<MapEntry> out = new LinkedList<MapEntry>();
		for (final Field field : getClass().getFields()) {
			out.add(new MapEntry((PdhRecordElement<?>) field.get(this), field.getName()));
		}
		return out;
	}
	
}
