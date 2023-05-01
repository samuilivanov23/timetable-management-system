package com.tms.spring.project.service;

public interface ITagService
{
	boolean CreateTask( Tag tag, Task task );
    List<Tag> GetAllTags();
    List<Tag> GetTagsForTask( Task task );
}
