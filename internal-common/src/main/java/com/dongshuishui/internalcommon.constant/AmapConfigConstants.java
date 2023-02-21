package com.dongshuishui.internalcommon.constant;

/**
 * @Author: 东水水
 * @Date: 2023/2/14  17:59
 * @Description: com.dongshuishui.internalcommon.constant
 * @Version: 1.0
 */
public class AmapConfigConstants {
    /**
     * 路径规划地址
     */
    public static final String DIRECTION_URL = "https://restapi.amap.com/v3/direction/driving";

    /**
     * 行政区域查询地址
     */
    public static final String DISTRICT_RUL = "https://restapi.amap.com/v3/config/district";

    /**
     * 猎鹰-创建服务地址
     */
    public static final String SERVICE_ADD_URL = "https://tsapi.amap.com/v1/track/service/add";

    /**
     * 猎鹰-创建终端地址
     */
    public static final String TERMINAL_ADD_RUL = "https://tsapi.amap.com/v1/track/terminal/add";

    /**
     * 猎鹰-创建终端轨迹地址
     */
    public static final String TRACK_ADD_RUL = "https://tsapi.amap.com/v1/track/trace/add";

    /**
     * 猎鹰-轨迹点上传（单点、批量）地址
     */

    public static final String POINT_UPLOAD = "https://tsapi.amap.com/v1/track/point/upload";

    /**
     * 猎鹰-周边搜索终端地址
     */
    public static final String TERMINAL_AROUND_SEARCH = "https://tsapi.amap.com/v1/track/terminal/aroundsearch";



    /**
     * 路径规划key值
     */
    public static final String STATUS = "status";

    public static final String ROUTE = "route";

    public static final String PATHS = "paths";

    public static final String DISTANCE = "distance";

    public static final String DURATION = "duration";

    /**
     * 地图json key值
     */
    public static final String DISTRICTS = "districts";

    public static final String ADCODE = "adcode";

    public static final String NAME = "name";

    public static final String LEVEL = "level";

    public static final String STREET = "street";

}
