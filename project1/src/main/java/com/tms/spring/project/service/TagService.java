package com.tms.spring.project.service;

import com.tms.spring.project.model.Tag;
import com.tms.spring.project.model.Task;
import com.tms.spring.project.repository.TagRepository;
import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

@Service
public class TagService implements ITagService 
{
	@Autowired
	private TagRepository tagRepository;

	@Override
	public boolean CreateTag( Tag tag, Task task )
	{
		long taskId = task.getId();
		return tagRepository.CreateTag( tag, taskId );
	}

    @Override
    List<Tag> GetAllTags()
    {
        return tagRepository.GetAllTags();
    }

    @Override
    List<Tag> GetTagsForTask(Task task )
    {
        long taskId = task.getId();
        return tagRepository.GetTagsForTask(taskId)
    }
}
