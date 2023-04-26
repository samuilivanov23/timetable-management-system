package com.tms.spring.project.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "users" )
public class User
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )

	private long id;
	private String insertedAt;
	private String firstName;
	private String lastName;
	private String username;
	private String emailAddress;
	private String password;
	private boolean isAuthenticated;
	private boolean isDeleted;

	public User() {};

	public User( long id, 
				 String insertedAt, 
				 String firstName, 
				 String lastName, 
				 String username,
				 String emailAddress, 
				 String password )
	{
		this.id = id;
		this.insertedAt = insertedAt;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.emailAddress = emailAddress;
		this.isAuthenticated = false;
		this.isDeleted = false;
	}

	public long getId() { return this.id; }
	public void setId( long id ) { this.id = id; }

	public String getInsertedAt() { return this.insertedAt; }
	public void setInsertedAt( String insertedAt ) { this.insertedAt = insertedAt; }
	
	public String getFirstName() { return this.firstName; }
	public void setFirstName( String firstName ) { this.firstName = firstName; }
	
	public String getLastName() { return this.lastName; }
	public void setLastName( String lastName ) { this.lastName = lastName; }
	
	public String getUsername() { return this.username; }
	public void setUsername( String username ) { this.username = username; }

	public String getEmailAddress() { return this.emailAddress; }
	public void setEmailAddress( String emailAddress ) { this.emailAddress = emailAddress; }

	public String getPassword() { return this.password; }
	public void setPassword( String password ) { this.password = password; }

	public boolean getIsAuthenticated() { return this.isAuthenticated; }
	public void setIsAuthenticated( boolean isAuthenticated ) { this.isAuthenticated = isAuthenticated; }
	
	public boolean getIsDeleted() { return this.isDeleted; }
	public void setIsDeleted( boolean isDeleted ) { this.isDeleted = isDeleted; }

	@Override
	public int hashCode() 
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode( this.id );
        hash = 79 * hash + Objects.hashCode( this.firstName );
		hash = 79 * hash + Objects.hashCode( this.lastName );
        hash = 79 * hash + Objects.hashCode( this.username );
		hash = 79 * hash + Objects.hashCode( this.emailAddress );
        return hash;
	}

	@Override
	public boolean equals( Object obj )
	{
		if( this == obj ) 
		{ 
			return true; 
		}
		else if( obj == null || obj.getClass() != this.getClass() ) 
		{ 
			return false; 
		}

		final User otherUser = ( User ) obj;

		if( !this.username.equals( otherUser.getUsername() ) )
		{
			return false;
		}

		if( !Objects.equals( this.username, otherUser.getUsername() ) )
		{
			return false;
		}

		return Objects.equals( this.id, otherUser.id );
	}

	@Override
	public String toString()
	{
		return "User[ " + "id:" + this.getId() + 
						  ", insertedAt:" + this.getInsertedAt() +
						  ", firstName:" + this.getFirstName() +
						  ", lastName:" + this.getLastName() +
						  ", isAuthenticated" + this.getIsAuthenticated() + 
						  ", isDeleted:" + this.getIsDeleted() +
						  " ]"; 
	}
}
