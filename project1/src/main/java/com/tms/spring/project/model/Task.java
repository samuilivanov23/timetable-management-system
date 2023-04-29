package com.tms.spring.project.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "tasks" )
public class Task
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )

	private long id;
	private String insertedAt;
	private long userId;
	private String name;
	private String description;
	private String startTime;
	private String endTime;
	private boolean isDeleted;
	private String taskDate;

	public User() {};

	public User( long id, 
				 String insertedAt, 
				 long userId, 
				 String name, 
				 String description,
				 String startTime, 
				 String endTime,
				 boolean taskDate,
				 String taskDate )
	{
		this.id = id;
		this.insertedAt = insertedAt;
		this.userId = userId;
		this.name = name;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.isDeleted = false;
		this.taskDate = taskDate;
	}

	public long getId() { return this.id; }
	public void setId( long id ) { this.id = id; }

	public String getInsertedAt() { return this.insertedAt; }
	public void setInsertedAt( String insertedAt ) { this.insertedAt = insertedAt; }

	public long getUserId() { return this.userId; }
	public void setUserId( long userId ) { this.userId = userId; }
	
	public String getName() { return this.name; }
	public void setName( String name ) { this.name = name; }
	
	public String getDescription() { return this.description; }
	public void setDescription( String description ) { this.description = description; }
	
	public String getStartTime() { return this.startTime; }
	public void setStartTime( String startTime ) { this.startTime = startTime; }

	public String getEndTime() { return this.endTime; }
	public void setEndTime( String endTime ) { this.endTime = endTime; }
	
	public boolean getIsDeleted() { return this.isDeleted; }
	public void setIsDeleted( boolean isDeleted ) { this.isDeleted = isDeleted; }

	public String getTaskDate() { return this.taskDate; }
	public void setTaskDate( String taskDate ) { this.taskDate = taskDate; }

	@Override
	public int hashCode() 
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode( this.id );
        hash = 79 * hash + Objects.hashCode( this.name );
		hash = 79 * hash + Objects.hashCode( this.description );
        hash = 79 * hash + Objects.hashCode( this.startTime );
		hash = 79 * hash + Objects.hashCode( this.endTime );
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
		return "Task[ " + "id:" + this.getId() + 
						  ", insertedAt:" + this.getInsertedAt() +
						  ", name:" + this.getLastName() +
						  ", description:" + this.getDescription() +
						  ", startTime" + this.getStartTime() + 
						  ", endTime:" + this.getEndTime() +
						  ", task date:" + this.getTaskDate() +
						  " ]"; 
	}
}