package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dao.EmployeeDao;
import com.tourismmanagementbackend.dto.EmployeeDto;
import com.tourismmanagementbackend.entity.Employee;
import com.tourismmanagementbackend.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Employee)表服务实现类
 *
 * @author makejava
 * @since 2024-07-11 19:46:33
 */
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public List<Employee> getEmployeeList(EmployeeDto employeeDto) {
        return employeeDao.getReviewList(employeeDto);
    }

    @Override
    public int countPage(EmployeeDto employeeDto) {
        return employeeDao.countPage(employeeDto);
    }

    @Override
    public int deleteEmployeeByEmployeeID(int id) {
        return employeeDao.deleteEmployeeByEmployeeID(id);
    }

    @Override
    public int addEmployee(Employee employee) {
        return employeeDao.addEmployee(employee);
    }

    @Override
    public int updateEmployee(Employee employee) {
        return employeeDao.updateEmployee(employee);
    }

    @Override
    public List<String> getJobList() {
        return employeeDao.getJobList();
    }

    @Override
    public List<String> getLocationList() {
        return employeeDao.getLocationList();
    }

    @Override
    public List<Employee> getRescuerList(EmployeeDto employeeDto) {
        return employeeDao.getRescuerList(employeeDto);
    }

    @Override
    public int countRescuerPage(EmployeeDto employeeDto) {
        return employeeDao.countRescuerPage(employeeDto);
    }
}