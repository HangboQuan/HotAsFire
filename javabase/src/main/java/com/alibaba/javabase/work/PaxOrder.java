package com.alibaba.javabase.work;

import lombok.Data;

import java.util.Date;

/**
 * @author quanhangbo
 * @date 2025-01-20 20:02
 */
@Data
public class PaxOrder implements Aggregate<PaxOrderId> {

    private PaxOrderId id;

    /**
     * 乘客订单状态
     */
    private EnumPassengerOrderStatus orderStatus;
    /**
     * 行程类型
     */
    private EnumPaxJourneyType journeyType;

    /**
     * 乘客信息
     */
    private PaxUserInfo paxUserInfo;

    private TimeInfo timeInfo;

    /**
     * 发单相关
     */
    private PublishInfo publishInfo;

    /**
     * 接单相关
     */
    private JourneyMatchedInfo journeyMatchedInfo;

    /**
     * 取消相关
     */
    private JourneyCanceledInfo canceledInfo;

    private GeoInfo startGeoInfo;

    private GeoInfo endGeoInfo;

    /**
     * 订单号
     */
    private String orderId;

    private Date createTime;

    private Date updateTime;

    private Journey journey;

    private EnumReceiveSourceType receiveSourceType;

    /**
     * 优惠券信息
     */
//    private CouponInfo couponInfo;

    /**
     * 乘客支付信息
     */
//    private PayInfo payInfo;




    @Override
    public PaxOrderId getId() {
        return null;
    }
}
