package com.shikharani.onlinelearningplatform.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.shikharani.onlinelearningplatform.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
