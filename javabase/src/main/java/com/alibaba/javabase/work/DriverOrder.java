package com.alibaba.javabase.work;

import java.util.Date;
import java.util.List;

/**
 * @author quanhangbo
 * @date 2025-01-20 20:56
 */
public class DriverOrder implements Aggregate<DriverOrderId> {

    private DriverOrderId id;

    private EnumDriverOrderStatus orderStatus;

    private List<PaxOrder> paxOrderList;

    private GeoInfo startGeoInfo;

    private GeoInfo endGeoInfo;

    private TimeInfo planTimeInfo;

    private String orderId;

    private Date createTime;

    private Date updateTime;

    /**
     * 发单相关
     */
    private PublishInfo publishInfo;

    /**
     * 选单信息
     */
    private JourneyPickedInfo pickedInfo;

    /**
     * 取消信息
     */
    private JourneyCanceledInfo canceledInfo;


//    private JourneyFinishedInfo finishedInfo;

    /**
     * 接单信息
     */
    private JourneyMatchedInfo matchedInfo;




    /**
     * 有效乘客单号
     */
    private List<String> validPassengerOrderNoList;



    @Override
    public DriverOrderId getId() {
        return null;
    }

    public String fetchDriverOrderId() {
        if (id == null) {
            return null;
        }
        return id.getId();
    }
}
