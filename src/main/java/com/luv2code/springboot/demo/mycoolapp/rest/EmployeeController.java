package com.luv2code.springboot.demo.mycoolapp.rest;

import com.luv2code.springboot.demo.mycoolapp.entity.Employee;
import com.luv2code.springboot.demo.mycoolapp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/listAll")
    public Iterable<Employee> listEmployees() {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee getEmployee(@PathVariable String employeeId) {
        return employeeService.findById(employeeId);
    }

    @PostMapping("/")
    public Employee addEmployee(@RequestBody Employee employee) {
        return employeeService.save(employee);
    }

    @DeleteMapping("/{employeeId}")
    public void deleteEmployeeByID(@PathVariable String employeeId) {
        employeeService.deleteById(employeeId);
    }

    @PutMapping("/{employeeId}")
    public Employee updateEmployeeByID(@PathVariable String employeeId, @RequestBody Employee employee) {
        return employeeService.updateById(employeeId, employee);
    }
}
