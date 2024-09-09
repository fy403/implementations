package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.repository;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.converter.CargoConverter;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.CargoDO;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.mapper.CargoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CargoRepositoryImpl implements CargoRepositoryMP {

    @Autowired
    private CargoMapper cargoMapper;

    @Override
    public void save(Cargo cargo) {
        CargoDO cargoDO = CargoConverter.serialize(cargo);
        CargoDO data = cargoMapper.selectById(cargoDO.getId());
        if (null == data) {
            cargoMapper.insert(cargoDO);
        } else {
            cargoMapper.updateById(cargoDO);
        }
    }
    @Override
    public Cargo findByBookingId(BookingId bookingId) {
        LambdaQueryWrapper<CargoDO> lqw = new LambdaQueryWrapper<CargoDO>().eq(CargoDO::getBookingId, bookingId.getBookingId());
        CargoDO cargoDO = cargoMapper.selectOne(lqw);
        return CargoConverter.deserialize(cargoDO);
    }
}
