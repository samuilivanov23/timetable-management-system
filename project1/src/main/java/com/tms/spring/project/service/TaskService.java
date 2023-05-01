package com.tms.spring.project.service;

import com.tms.spring.project.model.Task;
import com.tms.spring.project.model.User;
import com.tms.spring.project.repository.TaskRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

@Service
public class TaskService implements ITaskService 
{
	@Autowired
	private TaskRepository taskRepository;

	@Override
	public boolean CreateTask( Task task, User loggedInUser, List<Tag> tags )
	{
		long userId = loggedInUser.getId();
		return taskRepository.CreateTask( task, userId, tags );
	}
}
