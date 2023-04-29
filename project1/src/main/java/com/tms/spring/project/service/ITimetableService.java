package com.tms.spring.project.service;

import com.tms.spring.project.model.User;
import com.tms.spring.project.model.Timetable;
import java.util.List;

public interface ITimetableService
{
	Timetable GetTimetable( string weekAsDate, User ownerOfTasksToShow );
}