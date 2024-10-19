package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.EmployeeDto;
import com.tourismmanagementbackend.entity.Employee;

import java.util.List;

/**
 * (Employee)表服务接口
 *
 * @author makejava
 * @since 2024-07-11 19:46:33
 */
public interface EmployeeService {

    List<Employee> getEmployeeList(EmployeeDto employeeDto);

    int countPage(EmployeeDto employeeDto);

    int deleteEmployeeByEmployeeID(int id);

    int addEmployee(Employee employee);

    int updateEmployee(Employee employee);

    List<String> getJobList();

    List<String> getLocationList();

    List<Employee> getRescuerList(EmployeeDto employeeDto);

    int countRescuerPage(EmployeeDto employeeDto);
}