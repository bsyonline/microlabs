package com.rolex.alphax.licence.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author rolex
 * @since 2020-06-20
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Licence extends Model<Licence> {

    private static final long serialVersionUID=1L;

    @TableId(value = "licence_id", type = IdType.AUTO)
    private Integer licenceId;

    private Integer organizationId;

    private String licenceType;

    private String productName;

    private Integer licenceMax;

    private Integer licenceAllocated;

    private String comment;

    @Override
    protected Serializable pkVal() {
        return this.licenceId;
    }

}
