package com.dfwgg.sample.model

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name="Users")
class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	long id
	
	String firstName
	String lastName
	String userName
}
