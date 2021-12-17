package com.codingdojo.projectmanagment.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="projects")
public class Project {

	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private Long id;
	    
	  @NotEmpty(message="Title is required!")
	  private String title;
	  
	  @NotEmpty(message="Description is required")
	  @Size(min=3, message="Description must be at least 3 characters.")
	  private String description;
	  
	  @NotNull(message="Due date must be provided.")
	  @FutureOrPresent
	  @DateTimeFormat(pattern="yyyy-mm-dd")
	  private Date dueDate;
	  
	  @Column(updatable= false)
	  @DateTimeFormat(pattern="yyyy-mm-dd")
	  private Date createdAt;
    
	  @DateTimeFormat(pattern="mm-dd-yyy")
	  private Date updatedAt;
    
	  @ManyToOne(fetch = FetchType.LAZY)
	  @JoinColumn(name="user_id")
	  private User user;
	  
	   @ManyToMany(fetch=FetchType.LAZY)
	    @JoinTable(
	    		name="joined_team",
	    		joinColumns= @JoinColumn(name="project_id"),
	    		inverseJoinColumns = @JoinColumn(name="user_id")
	    		)
	    private List<User> joinedUsers;
	  
	  public Project() {}

	public Project(Long id, @NotEmpty(message = "Title is required!") String title,
			@NotEmpty(message = "Description is required") @Size(min = 3, message = "Description must be at least 3 characters.") String description,
			 Date dueDate, Date createdAt, Date updatedAt, User user) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
	}
	

	public Project(Long id, @NotEmpty(message = "Title is required!") String title,
			@NotEmpty(message = "Description is required") @Size(min = 3, message = "Description must be at least 3 characters.") String description,
			@NotNull(message = "Due date must be provided.") @FutureOrPresent Date dueDate, Date createdAt,
			Date updatedAt, User user, List<User> joinedUsers) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.dueDate = dueDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.joinedUsers = joinedUsers;
	}

	@PrePersist
    protected void onCreate() {
    	this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt = new Date();
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
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

	public List<User> getJoinedUsers() {
		return joinedUsers;
	}

	public void setJoinedUsers(List<User> joinedUsers) {
		this.joinedUsers = joinedUsers;
	}


	  
}
