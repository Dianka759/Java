package com.codingdojo.studentroster.models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="dorms")
public class Dorm {
	   @Id
	   @GeneratedValue(strategy = GenerationType.IDENTITY)
	   private Long id;
	    
	   @NotEmpty(message="Name is required!")
	   private String name;
	   
	   @Column(updatable= false)
	    @DateTimeFormat(pattern="yyyy-mm-dd")
	    private Date createdAt;
	    
	    @DateTimeFormat(pattern="yyyy-mm-dd")
	    private Date updatedAt;
	    
	    @OneToMany(mappedBy="dorm", fetch = FetchType.LAZY)
	    private List<Student> students;
	   
		@PrePersist
	    protected void onCreate() {
	    	this.createdAt = new Date();
	    }
	    
	    @PreUpdate
	    protected void onUpdate() {
	    	this.updatedAt = new Date();
	    }
	    
	    public Dorm() {}

	    
		public Dorm(@NotEmpty(message = "Name is required!") String name) {
			super();
			this.name = name;
		}

		public Dorm(Long id, @NotEmpty(message = "Name is required!") String name, Date createdAt, Date updatedAt,
				List<Student> students) {
			super();
			this.id = id;
			this.name = name;
			this.createdAt = createdAt;
			this.updatedAt = updatedAt;
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

		public List<Student> getStudents() {
			return students;
		}

		public void setStudents(List<Student> students) {
			this.students = students;
		}
	    
	    
}
