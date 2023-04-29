package com.tms.spring.project.repository;

import com.tms.spring.project.model.Task;
import com.tms.spring.project.service.IEmailService;
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
public class UserRepository 
{
	public boolean CreateTask( Task task, long userId )
	{
		boolean isTaskCreatedSuccessfully = false;
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );

			//statement = dbConn.prepareStatement( "INSERT INTO users(first_name, last_name, username, email_address, password) values(?, ?, ?, ?, ?) RETURNING id" );
			statement = dbConn.prepareStatement( "INSERT INTO tasks (user_id, name, description, start_time, end_time, task_date) values(?, ?, ?, ?, ?, ?) RETURNING id" );
			statement.setString( 1, userId );
			statement.setString( 2, task.getName() );
			statement.setString( 3, task.getDescription() );
			statement.setString( 4, task.getStartTime() );
			statement.setString( 5, task.getEndTime() );
			statement.setString( 6, task.getTaskDate() );
			
			result = statement.executeQuery();	
			result.next();
			userId = result.getLong( 1 );

			if( result.getLong( 1 ) )
			{
				isTaskCreatedSuccessfully = true;
				transactionStatement.executeUpdate( "COMMIT" );
			}
			else
			{
				isTaskCreatedSuccessfully = false;
				transactionStatement.executeUpdate( "ROLLBACK" );
			}
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}
		finally
		{
			DataBaseManager.CloseConnection( dbConn, result, ( Statement ) statement, transactionStatement );
		}

		return isTaskCreatedSuccessfully;
	}
}