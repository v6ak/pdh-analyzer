package v6;

public final class HtmlUtils {

	private HtmlUtils(){}
	
	public static String htmlSpecialChars(String in){
		if (in == null) {
			throw new NullPointerException("in must not be null");
		}
		final StringBuilder out = new StringBuilder();
		for (int i = 0; i < in.length(); i++) {
			final char c = in.charAt(i);
			switch (c) {
				case '<':
					out.append("&lt;");
					break;
				case '"':
					out.append("&quot;");
					break;
				default:
					out.append(c);
			}
		}
		return out.toString();
	}
	
}
