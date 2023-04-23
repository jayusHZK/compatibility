package com.jayus.test;

import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GeodeticCurve;
import org.gavaghan.geodesy.GlobalCoordinates;

import java.math.BigDecimal;

/**
 * @author : h zk
 * @date : 2023/4/21 15:21
 * @description :
 **/
public class LongAndLoaiTest {

    public static void main(String[] args) {

        BigDecimal bigDecimal = new BigDecimal(12501.256).divide(new BigDecimal(1000))
                .setScale(1, BigDecimal.ROUND_DOWN);
        System.out.println(bigDecimal);

        GlobalCoordinates source = new GlobalCoordinates(23.15792, 113.27324);
        GlobalCoordinates target = new GlobalCoordinates(39.916527, 116.397128);

        double meter1 = getDistanceMeter(source, target, Ellipsoid.Sphere);
        double meter2 = getDistanceMeter(source, target, Ellipsoid.WGS84);

        System.out.println("Sphere坐标系计算结果：" + meter1 + "米");
        System.out.println("WGS84坐标系计算结果：" + meter2 + "米");
    }

    public static double getDistanceMeter(GlobalCoordinates gpsFrom, GlobalCoordinates gpsTo, Ellipsoid ellipsoid) {
        //创建GeodeticCalculator，调用计算方法，传入坐标系、经纬度用于计算距离
        GeodeticCurve geoCurve = new GeodeticCalculator().calculateGeodeticCurve(ellipsoid, gpsFrom, gpsTo);

        return geoCurve.getEllipsoidalDistance();

    }

}
