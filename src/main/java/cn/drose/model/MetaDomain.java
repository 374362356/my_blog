package cn.drose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 项目类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MetaDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 项目主键
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;

    /**
     * 项目缩略名
     */
    private String slug;

    /**
     * 项目类型
     */
    private String type;

    /**
     * 对应的文章类型
     */
    private String contentType;

    /**
     * 选项描述
     */
    private String description;

    /**
     * 项目排序
     */
    private Integer sort;

    private Integer parent;

    private String isDel;

}
