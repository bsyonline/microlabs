package com.rolex.microlabs.service;

import com.rolex.microlabs.model.PointInfo;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands;

import java.util.Collection;
import java.util.List;

/**
 * @author rolex
 * @since 2020
 */
public interface GeoOpsService {
    /**
     * 保存point
     *
     * @param points {@link PointInfo}
     * @return 成功保存的个数
     */
    Long savePoints(Collection<PointInfo> points);

    /**
     * 获取point
     *
     * @param points 给定城市 key
     * @return {@link Point}s
     */
    List<Point> getPoints(String[] points);

    /**
     * 两点之间的距离
     *
     * @param p1
     * @param p2
     * @param metric {@link Metric} 单位信息, 可以是 null
     * @return {@link Distance}
     */
    Distance getTwoPointsDistance(String p1, String p2, Metric metric);

    /**
     * 根据给定地理位置坐标获取指定范围内的地理位置集合
     *
     * @param within {@link Circle} 中心点和距离
     * @param args   {@link RedisGeoCommands.GeoRadiusCommandArgs} 限制返回的个数和排序方式, 可以是 null
     * @return {@link RedisGeoCommands.GeoLocation}
     */
    GeoResults<RedisGeoCommands.GeoLocation<String>> getPointRadius(
            Circle within, RedisGeoCommands.GeoRadiusCommandArgs args);

    /**
     * 根据给定地理位置获取指定范围内的地理位置集合
     */
    GeoResults<RedisGeoCommands.GeoLocation<String>> getMemberRadius(
            String member, Distance distance, RedisGeoCommands.GeoRadiusCommandArgs args);

    /**
     * 获取某个地理位置的 geohash 值
     *
     * @param cities 给定城市 key
     * @return city geohashs
     */
    List<String> getCityGeoHash(String[] cities);
}
