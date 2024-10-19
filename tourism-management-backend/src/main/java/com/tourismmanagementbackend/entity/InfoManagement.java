package com.tourismmanagementbackend.entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (Infomanagement)实体类
 *
 * @author makejava
 * @since 2024-07-14 17:07:06
 */
public class InfoManagement implements Serializable {
    private static final long serialVersionUID = -96302727616827519L;

    private Integer infoId;

    private String info;

    private String timeStamp;

    private Integer published;

    public Integer getInfoId() {
        return infoId;
    }

    public void setInfoId(Integer infoId) {
        this.infoId = infoId;
    }


    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }



    public Integer getPublished() {
        return published;
    }

    public void setPublished(Integer published) {
        this.published = published;
    }

}

