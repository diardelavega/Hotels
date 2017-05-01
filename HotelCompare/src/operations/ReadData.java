package operations;

import java.util.Map;

import objects.CountNValue;
import objects.HotelInfo;
import objects.TopicSentiment;

public interface ReadData {
	public HotelInfo getHotelInfo();

	public Map<String, CountNValue> getAttributes();

	public Map<String, TopicSentiment> getTopicMap();
}
