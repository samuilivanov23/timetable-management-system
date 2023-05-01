package com.tms.spring.project.service;
import java.util.List;
import com.tms.spring.project.model.Tag;
import com.tms.spring.project.model.Task;
import com.tms.spring.project.model.User;


public interface ITagService
{
	boolean CreateTag( Tag tag,  User loggedInUser );
    List<Tag> GetAllTags();
    List<Tag> GetTagsForTask( Task task );
}
