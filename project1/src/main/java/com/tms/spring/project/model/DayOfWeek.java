package com.tms.spring.project.model;

import com.tms.spring.project.model.Task;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class DayOfWeek
{
	private String name;
	private List<Task> tasks;

	public DayOfWeek() { this.tasks = new ArrayList<Task>(); };

	public DayOfWeek( String name,
				 List<Task> tasks )
	{
		this.name = name;
		this.tasks = tasks;
	}

	public String getName() { return this.name; }
	public void setName( String name ) { this.name = name; }

	public List<Task> getTasks() { return this.tasks; }
	public void setTasks( List<Task> tasks ) { this.tasks = tasks; }

	public void AddTask( Task task )
	{
		this.tasks.add( task );
	}

	@Override
	public int hashCode() 
	{
		int hash = 7;
		hash = 79 * hash + Objects.hashCode( this.name );
        hash = 79 * hash + Objects.hashCode( this.tasks );
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

		final DayOfWeek otherDayOfWeek = ( DayOfWeek ) obj;

		if( !this.name.equals( otherDayOfWeek.getName() ) )
		{
			return false;
		}

		return Objects.equals( this.name, otherDayOfWeek.getName() );
	}

	@Override
	public String toString()
	{
		return "DayOfWeek[ " + "name:" + this.getName() + " ]"; 
	}
}