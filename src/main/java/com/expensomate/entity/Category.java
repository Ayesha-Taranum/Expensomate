package com.expensomate.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 */
@Entity
@Table(name="CATEGORY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category {
	@Id
	@Column(name="CATEGORYID")
	private String categoryID;

	@Column(name="CATEGORY_NAME")
	private String categoryName;
}
