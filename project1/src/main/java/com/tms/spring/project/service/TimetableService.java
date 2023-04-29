package com.tms.spring.project.service;

import com.tms.spring.project.model.Task;
import com.tms.spring.project.model.User;
import com.tms.spring.project.model.Timetable;
import com.tms.spring.project.repository.TimetableRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

@Service
public class TimetableService implements ITimetableService 
{
	@Autowired
	private TimetableRepository timetableRepository;

	@Override
	public Timetable GetTimetable( string weekAsDate, User ownerOfTasksToShow )
	{
		long userId = ownerOfTasksToShow.getId();
		Timetable timetable = timetableRepository.GetTimetable( weekAsDate, userId );
		
		return timetable;
	}
}