package com.codingdojo.beltreview.models;

import java.util.Date;
import java.util.List;
import java.util.Set;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="mischiefs")
public class Mischief {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Name is required!")
    @Size(min=3, max=30, message="name must be between 3 and 30 characters")
    private String name;
    
    @NotEmpty(message="location is required!")
    @Size(min=3, max=30, message="location must be between 3 and 30 characters")
    private String location;
    
    @NotEmpty(message="duration is required!")
    @Min(1)
    @Max(48)
    private String duration;
    
    @NotEmpty(message="Description is required!")
    @Size(min=10, max=50, message="Description must be between 10 and 50 characters") 
    private String description;

    @Column(updatable= false)
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date updatedAt;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="join_table",
    		joinColumns= @JoinColumn(name="mis_id"),
    		inverseJoinColumns = @JoinColumn(name="user_id")
    		)
    private List<User> users;
    
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;


	@PrePersist
    protected void onCreate() {
    	this.createdAt = new Date();
    }
    
    @PreUpdate
    protected void onUpdate() {
    	this.updatedAt = new Date();
    }
    

	public Mischief() {
		super();
	}
	
	public Mischief(Long id,
			@NotEmpty(message = "Name is required!") @Size(min = 3, max = 30, message = "name must be between 3 and 30 characters") String name,
			@NotEmpty(message = "location is required!") @Size(min = 3, max = 30, message = "location must be between 3 and 30 characters") String location,
			@NotEmpty(message = "duration is required!") @Min(1) @Max(48) String duration,
			@NotEmpty(message = "Description is required!") @Size(min = 10, max = 50, message = "Description must be between 10 and 50 characters") String description,
			Date createdAt, Date updatedAt, List<User> users, User user) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.duration = duration;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.users = users;
		this.user = user;
	}
	
	public Mischief(Long id,
			@NotEmpty(message = "Name is required!") @Size(min = 3, max = 30, message = "name must be between 3 and 30 characters") String name,
			@NotEmpty(message = "location is required!") @Size(min = 3, max = 30, message = "location must be between 3 and 30 characters") String location,
			@NotEmpty(message = "duration is required!") @Min(1) @Max(48) String duration,
			@NotEmpty(message = "Description is required!") @Size(min = 10, max = 50, message = "Description must be between 10 and 50 characters") String description,
			Date createdAt, Date updatedAt, User user) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.duration = duration;
		this.description = description;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}



    
    

}
