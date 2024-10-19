package com.tourismmanagementbackend.service;

import com.tourismmanagementbackend.dto.ComplaintDto;
import com.tourismmanagementbackend.entity.Complaint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * 投诉(Complaint)表服务接口
 *
 * @author makejava
 * @since 2024-07-13 15:12:05
 */
public interface ComplaintService {

    List<Complaint> getComplaintListByType(ComplaintDto complaintDto);

    int countPage(ComplaintDto complaintDto);

    int deleteComplaint(int complaintId);

    List<Complaint> getComplaintListByUsername(ComplaintDto complaintDto);

    int addComplaint(Complaint complaint);

    List<String> getScenicAreas();

    List<String> getHotels();

    int countPageNew(ComplaintDto complaintDto);

}
