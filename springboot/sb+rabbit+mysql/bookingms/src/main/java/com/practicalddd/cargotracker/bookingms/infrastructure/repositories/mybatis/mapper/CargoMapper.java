package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.CargoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CargoMapper extends BaseMapper<CargoDO> {
}
