package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.dto.ComplaintDto;
import com.tourismmanagementbackend.entity.Complaint;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * 投诉(Complaint)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-13 15:12:05
 */
public interface ComplaintDao {
    List<Complaint> getComplaintListByType(ComplaintDto complaintDto);

    int countPage(ComplaintDto complaintDto);

    int deleteComplaint(int complaintId);

    List<Complaint> getComplaintListByUsername(ComplaintDto complaintDto);

    int addComplaint(Complaint complaint);

    List<String> getScenicAreas();

    List<String> getHotels();

    int countPageNew(ComplaintDto complaintDto);

}

