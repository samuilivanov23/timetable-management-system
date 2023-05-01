package com.tms.spring.project.repository;

import com.tms.spring.project.model.Task;
import com.tms.spring.project.model.Tag;
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
public class TaskRepository 
{
	public boolean CreateTask( Task task, long userId, List<Tag> tags )
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

			statement = dbConn.prepareStatement( "INSERT INTO tasks (user_id, name, description, start_time, end_time, task_date) VALUES(?, ?, ?, ?::time, ?::time, ?::date) RETURNING id" );
			statement.setLong( 1, userId );
			statement.setString( 2, task.getName() );
			statement.setString( 3, task.getDescription() );
			statement.setString( 4, task.getStartTime() );
			statement.setString( 5, task.getEndTime() );
			statement.setString( 6, task.getTaskDate() );
			
			result = statement.executeQuery();
			result.next();
			long taskId = result.getLong( 1 );

			boolean isTagsTasksLinkedCorrectly = true;
			for(var tag: tags){
				statement = dbConn.prepareStatement( "INSERT INTO tasks_tags (task_id, tag_id) VALUES(?, ?) RETURNING task_id, tag_id" );
				statement.setLong( 1, taskId );
				statement.setLong( 2, tag.getId() );

				result = statement.executeQuery();
				result.next();
				long taskIdInMMTable = result.getLong(1); 
				long tagIdInMMTable = result.getLong(2);

				isTagsTasksLinkedCorrectly = (taskIdInMMTable == taskId && tagIdInMMTable == tag.getId());

				if(!isTagsTasksLinkedCorrectly) break;
				
			}
		

			if( taskId > 0 && isTagsTasksLinkedCorrectly)
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