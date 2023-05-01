package com.tms.spring.project.controller;

import com.tms.spring.project.model.User;
import com.tms.spring.project.model.Task;
import com.tms.spring.project.model.Tag;
import com.tms.spring.project.service.ITaskService;
import com.tms.spring.project.service.ITagService;
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
	
	@Autowired
	private ITagService tagService;

	@GetMapping( "/CreateTaskView" )
	public String Index( Model model )
	{
		try
		{
			List<Tag> allTags = tagService.GetAllTags();
			model.addAttribute( "loggedInUser", HomeController.loggedInUser );
			model.addAttribute( "allAvailableTags", allTags );
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
		boolean isTaskCreatedSuccessfully = false;
		try
		{
			System.out.println(task);
			isTaskCreatedSuccessfully = taskService.CreateTask( task, HomeController.loggedInUser, task.getTags() );
		}
		catch( Exception exception ) 
		{ 
			exception.printStackTrace();
	   	}

		return isTaskCreatedSuccessfully;
	}
}