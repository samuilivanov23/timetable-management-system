package com.tms.spring.project.controller;

import com.tms.spring.project.model.User;
import com.tms.spring.project.model.Timetable;
import com.tms.spring.project.controller.HomeController;
import com.tms.spring.project.service.ITimetableService;
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
public class TimetableController
{
	@Autowired
	private ITimetableService timetableService;

	@GetMapping( "/ShowTimetable" )
	public String Index( Model model )
	{
		try
		{
			Timetable timetable = timetableService.GetTimetable( "2029-04-29", HomeController.loggedInUser );
			System.out.println(timetable.getDaysOfWeek());
			for( var currentDay : timetable.getDaysOfWeek() )
			{
				System.out.println(currentDay.getName());
				System.out.println(currentDay.getTasks());
			}

			model.addAttribute( "loggedInUser", HomeController.loggedInUser );
			model.addAttribute( "timetable", timetable.getDaysOfWeek() );
		}
		catch( Exception exception )
		{
			exception.printStackTrace();
		}

		return "Timetable";
	}
}