package com.codingdojo.javaexam.models;

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
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="students")
public class Student {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message="Name is required!")
    private String name;
    
    @NotEmpty(message="email is required!")
    private String email;
    
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

    
//    @OneToMany(mappedBy="student", fetch = FetchType.LAZY)
//    private List<Class> studentClasses;
    
    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
    		name="students_in_class",
    		joinColumns= @JoinColumn(name="student_id"),
    		inverseJoinColumns = @JoinColumn(name="class_id")
    		)
    private List<YogaClass> classes;
    
    public Student() {}

	public Student(Long id, @NotEmpty(message = "Name is required!") String name,
			@NotEmpty(message = "email is required!") String email, Date createdAt, Date updatedAt,
			List<YogaClass> classes) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.classes = classes;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public List<YogaClass> getClasses() {
		return classes;
	}

	public void setClasses(List<YogaClass> classes) {
		this.classes = classes;
	}
    
    
}
