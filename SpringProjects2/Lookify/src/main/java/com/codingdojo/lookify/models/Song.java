package com.codingdojo.lookify.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="songs")
public class Song {
	  
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Title is required!")
    @Size(min=5, message="Title must be at least 5 characters.")
    private String title;
    
    @NotEmpty(message="Artist is required!")
    @Size(min=5, message="Artist must be at least 5 characters.")
    private String artist;
    
    @NotEmpty(message="Rating is required!")
    @Min(value=1)
    @Max(value=10,message="Title must be between 1 and 10.")
    private String rating;

    @Column(updatable= false)
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date updatedAt;
    
	@PrePersist
    protected void onCreate() {
    	this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt = new Date();
    }
    
    public Song() {}

	public Song(
			@NotEmpty(message = "Title is required!") @Size(min = 5, message = "Title must be at least 5 characters.") String title,
			@NotEmpty(message = "Artist is required!") @Size(min = 5, message = "Artist must be at least 5 characters.") String artist,
			@NotEmpty(message = "Rating is required!") @Min(1) @Max(value = 10, message = "Title must be between 1 and 10.") String rating) {
		super();
		this.title = title;
		this.artist = artist;
		this.rating = rating;
	}

	public Song(Long id,
			@NotEmpty(message = "Title is required!") @Size(min = 5, message = "Title must be at least 5 characters.") String title,
			@NotEmpty(message = "Artist is required!") @Size(min = 5, message = "Artist must be at least 5 characters.") String artist,
			@NotEmpty(message = "Rating is required!") @Min(1) @Max(value = 10, message = "Title must be between 1 and 10.") String rating,
			Date createdAt, Date updatedAt) {
		super();
		this.id = id;
		this.title = title;
		this.artist = artist;
		this.rating = rating;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getArtist() {
		return artist;
	}

	public void setArtist(String artist) {
		this.artist = artist;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public Date getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
    
    
}
