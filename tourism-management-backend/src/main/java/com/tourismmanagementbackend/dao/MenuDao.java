package com.tourismmanagementbackend.dao;

import com.tourismmanagementbackend.entity.Menu;
import com.tourismmanagementbackend.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;
import java.util.List;

/**
 * (Menu)表数据库访问层
 *
 * @author makejava
 * @since 2024-07-10 17:29:09
 */
public interface MenuDao {

    List<Menu>getMenusByUserType(User user);
}

