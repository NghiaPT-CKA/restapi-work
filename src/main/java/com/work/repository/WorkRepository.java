package com.work.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.work.entity.Work;

/**
 * Work repository
 * 
 * @author NghiaPT12
 *
 */
@Repository
public interface WorkRepository extends JpaRepository<Work, Integer> {

}
