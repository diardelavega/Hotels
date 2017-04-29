package run.run;

import java.io.IOException;
import java.text.BreakIterator;
import java.util.Locale;

import operations.ReadHotelFiles;

public class Demo {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		// System.out.println("AAAA");
		// ReadHotelFiles rhf = new ReadHotelFiles();
		// rhf.readHotelData();

		// wordBreaks();
		sentenceBreaks();
	}

	public static void wordBreaks() {
		Locale locale = Locale.UK;
		BreakIterator breakIterator = BreakIterator.getWordInstance(locale);
		String tt = "$95 - $97*";
		breakIterator.setText(tt);

		int start = breakIterator.first();
		for (int end = breakIterator.next(); end != BreakIterator.DONE; start = end, end = breakIterator.next()) {
			String ss = tt.substring(start, end);
			if (ss.length() == 1)
				continue;
			System.out.println(ss);
		}
	}

	public static void sentenceBreaks() {
		Locale locale = Locale.UK;
		BreakIterator breakIterator = BreakIterator.getSentenceInstance(locale);
		String tt = "Rooms were nice with refrigerator and microwave. The continental breakfast could have been better. I was able to print my boarding pass from their computer in the lobby and they did not charge a fee. Beds were very comfortable. They are right next to the on ramp that takes you to LAX, and yet you do not hear any freeway noise from the room. (which was nice!!)";
		breakIterator.setText(tt);
		int f = breakIterator.first();
		int e = breakIterator.next();

		while (e != BreakIterator.DONE) {
//			markers.setCharAt(boundary, '^');
			System.out.println(tt.substring(f,e));
			f=e;
			e= breakIterator.next();
		}

	}
}
