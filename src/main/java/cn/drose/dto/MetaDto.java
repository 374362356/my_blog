package cn.drose.dto;

import cn.drose.model.MetaDomain;
import lombok.Data;

/**
 * 标签、分类列表
 * Created by Donghua.Chen on 2018/4/30.
 */
@Data
public class MetaDto extends MetaDomain {

    private int count;

    private String isDel;

}
