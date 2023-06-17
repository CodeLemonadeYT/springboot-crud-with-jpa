package com.codelemonade.springboot.cruddemo.dao;

import com.codelemonade.springboot.cruddemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    //Nothing needed for crud methods
}
