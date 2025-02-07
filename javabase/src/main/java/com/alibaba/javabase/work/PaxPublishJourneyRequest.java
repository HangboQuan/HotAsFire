package com.alibaba.javabase.work;

/**
 * @author quanhangbo
 * @date 2025-01-20 22:20
 */
public class PaxPublishJourneyRequest extends BaseRequest {

    private String startLon;

    private String startLat;

    private String startLongAddress;

    private String startShortAddress;

    private Integer highWayFee;

    private String orderNo;

    /**
     * 乘客预计收到的车费
     */
    private Integer driverProFarePrice;

    /**
     * 乘客预计收到的拼车车费
     */
    private Integer driverProPoolFarePrice;

    /**
     * 乘客里程碑
     */
    private Integer mileagePrice;
}
