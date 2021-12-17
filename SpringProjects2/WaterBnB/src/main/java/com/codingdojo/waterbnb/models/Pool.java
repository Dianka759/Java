package com.codingdojo.waterbnb.models;

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
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name="pools")
public class Pool {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message="Address required.")
	@Size(min=3, max=50, message="address needs to be between 3 and 50")
	private String address;
	
	@NotEmpty(message="size required")
	private String poolSize;

	@NotNull(message="Cost required.")
	@Min(50)
	@Max(1000)
	private double price;
	
	@NotEmpty(message="description required")
	@Size(min=3, max=50, message="description between 3 and 50")
	private String description;
	
	
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
    
	 @OneToMany(mappedBy="pool", fetch=FetchType.LAZY)
	 private List<Review> reviews;
	
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;
   
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "reviews", 
        joinColumns = @JoinColumn(name = "pool_id"), 
        inverseJoinColumns = @JoinColumn(name = "user_id") )    
    private List<User> users;

   
	public Pool() {}

	public Pool(Long id,
			@NotEmpty(message = "Address required.") @Size(min = 3, max = 50, message = "address needs to be between 3 and 50") String address,
			@NotEmpty(message = "size required") String poolSize,
			@NotNull(message = "Cost required.") @Min(50) @Max(1000) double price,
			@NotEmpty(message = "description required") @Size(min = 3, max = 50, message = "description between 3 and 50") String description,
			Date createdAt, Date updatedAt, User user, List<User> users) {
		super();
		this.id = id;
		this.address = address;
		this.poolSize = poolSize;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.user = user;
		this.users = users;
	}

	
	public Pool(Long id,
			@NotEmpty(message = "Address required.") @Size(min = 3, max = 50, message = "address needs to be between 3 and 50") String address,
			@NotEmpty(message = "size required") String poolSize,
			@NotNull(message = "Cost required.") @Min(50) @Max(1000) double price,
			@NotEmpty(message = "description required") @Size(min = 3, max = 50, message = "description between 3 and 50") String description,
			Date createdAt, Date updatedAt, List<Review> reviews, User user, List<User> users) {
		super();
		this.id = id;
		this.address = address;
		this.poolSize = poolSize;
		this.price = price;
		this.description = description;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.reviews = reviews;
		this.user = user;
		this.users = users;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(String poolSize) {
		this.poolSize = poolSize;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
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
	
	

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}
	
	///FIND THE AVERAGE OF THE RATINGS ///
	public double getAverageRating() {
		double numReviews = this.reviews.size();
		if(numReviews == 0) {
			return 0.0;
		}
		else {
			double sum = 0.00;
			for(int i = 0; i < numReviews; i++) {
				sum += this.reviews.get(i).getRating();
			}
			return sum / numReviews;
		}
	}		

}
