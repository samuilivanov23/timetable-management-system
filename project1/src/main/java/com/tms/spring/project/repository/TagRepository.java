package com.tms.spring.project.repository;

import com.tms.spring.project.model.Tag;
import com.tms.spring.project.model.Task;
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
public class TagRepository 
{
	public boolean CreateTag( Tag tag, long userId )
	{
		boolean isTagCreatedSuccessfully = false;
		PreparedStatement statement = null;
		Statement transactionStatement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase();
		
		try
		{
			transactionStatement = dbConn.createStatement();
			transactionStatement.executeUpdate( "BEGIN" );

			statement = dbConn.prepareStatement( "INSERT INTO tags (name, user_id) VALUES(?, ?) RETURNING id" );
			statement.setString( 1, tag.getName() );
            statement.setLong( 2, userId );

			result = statement.executeQuery();	
			result.next();
			long tagId = result.getLong( 1 );

			if( tagId > 0 )
			{
				isTagCreatedSuccessfully = true;
				transactionStatement.executeUpdate( "COMMIT" );
			}
			else
			{
				isTagCreatedSuccessfully = false;
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

		return isTagCreatedSuccessfully;
	}

    public List<Tag> GetAllTags()
	{
		List<Tag> tags = null;
        Tag tmpTag = null;
		Statement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase();
		
		try
		{
			statement = dbConn.createStatement();
			result = statement.executeQuery( "SELECT * FROM tags" );
			tags = new ArrayList<Tag>();

			while( result.next() )
			{
				tmpTag = new Tag();
				tmpTag.setId( result.getLong( 1 ) );
				tmpTag.setName( result.getString( 2 ) );
                tmpTag.setUserId( result.getLong(3) );

				tags.add( tmpTag );
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
	
		return tags;
	}

    public List<Tag> GetTagsForTask(long taskId)
	{
		List<Tag> tags = null;
        Tag tmpTag = null;
		PreparedStatement statement = null;
		ResultSet result = null;
		Connection dbConn = DataBaseManager.ConnectToDatabase();
		
		try
		{
			statement = dbConn.prepareStatement( "SELECT id, name, user_id FROM tags AS tg JOIN tasks_tags AS tt ON tt.tag_id = tg.id WHERE tt.task_id = ?" );

			statement.setLong( 1, taskId );
			
			result = statement.executeQuery();
			tags = new ArrayList<Tag>();

			while( result.next() )
			{
				tmpTag = new Tag();
				tmpTag.setId( result.getLong( 1 ) );
				tmpTag.setName( result.getString( 2 ) );
                tmpTag.setUserId( result.getLong(3) );

				tags.add( tmpTag );
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
	
		return tags;
	}
}