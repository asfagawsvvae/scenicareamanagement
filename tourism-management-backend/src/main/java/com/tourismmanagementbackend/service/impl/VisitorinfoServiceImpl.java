package com.tourismmanagementbackend.service.impl;

import com.tourismmanagementbackend.dto.VisitorinfoDto;
import com.tourismmanagementbackend.entity.Visitorinfo;
import com.tourismmanagementbackend.dao.VisitorinfoDao;
import com.tourismmanagementbackend.service.VisitorinfoService;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import javax.annotation.Resource;
import java.util.List;

/**
 * 游客信息表，全(Visitorinfo)表服务实现类
 *
 * @author makejava
 * @since 2024-07-13 16:03:33
 */
@Service("visitorinfoService")
public class VisitorinfoServiceImpl implements VisitorinfoService {

    @Resource
    private VisitorinfoDao visitorinfoDao;

    @Override
    public List<Visitorinfo> getVisitorinfoByVisitorNameList(VisitorinfoDto visitorinfoDto){
        return visitorinfoDao.getVisitorinfoByVisitorNameList(visitorinfoDto);
    }

    @Override
    public int countPage(VisitorinfoDto visitorinfoDto){
        return visitorinfoDao.countPage(visitorinfoDto);
    }

    @Override
    public int deleteVisitorinfo(String username){
        return visitorinfoDao.deleteVisitorinfo(username);
    }
    @Override
    public   int getVisitorParkingNum(Visitorinfo visitorinfo){return visitorinfoDao.getVisitorParkingNum(visitorinfo);}
    @Override
    public   int getVisitorHotelParkingNum(Visitorinfo visitorinfo){return visitorinfoDao.getVisitorParkingNum(visitorinfo);}
}
