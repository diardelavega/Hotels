package objects;

import java.time.LocalDate;

/**
 * @author Administrator
 *
 *         A class designed to keep data regarding the comments that the hotel
 *         clients left, regarding their experience.
 * 
 *         The class is designed based on the json object, by taking only the
 *         necessary attributes we require.
 *
 */
public class Comment {
	private String authorLocation;
	private String author;
	private String title;
	private String content;
	private LocalDate ld;
	private float sentiment;// a value from the analysis of the text-comment

	public Comment() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comment(String authorLocation, String author, String title, String content, LocalDate ld, float sentiment) {
		super();
		this.authorLocation = authorLocation;
		this.author = author;
		this.title = title;
		this.content = content;
		this.ld = ld;
		this.sentiment = sentiment;
	}

	public String getAuthorLocation() {
		return authorLocation;
	}

	public void setAuthorLocation(String authorLocation) {
		this.authorLocation = authorLocation;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public LocalDate getLd() {
		return ld;
	}

	public void setLd(LocalDate ld) {
		this.ld = ld;
	}

	public float getSentiment() {
		return sentiment;
	}

	public void setSentiment(float sentiment) {
		this.sentiment = sentiment;
	}

}
