package cn.drose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 文章关联信息表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RelationShipDomain {

    private Integer id;

    /**
     * 文章主键编号
     */
    private Integer cid;
    /**
     * 项目编号
     */
    private Integer mid;

}
