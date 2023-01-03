package com.stackroute.feedbackservice.model;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(collection = "feedbackPost")
public class Feedback {


	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getFeedback() {
		return feedback;
	}
	public void setFeedback(String feedback) {
		this.feedback = feedback;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	//@Id
	//private int id;

    private int userId;
    private String userName;
	private String companyName;
	private String feedback;
	private int rating;
}
	