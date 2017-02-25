package model;

import java.time.LocalDate;

import dal.exceptions.ValidationException;

public class Video {
	private Integer videoId;
	private String title;
	private String director;
	private LocalDate appearance;
	private Integer duration;
	private Integer fee;
	private String commentLine;
	private String type;

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}
	
	public void parseVideoId(String videoId) throws ValidationException {
		//TODO: validate
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void parseTitle(String title) throws ValidationException {
		//TODO: validate
		
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}
	
	public void parseDirector(String director) throws ValidationException {
		//TODO: validate
	}

	public LocalDate getAppearance() {
		return appearance;
	}

	public void setAppearance(LocalDate appearance) {
		this.appearance = appearance;
	}

	public Integer getDuration() {
		return duration;
	}

	public void setDuration(Integer duration) {
		this.duration = duration;
	}
	
	public void setDuration(String duration) throws ValidationException {
		//TODO: validate
	}

	public Integer getFee() {
		return fee;
	}

	public void setFee(Integer fee) {
		this.fee = fee;
	}
	
	public void parseFee(String fee) throws ValidationException {
		//TODO: validate
	}

	public String getCommentLine() {
		return commentLine;
	}

	public void setCommentLine(String commentLine) {
		this.commentLine = commentLine;
	}
	
	public void parseCommentLine(String commentLine) throws ValidationException {
		//TODO: validate
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public void parseType(String type) throws ValidationException {
		//TODO: validate
	}

	@Override
	public String toString() {
		return "Video [videoId=" + videoId + ", title=" + title + ", director=" + director + "]";
	}

}
