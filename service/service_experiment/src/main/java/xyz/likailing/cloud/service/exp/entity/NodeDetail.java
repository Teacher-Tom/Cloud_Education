package xyz.likailing.cloud.service.exp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

/**
 * 
 * @TableName exp_node_detail
 */
@TableName(value ="exp_node_detail")
@Data
public class NodeDetail implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String nodeId;

    /**
     * 
     */
    private String teamId;

    /**
     * 
     */
    private Boolean hasFinish;

    /**
     * 难度评价：1-5星
     */
    private Integer difficulty;

    /**
     * 
     */
    @JsonFormat(locale="zh", timezone="GMT+8", pattern="yyyy-MM-dd")
    private Date finishTime;

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
        NodeDetail other = (NodeDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getNodeId() == null ? other.getNodeId() == null : this.getNodeId().equals(other.getNodeId()))
            && (this.getTeamId() == null ? other.getTeamId() == null : this.getTeamId().equals(other.getTeamId()))
            && (this.getHasFinish() == null ? other.getHasFinish() == null : this.getHasFinish().equals(other.getHasFinish()))
            && (this.getDifficulty() == null ? other.getDifficulty() == null : this.getDifficulty().equals(other.getDifficulty()))
            && (this.getFinishTime() == null ? other.getFinishTime() == null : this.getFinishTime().equals(other.getFinishTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getNodeId() == null) ? 0 : getNodeId().hashCode());
        result = prime * result + ((getTeamId() == null) ? 0 : getTeamId().hashCode());
        result = prime * result + ((getHasFinish() == null) ? 0 : getHasFinish().hashCode());
        result = prime * result + ((getDifficulty() == null) ? 0 : getDifficulty().hashCode());
        result = prime * result + ((getFinishTime() == null) ? 0 : getFinishTime().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", nodeId=").append(nodeId);
        sb.append(", teamId=").append(teamId);
        sb.append(", hasFinish=").append(hasFinish);
        sb.append(", difficulty=").append(difficulty);
        sb.append(", finishTime=").append(finishTime);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}