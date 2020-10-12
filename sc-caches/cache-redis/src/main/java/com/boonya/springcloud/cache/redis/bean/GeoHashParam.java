package com.boonya.springcloud.cache.redis.bean;

import org.springframework.data.geo.Point;

/**
 * @ClassName: GeoHashParam
 * @Description: TODO(功能说明：GEOHASH类型)
 * @author: pengjunlin
 * @motto: 学习需要毅力，那就秀毅力
 * @date 2020/4/25 0:31
 */
public class GeoHashParam extends HashParam {

    private Point point;

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
