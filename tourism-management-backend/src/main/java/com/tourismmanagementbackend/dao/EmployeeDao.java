package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.EmployeeDto;
import com.tourismmanagementbackend.entity.Employee;

import java.util.List;

/**
 * (Employee)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-11 19:46:32
 */
public interface EmployeeDao {

    // 获取员工列表
    List<Employee> getReviewList(EmployeeDto employeeDto);

    // 计算员工总数
    int countPage(EmployeeDto employeeDto);

    // 删除/解雇指定员工
    int deleteEmployeeByEmployeeID(int id);

    // 新增员工
    int addEmployee(Employee employee);

    // 修改员工信息
    int updateEmployee(Employee employee);

    // 获取职务名单
    List<String> getJobList();

    // 获取工作地点名单
    List<String> getLocationList();

    // 单独获取救援人员列表
    List<Employee> getRescuerList(EmployeeDto employeeDto);

    // 计算救援人员总数
    int countRescuerPage(EmployeeDto employeeDto);
}