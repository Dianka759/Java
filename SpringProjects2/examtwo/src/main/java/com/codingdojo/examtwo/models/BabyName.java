package com.codingdojo.examtwo.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "babynames")
public class BabyName {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	@Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long.")
	private String name;

	@NotNull
	@Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long.")
	private String gender;

	@NotNull
	@Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long.")
	private String origin;

	@NotNull
	@Size(min = 3, max = 300, message = "Description must be between 3 and 300 characters long.")
	private String meaning;

	@NotNull
	private int votes;

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;
	
//   @OneToMany(mappedBy="babynames", fetch=FetchType.LAZY)
//   private List<User> voters;
//	
	
	

	public BabyName() {
		super();
	}

	public BabyName(Long id,
			@NotNull @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long.") String name,
			@NotNull @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long.") String gender,
			@NotNull @Size(min = 3, max = 25, message = "Name must be between 3 and 25 characters long.") String origin,
			@NotNull @Size(min = 3, max = 300, message = "Description must be between 3 and 300 characters long.") String meaning,
			@NotNull int votes, Date createdAt, Date updatedAt, User user) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.origin = origin;
		this.meaning = meaning;
		this.votes = votes;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getMeaning() {
		return meaning;
	}

	public void setMeaning(String meaning) {
		this.meaning = meaning;
	}

	public int getVotes() {
		return votes;
	}

	public void setVotes(int votes) {
		this.votes = votes;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public int Votes(int votes) {
		 votes = getVotes();
		votes = votes + 1;
		setVotes(votes);
		return votes;
	}

}
