package objects;

/**
 * @author Administrator
 *
 *         In case a topic is found keep track of the topic, the sentiment
 *         expressed in it according to the semantic definitions (from the
 *         file), the sentence in which the topic is found and information about
 *         the review where it was written.
 */
public class TopicSentiment {

	private String topic;
	private float sentimentPoint;
	private String sentence;
	private ReviewInfo reviewAuthor;

	public TopicSentiment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TopicSentiment(String topic, float sentimentPoint, String sentence, ReviewInfo reviewAuthor) {
		super();
		this.topic = topic;
		this.sentimentPoint = sentimentPoint;
		this.sentence = sentence;
		this.reviewAuthor = reviewAuthor;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public double getSentimentPoint() {
		return sentimentPoint;
	}

	public void setSentimentPoint(float sentimentPoint) {
		this.sentimentPoint = sentimentPoint;
	}

	public String getSentence() {
		return sentence;
	}

	public void setSentence(String sentence) {
		this.sentence = sentence;
	}

	public ReviewInfo getReviewAuthor() {
		return reviewAuthor;
	}

	public void setReviewAuthor(ReviewInfo reviewAuthor) {
		this.reviewAuthor = reviewAuthor;
	}

}
