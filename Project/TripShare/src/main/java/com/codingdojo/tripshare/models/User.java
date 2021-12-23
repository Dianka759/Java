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
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="First name is required!")
    @Size(min=3, max=30, message="First name must be between 3 and 30 characters.")
    private String firstName;
    
    @NotEmpty(message="Last name is required!")
    @Size(min=3, max=30, message="Last name must be between 3 and 30 characters.")
    private String lastName;
    
    @NotEmpty(message="Email is required!")
    @Email(message="Please enter a valid email!")
    private String email;
    
    @NotEmpty(message="Password is required!")
    @Size(min=8, max=128, message="Password must be between 8 and 128 characters.")
    private String password;
    
    @Transient
    @NotEmpty(message="Confirm Password is required!")
    @Size(min=8, max=128, message="Confirm Password must match the above password.")
    private String confirm;
    
    @Column(updatable= false)
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date createdAt;
    
    @DateTimeFormat(pattern="yyyy-mm-dd")
    private Date updatedAt;
    
    /////////////////// TABLE JOINS ///////////////////////
    @OneToMany(mappedBy="user", fetch = FetchType.LAZY)
    private List<Trip> userTrips;
    ////////////////////////////////////////////////////// 
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="comments",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns = @JoinColumn(name="trip_id")
    		)
    private List<Trip> comments;
    
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="friendships",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns = @JoinColumn(name="friend_id")
    		)
    private List<User> myFriends;
    
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="friendships",
    		joinColumns= @JoinColumn(name="friend_id"),
    		inverseJoinColumns = @JoinColumn(name="user_id")
    		)
    private List<User> usersWhoFriended;
    
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="signups",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns = @JoinColumn(name="trip_id")
    		)
    private List<Trip> participating;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="ratings",
    		joinColumns= @JoinColumn(name="user_id"),
    		inverseJoinColumns = @JoinColumn(name="location_id")
    		)
    private List<Location> ratingsPosted;
    ////////////////////////////////////////////////////////////////////
    
    
    public User() {}
    

	public User(Long id,
			@NotEmpty(message = "Name is required!") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters") String firstName,
			@NotEmpty(message = "Name is required!") @Size(min = 3, max = 30, message = "Username must be between 3 and 30 characters") String lastName,
			@NotEmpty(message = "Email is required!") @Email(message = "Please enter a valid email!") String email,
			@NotEmpty(message = "Password is required!") @Size(min = 8, max = 128, message = "Password must be between 8 and 128 characters") String password,
			@NotEmpty(message = "Confirm Password is required!") @Size(min = 8, max = 128, message = "Confirm Password must be between 8 and 128 characters") String confirm,
			Date createdAt, Date updatedAt, List<Trip> userTrips, List<Trip> comments, List<User> myFriends,
			List<User> usersWhoFriended, List<Trip> participating, List<Location> ratingsPosted) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.confirm = confirm;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.userTrips = userTrips;
		this.comments = comments;
		this.myFriends = myFriends;
		this.usersWhoFriended = usersWhoFriended;
		this.participating = participating;
		this.ratingsPosted = ratingsPosted;
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


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirm() {
		return confirm;
	}

	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}


	public List<Trip> getUserTrips() {
		return userTrips;
	}


	public void setUserTrips(List<Trip> userTrips) {
		this.userTrips = userTrips;
	}


	public List<Trip> getComments() {
		return comments;
	}


	public void setComments(List<Trip> comments) {
		this.comments = comments;
	}

	public List<Trip> getParticipating() {
		return participating;
	}


	public void setParticipating(List<Trip> participating) {
		this.participating = participating;
	}


	public List<User> getMyFriends() {
		return myFriends;
	}


	public void setMyFriends(List<User> myFriends) {
		this.myFriends = myFriends;
	}


	public List<User> getUsersWhoFriended() {
		return usersWhoFriended;
	}


	public void setUsersWhoFriended(List<User> usersWhoFriended) {
		this.usersWhoFriended = usersWhoFriended;
	}


	public List<Location> getRatingsPosted() {
		return ratingsPosted;
	}


	public void setRatingsPosted(List<Location> ratingsPosted) {
		this.ratingsPosted = ratingsPosted;
	}
	
	public List<User> deleteFriend(Long id) {
		List<User> friends = this.myFriends;
		for(int i = 0; i < friends.size(); i++) {
			if(id == friends.get(i).getId()) {
				friends.remove(friends.get(i));
				return this.myFriends = friends;
			}
		}
		return this.myFriends = friends;
		
	}


}
