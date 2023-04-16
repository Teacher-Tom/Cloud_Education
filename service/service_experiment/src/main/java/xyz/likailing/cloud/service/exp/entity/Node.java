package xyz.likailing.cloud.service.exp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName exp_node
 */
@TableName(value ="exp_node")
@Data
public class Node implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String experimentId;

    /**
     * 
     */
    private String name;

    /**
     * 节点类型
     */
    private Integer type;

    /**
     * 
     */
    private Date beginTime;

    /**
     * 
     */
    private Date endTime;
    @TableField(value = "`left`")
    private String left;

    private String top;

    private String requirement;

    private String steps;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;


}