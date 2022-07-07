package cn.drose.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 日志类
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogDomain implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    private Integer id;

    /**
     * 产生的动作
     */
    private String action;

    /**
     * 产生的数据
     */
    private String data;

    /**
     * 发生人id
     */
    private Integer authorId;

    /**
     * 日志产生的ip
     */
    private String ip;

    /**
     * 日志创建时间
     */
    private Integer created;


}
