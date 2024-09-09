package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;

/**
 * <p>
 * 
 * </p>
 *
 * @author author
 * @since 2024-09-09
 */
@Data
@TableName("leg")
public class LegDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer id;

    private LocalDateTime loadTime;

    private LocalDateTime unloadTime;

    private String loadLocationId;

    private String unloadLocationId;

    private String voyageNumber;

    private Integer cargoId;
}
