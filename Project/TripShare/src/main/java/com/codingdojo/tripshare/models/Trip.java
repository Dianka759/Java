package com.codingdojo.tripshare.models;

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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="trips")
public class Trip {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Name is required!")
    @Size(min=3, max=30, message="Name must be between 3 and 30 characters")
    private String name;
    
    @NotEmpty(message="Start Date is required!")
    private String startDate;
    
    @NotEmpty(message="End Date is required!")
    private String endDate;
    
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
    
    ///////////////////////////////////////////////////////////////////////////////
    @OneToMany(mappedBy="trip", fetch = FetchType.LAZY)
    private List<Location> tripLocations;
    
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="comments",
    		joinColumns= @JoinColumn(name="trip_id"),
    		inverseJoinColumns = @JoinColumn(name="user_id")
    		)
    private List<User> userComments;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="signups",
    		joinColumns= @JoinColumn(name="trip_id"),
    		inverseJoinColumns = @JoinColumn(name="user_id")
    		)
    private List<User> usersParticipating;
    ////////////////////////////////////////////////////////////////////////////////
    
    public Trip() {}



	public Trip(Long id,
			@NotEmpty(message = "Name is required!") @Size(min = 3, max = 30, message = "Name must be between 3 and 30 characters") String name,
			@NotNull(message = "Start Date is required!") String startDate,
			@NotNull(message = "End Date is required!") String endDate, Date createdAt, Date updatedAt,
			List<Location> tripLocations, User user, List<User> userComments, List<User> usersParticipating) {
		super();
		this.id = id;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.tripLocations = tripLocations;
		this.user = user;
		this.userComments = userComments;
		this.usersParticipating = usersParticipating;
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

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public List<Location> getTripLocations() {
		return tripLocations;
	}

	public void setTripLocations(List<Location> tripLocations) {
		this.tripLocations = tripLocations;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<User> getUserComments() {
		return userComments;
	}

	public void setUserComments(List<User> userComments) {
		this.userComments = userComments;
	}

	public List<User> getUsersParticipating() {
		return usersParticipating;
	}

	public void setUsersParticipating(List<User> usersParticipating) {
		this.usersParticipating = usersParticipating;
	}

    
}
