package com.practicalddd.cargotracker.bookingms.infrastructure.repositories.mybatis.dataobject;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.github.dreamyoung.mprelation.CascadeType;
import com.github.dreamyoung.mprelation.FetchType;
import com.github.dreamyoung.mprelation.JoinColumn;
import com.github.dreamyoung.mprelation.OneToMany;
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
@TableName("cargo") // 指定这个类映射到名为`cargo`的数据库表
public class CargoDO implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO) // 标记为主键，并且是自增类型
    private Integer id;

    @TableField("BOOKING_ID") // 映射到表中的`BOOKING_ID`字段
    private String bookingId;

    @TableField(exist = false)
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="ID", referencedColumnName = "CARGO_ID")
    private List<LegDO> legs;

    @TableField("TRANSPORT_STATUS")
    private String transportStatus;

    @TableField("ROUTING_STATUS")
    private String routingStatus;

    @TableField("spec_origin_id")
    private String specOriginId;

    @TableField("spec_destination_id")
    private String specDestinationId;

    @TableField("SPEC_ARRIVAL_DEADLINE")
    private LocalDate specArrivalDeadline;

    @TableField("origin_id")
    private String originId;

    @TableField("BOOKING_AMOUNT")
    private Integer bookingAmount;

    @TableField("handling_event_id")
    private Integer handlingEventId;

    @TableField("next_expected_location_id")
    private String nextExpectedLocationId;

    @TableField("next_expected_handling_event_type")
    private String nextExpectedHandlingEventType;

    @TableField("next_expected_voyage_id")
    private String nextExpectedVoyageId;

    @TableField("last_known_location_id")
    private String lastKnownLocationId;

    @TableField("current_voyage_number")
    private String currentVoyageNumber;

    @TableField("last_handling_event_id")
    private Integer lastHandlingEventId;

    @TableField("last_handling_event_type")
    private String lastHandlingEventType;

    @TableField("last_handling_event_location")
    private String lastHandlingEventLocation;

    @TableField("last_handling_event_voyage")
    private String lastHandlingEventVoyage;
}
