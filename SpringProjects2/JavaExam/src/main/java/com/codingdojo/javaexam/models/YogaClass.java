package com.codingdojo.javaexam.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="classes")
public class YogaClass {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    @NotEmpty(message="Name is required!")
	    private String name;
	    
	    @NotEmpty(message="Day is required!")
	    private String day;
	    
	    @NotNull(message="Price is required.")
	    @Min(value=1, message="Price cannot be negative.")
	    private double price;
	    
	    @NotEmpty(message="Description is required!")
//	    @Size(min=1, max=100, message="must be between 1 and 100 characters")
	    private String description;
	    
	    @NotEmpty(message="Time cannot be empty!")
	    private String time;
	    
	    @Column(updatable=false)
	    private Date createdAt;
	    
	    private Date updatedAt;
	    
	    @ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name="user_id")
	    private User user;
	    
	    @ManyToMany(fetch=FetchType.LAZY)
	    @JoinTable(
	    		name="students_in_class",
	    		joinColumns= @JoinColumn(name="class_id"),
	    		inverseJoinColumns = @JoinColumn(name="student_id")
	    		)
	    private List<Student> students;
	    
	    @PrePersist
	    protected void onCreate(){
	        this.createdAt = new Date();
	    }
	    
		@PreUpdate
	    protected void onUpdate(){
	        this.updatedAt = new Date();
	    }
		
		public YogaClass() {}



		public YogaClass(Long id, @NotEmpty(message = "Name is required!") String name,
				@NotEmpty(message = "Day is required!") String day,
				@NotNull(message = "Price is required.") @Min(value = 1, message = "price cannot be negative.") double price,
				@NotEmpty(message = "Description is required!") String description, Date createdAt, Date updatedAt,
				User user, List<Student> students) {
			super();
			this.id = id;
			this.name = name;
			this.day = day;
			this.price = price;
			this.description = description;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.user = user;
			this.students = students;
		}

		public YogaClass(Long id, @NotEmpty(message = "Name is required!") String name,
				@NotEmpty(message = "Day is required!") String day,
				@NotNull(message = "Price is required.") @Min(value = 1, message = "price cannot be negative.") double price,
				@NotEmpty(message = "Description is required!") String description, String time, Date createdAt,
				Date updatedAt, User user, List<Student> students) {
			super();
			this.id = id;
			this.name = name;
			this.day = day;
			this.price = price;
			this.description = description;
			this.time = time;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
			this.user = user;
			this.students = students;
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

		public String getDay() {
			return day;
		}

		public void setDay(String day) {
			this.day = day;
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


		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
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

		public List<Student> getStudents() {
			return students;
		}

		public void setStudents(List<Student> students) {
			this.students = students;
		}
		
		
	    // Function to validate the time in 12-hour format.
	    public boolean isValidTime(String time)
	    {
	        // Regex to check valid time in 12-hour format.
	        String regexPattern
	            = "(1[012]|[1-9]):" + "[0-5][0-9](\\s)" + "?(?i)(am|pm)";
	 
	        // Compile the ReGex
	        Pattern compiledPattern = Pattern.compile(regexPattern);
	        // If the time is empty, return false
	        if (time == null) {
	            return false;
	        }
	        // matcher() method finds matching between given time and regular expression
	        Matcher m = compiledPattern.matcher(time);
	 
	        // Return if the time matched the ReGex
	        return m.matches();
	    }	
	    
	    ///////// CONVERTING A 24H STRING TO 12H STRING YASSSSSSSSSSSS///
	    public static String convertTo24Hour(String Time) throws ParseException {
	        SimpleDateFormat _24HourSDF = new SimpleDateFormat("HH:mm");
	        SimpleDateFormat _12HourSDF = new SimpleDateFormat("hh:mm a");
	        Date _24HourDt = _24HourSDF.parse(Time);

			return _12HourSDF.format(_24HourDt);
	    }
	   
	
}
