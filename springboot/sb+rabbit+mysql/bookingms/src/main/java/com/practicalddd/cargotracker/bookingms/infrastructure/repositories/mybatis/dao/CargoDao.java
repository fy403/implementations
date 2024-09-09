package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dao;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.CargoDO;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.mapper.CargoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CargoDao extends ServiceImpl<CargoMapper, CargoDO> {
    public List<CargoDO> queryCargos() {
        return lambdaQuery().list();
    }

    public CargoDO selectById(String cargoId) {
        return lambdaQuery().eq(CargoDO::getId, cargoId).one();
    }
}
