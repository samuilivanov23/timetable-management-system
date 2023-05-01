package com.tms.spring.project.controller;

import com.tms.spring.project.model.Tag;
import com.tms.spring.project.model.Task;
import com.tms.spring.project.service.ITagService;
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
public class TagController
{
	@Autowired
	private ITagService tagService;

	@GetMapping( "/CreateTagView" )
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

		return "CreateTagView";
	}

	@PostMapping( "/CreateTagAction" )
	@ResponseBody
	public boolean CreateTagSubmission( @ModelAttribute Tag tag)
	{
		boolean isTagCreatedSuccessfully = false;
		try
		{
			isTagCreatedSuccessfully = tagService.CreateTag(tag);
		}
		catch( Exception exception ) 
		{ 
			exception.printStackTrace();
	   	}

		return isTagCreatedSuccessfully;
	}
}