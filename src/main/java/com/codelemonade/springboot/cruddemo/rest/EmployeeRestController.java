package com.codelemonade.springboot.cruddemo.rest;

import com.codelemonade.springboot.cruddemo.entity.Employee;
import com.codelemonade.springboot.cruddemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class EmployeeRestController {

   private EmployeeService employeeService;

    //directly inject employee dao
    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService){
        employeeService = theEmployeeService;
    }
    //return a list of employees
    @GetMapping("/employees")
    public List<Employee> findAll(){
        return employeeService.findAll();
    }

    @GetMapping("/employees/{employeeID}")
    public Employee getEmployee(@PathVariable int employeeID){
        Employee theEmployee = employeeService.findById(employeeID);

        if(theEmployee == null){
            throw new RuntimeException("Employee id not found - "+ employeeID);
        }
        return theEmployee;
    }

    //add mapping for employees
    @PostMapping("/employees")
    public Employee addEmployee(@RequestBody Employee theEmployee){

        //add just in case they pass an id in JSON ... set id to 0
        //this is to force a save of new item instead of update
        theEmployee.setId(0);

        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //add mapping for PUT/employees - update existing employees
    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee theEmployee){
        Employee dbEmployee = employeeService.save(theEmployee);

        return dbEmployee;
    }

    //add madding for deleting
    @DeleteMapping("/employees/{employeeId}")
    public String deleteEmployee(@PathVariable int employeeId){
        Employee tempEmployee = employeeService.findById(employeeId);

        //throw exception if null
        if(tempEmployee == null){
            throw new RuntimeException(("Employee id not found - "+ employeeId));
        }

        employeeService.deleteById(employeeId);
        return "Deleted employee id - "+employeeId;
    }

}
