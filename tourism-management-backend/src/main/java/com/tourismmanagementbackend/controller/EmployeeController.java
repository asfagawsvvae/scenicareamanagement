package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.EmployeeDto;
import com.tourismmanagementbackend.entity.Employee;
import com.tourismmanagementbackend.service.EmployeeService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Employee)表控制层
 *
 * @author makejava
 * @since 2024-07-11 19:46:32
 */
@RestController
@RequestMapping("employee")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    // 展示所有的员工/根据员工姓名展示指定员工
    @RequestMapping("/getEmployeeList")
    public DataResult getEmployeeList(@RequestBody EmployeeDto employeeDto){
        // 计算分页存储
        employeeDto.setPage(PageUtils.getStartPage(employeeDto.getPage(), employeeDto.getLimit()));
        // 获取员工列表
        List<Employee> allEmployee = employeeService.getEmployeeList(employeeDto);
        // 获取员工总数
        long countPage = employeeService.countPage(employeeDto);
        return DataResult.successByTotalData(allEmployee, countPage);
    }

    // 删除/解雇员工
    @RequestMapping("/deleteEmployeeByEmployeeID/{id}")
    public DataResult deleteEmployeeByEmployeeID(@PathVariable("id") int id){
        int i = employeeService.deleteEmployeeByEmployeeID(id);
        if(i>0){
            return DataResult.succ();
        } else{
            return DataResult.errByErrCode(Code.DELETE_EMPLOYEE_ERROR);
        }
    }

    // 添加员工
    @RequestMapping("/addEmployee")
    public DataResult addEmployee(@RequestBody Employee employee){
        int i = employeeService.addEmployee(employee);
        if(i>0){
            return DataResult.succ();
        } else{
            return DataResult.errByErrCode(Code.ADD_EMPLOYEE_ERROR);
        }
    }

    // 修改员工信息
    @RequestMapping("/updateEmployee")
    public DataResult updateEmployee(@RequestBody Employee employee){
        int i = employeeService.updateEmployee(employee);
        if(i>0){
            return DataResult.succ();
        } else{
            return DataResult.errByErrCode(Code.UPDATE_EMPLOYEE_ERROR);
        }
    }

    // 获取职称名单
    @RequestMapping("/getJobList")
    public DataResult getJobList(){
        List<String> allJob = employeeService.getJobList();
        return DataResult.successByDataArray(allJob);
    }

    // 获取工作地点列表
    @RequestMapping("/getLocationList")
    public DataResult getLocationList() {
        List<String> allLocation = employeeService.getLocationList();
        return DataResult.successByDataArray(allLocation);
    }

    // 单独获取救援人员列表
    @RequestMapping("/getRescuerList")
    public DataResult getRescuerList(@RequestBody EmployeeDto employeeDto){
        // 计算分页存储
        employeeDto.setPage(PageUtils.getStartPage(employeeDto.getPage(), employeeDto.getLimit()));
        // 获取救援员工列表
        List<Employee> allRescuer = employeeService.getRescuerList(employeeDto);
        // 获取救援员工总数
        long countPage = employeeService.countRescuerPage(employeeDto);
        return DataResult.successByTotalData(allRescuer, countPage);
    }
}