package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-09-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("location")
public class LocationDO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private String unlocode;


}
