package com.tms.spring.project.service;
import java.util.List;
import com.tms.spring.project.model.Tag;
import com.tms.spring.project.model.Task;

public interface ITagService
{
	boolean CreateTag( Tag tag, Task task );
    List<Tag> GetAllTags();
    List<Tag> GetTagsForTask( Task task );
}
