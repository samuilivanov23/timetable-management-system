package com.tms.spring.project.controller;

import com.tms.spring.project.model.User;
import com.tms.spring.project.model.Task;
import com.tms.spring.project.service.ITaskService;
import com.tms.spring.project.controller.HomeController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Controller
public class TaskController
{
	@Autowired
	private ITaskService taskService;

	@GetMapping( "/CreateTaskView" )
	public String Index( Model model )
	{
		try
		{
			model.addAttribute( "loggedInUser", HomeController.loggedInUser );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}

		return "CreateTaskView";
	}

	@PostMapping( "/CreateTaskAction" )
	@ResponseBody
	public boolean CreateTaskSubmission( @ModelAttribute Task task )
	{
		System.out.println( task );

		boolean isTaskCreatedSuccessfully = false;
		try
		{
			isTaskCreatedSuccessfully = taskService.CreateTask( task, HomeController.loggedInUser );
		}
		catch( Exception exception ) 
		{ 
			exception.printStackTrace();
	   	}

		return isTaskCreatedSuccessfully;
	}
}