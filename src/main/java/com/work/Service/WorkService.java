package com.work.Service;

import java.util.List;

import com.work.entity.Work;

/**
 * Work service
 * 
 * @author NghiaPT12
 *
 */
public interface WorkService {

	Work save(Work work);

	Work update(Work work);

	void delete(Integer id);

	Work getById(Integer id);

	List<Work> getAll();

	boolean isExist(Integer id);
}
