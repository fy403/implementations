package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.BookingId;
import com.practicalddd.cargotracker.bookingms.domain.model.aggregates.Cargo;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.converter.CargoConverter;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.CargoDO;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.mapper.CargoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CargoDao extends ServiceImpl<CargoMapper, CargoDO> {
    public List<Cargo> queryCargos() {
        return lambdaQuery().list().stream().map(CargoConverter::deserialize).collect(Collectors.toList());
    }

    public Cargo selectByBookingId(String bookingId) {
        return CargoConverter.deserialize(lambdaQuery().eq(CargoDO::getBookingId, bookingId).one());
    }

    public List<String> selectAllBookingIds() {
        return lambdaQuery().select(CargoDO::getBookingId).list()
                .stream().map(CargoDO::getBookingId).collect(Collectors.toList());
    }

    public void save(Cargo cargo) {
        CargoDO cargoDO = CargoConverter.serialize(cargo);
        CargoDO data = baseMapper.selectById(cargoDO.getId());
        if (null == data) {
            baseMapper.insert(cargoDO);
        } else {
            baseMapper.updateById(cargoDO);
        }
    }
    public Cargo findByBookingId(BookingId bookingId) {
        LambdaQueryWrapper<CargoDO> lqw = new LambdaQueryWrapper<CargoDO>().eq(CargoDO::getBookingId, bookingId.getBookingId());
        CargoDO cargoDO = baseMapper.selectOne(lqw);
        return CargoConverter.deserialize(cargoDO);
    }
}
