package xyz.likailing.cloud.service.exp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName exp_line
 */
@TableName(value ="exp_line")
@Data
public class Line implements Serializable {
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
     * 起点id
     */
    private String fromNodeId;

    /**
     * 终点id
     */
    private String toNodeId;

    /**
     * 连线标签
     */
    private String label;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Line other = (Line) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getExperimentId() == null ? other.getExperimentId() == null : this.getExperimentId().equals(other.getExperimentId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getFromNodeId() == null ? other.getFromNodeId() == null : this.getFromNodeId().equals(other.getFromNodeId()))
            && (this.getToNodeId() == null ? other.getToNodeId() == null : this.getToNodeId().equals(other.getToNodeId()))
            && (this.getLabel() == null ? other.getLabel() == null : this.getLabel().equals(other.getLabel()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getExperimentId() == null) ? 0 : getExperimentId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getFromNodeId() == null) ? 0 : getFromNodeId().hashCode());
        result = prime * result + ((getToNodeId() == null) ? 0 : getToNodeId().hashCode());
        result = prime * result + ((getLabel() == null) ? 0 : getLabel().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", experimentId=").append(experimentId);
        sb.append(", name=").append(name);
        sb.append(", fromNodeId=").append(fromNodeId);
        sb.append(", toNodeId=").append(toNodeId);
        sb.append(", label=").append(label);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}