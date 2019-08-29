package com.luv2code.springboot.demo.mycoolapp.service;

import java.util.Optional;

import com.luv2code.springboot.demo.mycoolapp.exception.BadRequestException;
import com.luv2code.springboot.demo.mycoolapp.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import com.luv2code.springboot.demo.mycoolapp.dao.EmployeeRepository;
import com.luv2code.springboot.demo.mycoolapp.entity.Employee;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Override
    public Iterable<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(String theId) {
        return employeeRepository.findById(new ObjectId(theId)).orElseThrow(() -> new NotFoundException("Employee not found with id - " + theId));
    }

    @Override
    public Employee save(Employee theEmployee) {
        Optional.ofNullable(theEmployee.getEmail()).filter(email -> !email.trim().isEmpty()).orElseThrow(() -> new BadRequestException("Email is required"));
        return employeeRepository.save(theEmployee);
    }

    @Override
    public void deleteById(String theId) {
        this.findById(theId);
        employeeRepository.deleteById(new ObjectId(theId));
    }

    @Override
    public Employee updateById(String theId, Employee employee) {
        this.findById(theId);
        employee.setId(new ObjectId(theId));
        return this.save(employee);
    }

}






