package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.converter;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.domain.model.entities.Location;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.Leg;
import com.practicalddd.cargotracker.bookingms.domain.model.valueobjects.Voyage;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.CargoDO;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.LegDO;

import java.time.ZoneId;
import java.util.Date;

public class LegConverter {

    /**
     * 将 Leg 转换为 LegDO。
     *
     * @param leg Leg 实例
     * @return 转换后的 LegDO 实例
     */
    public static LegDO serialize(Leg leg) {
        if (leg == null) {
            return null;
        }
        LegDO target = new LegDO();
        target.setId(leg.getId() != null ? leg.getId().intValue() : null);
        target.setLoadTime(leg.getLoadTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        target.setUnloadTime(leg.getUnloadTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        target.setLoadLocationId(leg.getLoadLocation().getUnLocCode());
        target.setUnloadLocationId(leg.getUnloadLocation().getUnLocCode());
        target.setVoyageNumber(leg.getVoyage().getVoyageId()); // 假设 Voyage 类有 getVoyageNumber 方法
        // 假设 cargoId 是可选字段，这里可以设置默认值或从其他地方获取
        target.setCargoId(null); // 或者 leg.getCargoId() 如果有这个属性

        return target;
    }

    /**
     * 将 LegDO 转换为 Leg。
     *
     * @param legDO LegDO 实例
     * @return 转换后的 Leg 实例
     */
    public static Leg unserialize(LegDO legDO) {
        if (legDO == null) {
            return null;
        }
        Voyage voyage = new Voyage(legDO.getVoyageNumber());
        Location loadLocation = new Location(legDO.getLoadLocationId());
        Location unloadLocation = new Location(legDO.getUnloadLocationId());
        Date loadTime = Date.from(legDO.getLoadTime().atZone(ZoneId.systemDefault()).toInstant());
        Date unloadTime = Date.from(legDO.getUnloadTime().atZone(ZoneId.systemDefault()).toInstant());
        return new Leg(voyage, loadLocation, unloadLocation, loadTime, unloadTime);
    }
}