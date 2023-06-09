package com.tms.spring.project.model;

import com.tms.spring.project.model.DayOfWeek;
import java.util.Objects;
import java.util.List;
import java.util.ArrayList;

public class Timetable
{
	private List<DayOfWeek> daysOfWeek;

	public Timetable() { this.daysOfWeek = new ArrayList<DayOfWeek>(); };

	public Timetable( List<DayOfWeek> daysOfWeek )
	{
		this.daysOfWeek = daysOfWeek;
	}

	public List<DayOfWeek> getDaysOfWeek() { return this.daysOfWeek; }
	public void setDaysOfWeek( List<DayOfWeek> daysOfWeek ) { this.daysOfWeek = daysOfWeek; }

	public void AddDayOfWeek( DayOfWeek dayOfWeek )
	{
		this.daysOfWeek.add( dayOfWeek );
	}

	@Override
	public int hashCode() 
	{
		int hash = 7;
        hash = 79 * hash + Objects.hashCode( this.daysOfWeek );
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

		final Timetable otherTimetable = ( Timetable ) obj;

		return Objects.equals( this.daysOfWeek, otherTimetable.getDaysOfWeek() );
	}

	@Override
	public String toString()
	{
		return "Task[ " + "daysOfWeek:" + this.getDaysOfWeek() + " ]"; 
	}
}