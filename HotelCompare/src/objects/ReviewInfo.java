package objects;

public class ReviewInfo {

	private String authName;
	private String authLocation;
	private String reviewTitle;
	private String reviewId;
	private String reviewComment;

	public ReviewInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ReviewInfo(String authName, String authLocation, String reviewTitle, String reviewId,
			String reviewComment) {
		super();
		this.authName = authName;
		this.authLocation = authLocation;
		this.reviewTitle = reviewTitle;
		this.reviewId = reviewId;
		this.reviewComment = reviewComment;
	}

	public String getAuthName() {
		return authName;
	}

	public void setAuthName(String authName) {
		this.authName = authName;
	}

	public String getAuthLocation() {
		return authLocation;
	}

	public void setAuthLocation(String authLocation) {
		this.authLocation = authLocation;
	}

	public String getReviewTitle() {
		return reviewTitle;
	}

	public void setReviewTitle(String reviewTitle) {
		this.reviewTitle = reviewTitle;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getReviewComment() {
		return reviewComment;
	}

	public void setReviewComment(String reviewComment) {
		this.reviewComment = reviewComment;
	}

}
