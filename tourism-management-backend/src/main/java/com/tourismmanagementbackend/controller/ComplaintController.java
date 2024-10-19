package com.tourismmanagementbackend.controller;

import com.tourismmanagementbackend.dto.ComplaintDto;
import com.tourismmanagementbackend.entity.Complaint;
import com.tourismmanagementbackend.service.ComplaintService;
import com.tourismmanagementbackend.utils.PageUtils;
import com.tourismmanagementbackend.utils.result.DataResult;
import com.tourismmanagementbackend.utils.result.code.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 投诉(Complaint)表控制层
 *
 * @author makejava
 * @since 2024-07-13 15:12:05
 */
@RestController
@RequestMapping("complaint")
public class ComplaintController {

    @Autowired
    private ComplaintService complaintService;

    @RequestMapping("getComplaintListByType")
    public DataResult getComplaintListByType(@RequestBody ComplaintDto complaintDto){
        complaintDto.setPage(PageUtils.getStartPage(complaintDto.getPage(),complaintDto.getLimit()));
        List<Complaint> complaintList = complaintService.getComplaintListByType(complaintDto);
        long countPage = complaintService.countPage(complaintDto);
        return DataResult.successByTotalData(complaintList,countPage);
    }

    @RequestMapping("deleteComplaint/{complaintId}")
    public DataResult deleteComplaint(@PathVariable("complaintId") int complaintId){
        int i = complaintService.deleteComplaint(complaintId);
        if (i > 0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.DELETE_STUDENT_ERROR);
        }
    }

    @RequestMapping("getComplaintListByUsername")
    public DataResult getComplaintListByUsername(@RequestBody ComplaintDto complaintDto){
        complaintDto.setPage(PageUtils.getStartPage(complaintDto.getPage(),complaintDto.getLimit()));
        List<Complaint> complaintList = complaintService.getComplaintListByUsername(complaintDto);
        long countPage = complaintService.countPageNew(complaintDto);
        return DataResult.successByTotalData(complaintList,countPage);
    }

    @RequestMapping("addComplaint")
    public DataResult addComplaint(@RequestBody Complaint complaint){
        int i = complaintService.addComplaint(complaint);
        if (i > 0){
            return DataResult.succ();
        }else {
            return DataResult.errByErrCode(Code.ADD_STUDENT_ERROR);
        }
    }

    @RequestMapping("getScenicAreas")
    public DataResult getScenicAreas(){
        return DataResult.successByDataArray(complaintService.getScenicAreas());
    }

    @RequestMapping("getHotels")
    public DataResult getHotels(){
        return DataResult.successByDataArray(complaintService.getHotels());
    }
}

