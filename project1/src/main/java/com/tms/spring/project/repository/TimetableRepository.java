package com.tms.spring.project.repository;

import com.tms.spring.project.model.Task;
import com.tms.spring.project.model.Timetable;
import com.tms.spring.project.model.DayOfWeek;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import java.util.ArrayList;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import com.tms.spring.project.DBManager.DataBaseManager;
import com.tms.spring.project.service.UserService;

@Repository
public class TimetableRepository 
{
	public Timetable GetTimetable( String weekAsDate, long userId )
	{
		Timetable timetable = new Timetable();
		DayOfWeek tmpDay = null;
		Task tmpTask = null;
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase();
		List<String> daysOfWeekList = List.of( "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday" );

		try
		{
			if( weekAsDate == null || weekAsDate.isEmpty() || weekAsDate.trim().isEmpty() )
			{
				weekAsDate = "now()::date";
			}

			for( int i = 1; i <= 7; i++ )
			{
				statement = dbConn.prepareStatement( "SELECT * FROM tasks as t where extract(week from t.task_date)=extract(week from ?::date) and extract(dow from t.task_date)=?::int" );	
				statement.setString( 1, weekAsDate );
				statement.setInt( 2, i );
				result = statement.executeQuery();
				tmpDay = new DayOfWeek();

				while( result.next() )
				{
					tmpTask = new Task();

					tmpTask.setId( result.getLong( 1 ) );
					tmpTask.setInsertedAt( result.getString( 2 ) );
					tmpTask.setUserId( result.getLong( 3 ) );
					tmpTask.setName( result.getString( 4 ) );
					tmpTask.setDescription( result.getString( 5 ) );
					tmpTask.setStartTime( result.getString( 6 ) );
					tmpTask.setEndTime( result.getString( 7 ) );
					tmpTask.setIsDeleted( result.getBoolean( 8 ) );
					tmpTask.setTaskDate( result.getString( 9 ) );

					tmpDay.AddTask( tmpTask );
					tmpDay.setName( daysOfWeekList.get(i-1) );
				}

				timetable.AddDayOfWeek( tmpDay );
			}
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement );
		}

		return timetable;
	}
}