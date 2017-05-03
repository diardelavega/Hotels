package run.run;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import objects.HotelObj;
import operations.CompareTopic;
import operations.ReadHotelFiles;
import utils.Commons;

public class Demo {

	public static void main(String[] args) throws IOException {
		System.out.println("args: " + args);
		demoRun(args);
	}

	public static void demoRun(String[] args) throws IOException {
		Commons.init();// init semantic data structs
		args[0] = "C:/hotel";
		args[1] = "breakfast";
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
			ReadHotelFiles rhf ;

			List<HotelObj> hotelList = new ArrayList<>();
			for (String f : dataFolder.list()) {
				
				String fileName = folderPath + "/" + f;
				System.out.println(fileName );
				HotelObj hotel = new HotelObj();
				rhf = new ReadHotelFiles();
				if (rhf.readHotelData(fileName, topic) != -1) {

					hotel.setInfo(rhf.getHotelInfo());
					hotel.setAttributes(rhf.getAttributes());
					hotel.setTsr(rhf.getTopicResults());
					hotelList.add(hotel);
				}
			}

			// TODO sort by topicPoint
			// print results;
			// CompareTopic ct= new CompareTopic();
			for (int i = 0; i < hotelList.size(); i++) {
				System.out.printf("%s, points= %.3f \n", hotelList.get(i).getInfo().getName(),hotelList.get(i).getTsr().getTotalSentimentPoints());
			}
			System.out.println("-------------------");
			Collections.sort(hotelList, new CompareTopic());
			for (int i = 0; i < hotelList.size(); i++) {
				System.out.printf("%s, points= %.3f \n", hotelList.get(i).getInfo().getName(),hotelList.get(i).getTsr().getTotalSentimentPoints());
			}
		}

	}
}
