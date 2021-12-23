package com.codingdojo.tripshare.models;

import java.text.DecimalFormat;
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

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "locations")
public class Location {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "Name is required!")
	private String name;

	@NotEmpty(message = "Address is required!")
	private String address;

	@NotEmpty(message = "Link is required!")
	private String outsideLink;

	@NotNull(message = "Cost is required!")
	private Double cost;
	
	
	private String avgRating;
	
	

	@Column(updatable = false)
	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date createdAt;

	@DateTimeFormat(pattern = "yyyy-mm-dd")
	private Date updatedAt;

	@PrePersist
	protected void onCreate() {
		this.createdAt = new Date();
	}

	@PreUpdate
	protected void onUpdate() {
		this.updatedAt = new Date();
	}

	//////////////////////////////////////////////////////////
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "trip_id")
	private Trip trip;
	
	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name="ratings",
			joinColumns= @JoinColumn(name="location_id"),
			inverseJoinColumns = @JoinColumn(name="user_id")
			)
	private List<User> usersThatRated;
	
	 @OneToMany(mappedBy="location", fetch=FetchType.LAZY)
	 private List<Rating> ratings;
	
	//////////////////////////////////////////////////////////
	
	public Location() {
	}

	public Location(Long id, @NotEmpty(message = "Name is required!") String name,
			@NotEmpty(message = "Address is required!") String address,
			@NotEmpty(message = "Link is required!") String outsideLink,
			@NotNull(message = "Cost is required!") Double cost,
			Date createdAt, Date updatedAt,
			Trip trip, List<User> usersThatRated, List<Rating> ratings) {
		super();
		this.id = id;
		this.name = name;
		this.address = address;
		this.outsideLink = outsideLink;
		this.cost = cost;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.trip = trip;
		this.usersThatRated = usersThatRated;
		this.ratings = ratings;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOutsideLink() {
		return outsideLink;
	}

	public void setOutsideLink(String outsideLink) {
		this.outsideLink = outsideLink;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}



	public Trip getTrip() {
		return trip;
	}

	public void setTrip(Trip trip) {
		this.trip = trip;
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
	
	public List<User> getLocationRatings() {
		return usersThatRated;
	}

	public void setLocationRatings(List<User> usersThatRated) {
		this.usersThatRated = usersThatRated;
	}

	public List<Rating> getRatings() {
		return ratings;
	}

	public void setRatings(List<Rating> ratings) {
		this.ratings = ratings;
	}
	
	public String getAvgRating() {
		return avgRating;
	}

	///FIND THE AVERAGE OF THE RATINGS ///
	public String getAvgerageRating() {
		double numRatings = this.ratings.size();
		if(numRatings == 0) {
			return "0.0";
		}
		else {
			double sum = 0.00;
			for(int i = 0; i < numRatings; i++) {
				sum += this.ratings.get(i).getRating();
			}
			DecimalFormat df = new DecimalFormat("#.0");
			double average = sum / numRatings;
			return  df.format(average);
		}
	}		
	
	/////Find average cost per person /////
	public double getAverageCost() {
		double numOfParticipants = this.trip.getUsersParticipating().size();
		if(numOfParticipants == 0) {
			return 0.0;
		}
		else {	
			double total = this.getCost() / numOfParticipants; 
			return total;
		}
	}		

}
