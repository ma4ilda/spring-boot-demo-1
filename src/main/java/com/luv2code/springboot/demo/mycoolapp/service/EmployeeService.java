package com.luv2code.springboot.demo.mycoolapp.service;

import com.luv2code.springboot.demo.mycoolapp.entity.Employee;

public interface EmployeeService {

    Iterable<Employee> findAll();

    Employee findById(String theId);

    Employee save(Employee theEmployee);

    Employee updateById(String theId, Employee theEmployee);

    void deleteById(String theId);
}
