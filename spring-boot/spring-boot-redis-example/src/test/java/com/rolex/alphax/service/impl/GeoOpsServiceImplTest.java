package com.rolex.alphax.service.impl;

import com.rolex.alphax.model.PointInfo;
import com.rolex.alphax.service.GeoOpsService;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.geo.*;
import org.springframework.data.redis.connection.RedisGeoCommands.GeoRadiusCommandArgs;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author rolex
 * @since 2020
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class GeoOpsServiceImplTest {
    private List<PointInfo> points;
    @Autowired
    private GeoOpsService geoService;

    @Before
    public void init() {
        points = new ArrayList<>();
        points.add(new PointInfo("p1", 117.17, 31.52));
        points.add(new PointInfo("p2", 117.02, 30.31));
        points.add(new PointInfo("p3", 116.47, 33.57));
        points.add(new PointInfo("p4", 116.58, 33.38));
        points.add(new PointInfo("p5", 115.48, 32.54));
        points.add(new PointInfo("p6", 117.21, 32.56));
        points.add(new PointInfo("p7", 118.18, 29.43));
    }

    @Test
    public void test01SavePoints() {
        geoService.savePoints(points);
        List<Point> list = geoService.getPoints(new String[]{"p1"});
        assertEquals(117.17, list.get(0).getX(), 2);
        assertEquals(31.52, list.get(0).getY(), 2);
    }

    @Test
    public void test02GetPoints() {
    }

    @Test
    public void test03GetTwoPointsDistance() {
        System.out.println(geoService.getTwoPointsDistance("p1", "p2", Metrics.KILOMETERS));
    }

    @Test
    public void test04GetPointRadius() {
        Point point = new Point(points.get(0).getLongitude(),points.get(0).getLatitude());
        Distance radius = new Distance(200, Metrics.KILOMETERS);
        Circle circle = new Circle(point, radius);
        System.out.println(geoService.getPointRadius(circle, null));
        System.out.println(geoService.getPointRadius(circle, GeoRadiusCommandArgs.newGeoRadiusArgs().includeDistance().limit(2).sortAscending()));
    }

    @Test
    public void test05GetMemberRadius() {
        Distance radius = new Distance(200, Metrics.KILOMETERS);
        System.out.println(geoService.getMemberRadius("p3", radius, null));
    }

    @Test
    public void test06GetCityGeoHash() {
        System.out.println(geoService.getCityGeoHash(Arrays.asList("p4", "p5", "p9").toArray(new String[3])));
    }
}