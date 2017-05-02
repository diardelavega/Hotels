package run.run;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import objects.HotelInfo;
import objects.HotelObj;
import operations.ReadHotelFiles;
import utils.Commons;

public class Demo {

	public static void main(String[] args) throws IOException {
		Commons.init();// init semantic data structs

		if (args.length > 0) {
			String folderPath = args[0];
			File dataFolder = new File(folderPath);
			if (!dataFolder.exists()) {
				throw new FileNotFoundException();
			} else if (!dataFolder.isDirectory()) {
				throw new FileNotFoundException("this is not a folder/directory");
			} else if (dataFolder.list().length == 0) {
				throw new FileNotFoundException("this folder is empty");
			}

			String topic = args[1];
			ReadHotelFiles rhf = new ReadHotelFiles();
			
			List<HotelObj> hotelList= new ArrayList<>();
			for (String f : dataFolder.list()) {
				String fileName = folderPath + "/" + f;
				HotelObj hotel = new HotelObj();
				rhf.readHotelData(fileName, topic);
				
				hotel.setInfo(rhf.getHotelInfo());
				hotel.setAttributes(rhf.getAttributes());
				hotel.setTopicInfo(rhf.getTopicMap());
				hotelList.add(hotel);
			}
			
			//TODO sort by topicPoint
			//print results;

		}

		// wordBreaks();
		// sentenceBreaks();
	}

	// private static String filemaker(String f) {
	// return folderPath + "/" + f;
	// }

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
			// markers.setCharAt(boundary, '^');
			System.out.println(tt.substring(f, e));
			f = e;
			e = breakIterator.next();
		}

	}
}
