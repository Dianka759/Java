package com.codingdojo.waterbnb.models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
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

	@NotEmpty(message="Cost required.")
	@Min(50)
	@Max(1000)
	private Double price;
	
	@NotEmpty(message="description required")
	@Size(min=3, max=50, message="description between 3 and 50")
	private String description;
	
	@NotEmpty(message="Rating is required.")
	@Min(1)
	@Max(5)
	private int rating;
	
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
	
    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;
	
	public Pool() {}

	public Pool(Long id,
			@NotEmpty(message = "Address required.") @Size(min = 3, max = 50, message = "address needs to be between 3 and 50") String address,
			@NotEmpty(message = "size required") String poolSize,
			@NotEmpty(message = "Cost required.") @Min(50) @Max(1000) Double price,
			@NotEmpty(message = "description required") @Size(min = 3, max = 50, message = "dets between 3 and 50") String description,
			@NotEmpty(message = "Rating is required.") @Min(1) @Max(5) int rating, Date createdAt, Date updatedAt,
			User user) {
		super();
		this.id = id;
		this.address = address;
		this.poolSize = poolSize;
		this.price = price;
		this.description = description;
		this.rating = rating;
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}
	
}
