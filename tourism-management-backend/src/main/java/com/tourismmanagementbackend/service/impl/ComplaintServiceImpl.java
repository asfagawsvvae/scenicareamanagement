package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dto.ComplaintDto;
import com.tourismmanagementbackend.entity.Complaint;
import com.tourismmanagementbackend.dao.ComplaintDao;
import com.tourismmanagementbackend.service.ComplaintService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 投诉(Complaint)表服务实现类
 *
 * @author makejava
 * @since 2024-07-13 15:12:05
 */
@Service("complaintService")
public class ComplaintServiceImpl implements ComplaintService {
    @Resource
    private ComplaintDao complaintDao;

    @Override
    public List<Complaint> getComplaintListByType(ComplaintDto complaintDto) {
        return complaintDao.getComplaintListByType(complaintDto);
    }

    @Override
    public int countPage(ComplaintDto complaintDto) {
        return complaintDao.countPage(complaintDto);
    }

    @Override
    public int deleteComplaint(int complaintId) {
        return complaintDao.deleteComplaint(complaintId);
    }

    @Override
    public List<Complaint> getComplaintListByUsername(ComplaintDto complaintDto) {
        return complaintDao.getComplaintListByUsername(complaintDto);
    }

    @Override
    public int addComplaint(Complaint complaint) {
        return complaintDao.addComplaint(complaint);
    }

    @Override
    public List<String> getScenicAreas() {
        return complaintDao.getScenicAreas();
    }

    @Override
    public List<String> getHotels() {
        return complaintDao.getHotels();
    }

    @Override
    public int countPageNew(ComplaintDto complaintDto) {
        return complaintDao.countPageNew(complaintDto);
    }
}
