package operations;

import java.util.Comparator;

import objects.HotelObj;

public class CompareTopic implements Comparator<HotelObj> {

	@Override
	public int compare(HotelObj o1, HotelObj o2) {

		return Float.compare(o1.getTsr().getTotalSentimentPoints(), o2.getTsr().getTotalSentimentPoints());
	}

}
