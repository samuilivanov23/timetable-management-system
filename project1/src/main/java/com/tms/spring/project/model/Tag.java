package com.tms.spring.project.model;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table( name = "tags" )
public class Tag
{
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO )

	private long id;
	private String name;
    private long userId;

	public Tag() {};

	public Tag( long id, 			
				 String name,
                 long userId)
	{
		this.id = id;
		this.name = name;
        this.userId = userId;
	}

	public long getId() { return this.id; }
	public void setId( long id ) { this.id = id; }

	public String getName() { return this.name; }
	public void setName( String name ) { this.name = name; }

    public long getUserId() { return this.userId; }
	public void setUserId( long userId ) { this.userId = userId; }
	

	@Override
	public int hashCode() 
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode( this.id );
        hash = 79 * hash + Objects.hashCode( this.name );
        hash = 79 * hash + Objects.hashCode( this.userId );
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

		final Tag otherTag = ( Tag ) obj;

		if( !this.name.equals( otherTag.getName() ) )
		{
			return false;
		}

		return Objects.equals( this.id, otherTag.getId());
	}

	@Override
	public String toString()
	{
		return "Tag[ " + "id:" + this.getId() + 		  
						 ", name:" + this.getName() +
                         ", userId:" + this.getUserId() +
						 " ]"; 
	}
}