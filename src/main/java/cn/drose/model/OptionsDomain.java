package cn.drose.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 网站配置项
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OptionsDomain {

    private Integer id;

    /** 名称 */
    private String name;
    /** 内容 */
    private String value;
    /** 备注 */
    private String description;

}
