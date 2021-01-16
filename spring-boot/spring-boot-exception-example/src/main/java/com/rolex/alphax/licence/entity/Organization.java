package com.rolex.alphax.licence.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
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
public class Organization extends Model<Organization> {

    private static final long serialVersionUID=1L;
    private Integer id;

    private String name;

    private String contactName;

    private String contactEmail;

    private String contactPhone;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }

}
