package com.work.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.work.util.DateTimeUtils;

/**
 * Work entity
 * 
 * @author NghiaPT12
 *
 */
@Entity(name = "work")
@EnableAutoConfiguration
public class Work {
	/** Id */
	private Integer id;
	/** Name */
	private String name;
	/** Starting date */
	@JsonFormat(pattern = "yyyyMMdd")
	private LocalDate startingDate;
	/** Ending date */
	@JsonFormat(pattern = "yyyyMMdd")
	private LocalDate endingDate;
	/** Status */
	private String status;
	/** Create date */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createDate;
	/** Update date */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime updateDate;

	public Work() {
	}

	public Work(Map<String, String> inputMap, Integer id) {
		super();
		this.id = id;
		this.name = inputMap.get("name") == null ? null : inputMap.get("name");
		this.startingDate = inputMap.get("startingDate") == null ? null
				: DateTimeUtils.stringToLocalDate(inputMap.get("startingDate"));
		this.endingDate = inputMap.get("endingDate") == null ? null
				: DateTimeUtils.stringToLocalDate(inputMap.get("endingDate"));
		this.status = inputMap.get("status");
	}

	/**
	 * Get id
	 * 
	 * @return id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}

	/**
	 * Set id
	 * 
	 * @return id
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * Get name
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set name
	 * 
	 * @return name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get starting date
	 * 
	 * @return startingDate
	 */
	public LocalDate getStartingDate() {
		return startingDate;
	}

	/**
	 * Set starting date
	 * 
	 * @return startingDate
	 */
	public void setStartingDate(LocalDate startingDate) {
		this.startingDate = startingDate;
	}

	/**
	 * Get ending date
	 * 
	 * @return ending date
	 */
	public LocalDate getEndingDate() {
		return endingDate;
	}

	/**
	 * Set ending date
	 * 
	 * @return endingDate
	 */
	public void setEndingDate(LocalDate endingDate) {
		this.endingDate = endingDate;
	}

	/**
	 * Get status
	 * 
	 * @return status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Set status
	 * 
	 * @return status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Get create date
	 * 
	 * @return createDate
	 */
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	/**
	 * Set create date
	 * 
	 * @return createDate
	 */
	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	/**
	 * Get update date
	 * 
	 * @return updateDate
	 */
	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	/**
	 * Set update date
	 * 
	 * @return updateDate
	 */
	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}
}
