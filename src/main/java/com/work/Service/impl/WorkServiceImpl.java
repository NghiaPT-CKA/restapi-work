package com.work.Service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.work.Service.WorkService;
import com.work.entity.Work;
import com.work.repository.WorkRepository;
import com.work.util.DateTimeUtils;

/**
 * Work service implement
 * 
 * @author NghiaPT
 *
 */
@Service
public class WorkServiceImpl implements WorkService {

	/** Work repository */
	@Autowired
	private WorkRepository repo;

	@Override
	public boolean isExist(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public Work save(Work work) {
		work.setCreateDate(LocalDateTime.now());
		return repo.save(work);
	}

	@Override
	public Work update(Work work) {
		Work workUpdate = repo.getById(work.getId());

		if (work.getName() != null) {
			workUpdate.setName(work.getName());
		}

		if (work.getStartingDate() != null) {
			workUpdate.setStartingDate(work.getStartingDate());
		}

		if (work.getEndingDate() != null) {
			workUpdate.setEndingDate(work.getEndingDate());
		}

		if (work.getStatus() != null) {
			workUpdate.setStatus(work.getStatus());
		}

		workUpdate.setUpdateDate(LocalDateTime.now());
		return repo.save(workUpdate);
	}

	@Override
	public void delete(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Work getById(Integer id) {
		return repo.getById(id);
	}

	@Override
	public List<Work> getAll() {
		return repo.findAll();
	}

}
