package com.myPostApp.myPostApp.entity;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="role")
public class Role {

	@Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name="id")
	private Long id;
    private String name;
	
    public Role() {
	}

	public Role(String name) {
		this.name = name;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
    
	

}
