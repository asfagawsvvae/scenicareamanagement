package com.tourismmanagementbackend.dto;


import com.tourismmanagementbackend.entity.Menu;
import com.tourismmanagementbackend.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


import java.util.List;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LoginUserDto {
    private User user;//用户
    private String token;//token
    private List<Menu> menus;//菜单
}

