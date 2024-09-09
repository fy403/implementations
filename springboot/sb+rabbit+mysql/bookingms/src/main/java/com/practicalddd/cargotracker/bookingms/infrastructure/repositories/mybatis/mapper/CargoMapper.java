package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject.CargoDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CargoMapper extends BaseMapper<CargoDO> {

//    CargoDO select(@Param("id") String id);
//
//    List<CargoDO> selectAll();
//
//    List<CargoDO> selectByCustomer(@Param("phone") String phone);
//
//    void save(CargoDO cargoDO);
//
//    void update(CargoDO cargoDO);
//
//    void remove(@Param("id") String id);
//
//    int countByCustomer(@Param("phone") String phone);

}
