package xyz.likailing.cloud.service.exp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName exp_team
 */
@TableName(value ="exp_team")
@Data
public class Team implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String leaderName;

    /**
     * 队长的学生id
     */
    private String leaderId;

    /**
     * 成员数量
     */
    private Integer memberNum;

    /**
     * 
     */
    private String courseId;

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
        Team other = (Team) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getLeaderName() == null ? other.getLeaderName() == null : this.getLeaderName().equals(other.getLeaderName()))
            && (this.getLeaderId() == null ? other.getLeaderId() == null : this.getLeaderId().equals(other.getLeaderId()))
            && (this.getMemberNum() == null ? other.getMemberNum() == null : this.getMemberNum().equals(other.getMemberNum()))
            && (this.getCourseId() == null ? other.getCourseId() == null : this.getCourseId().equals(other.getCourseId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getLeaderName() == null) ? 0 : getLeaderName().hashCode());
        result = prime * result + ((getLeaderId() == null) ? 0 : getLeaderId().hashCode());
        result = prime * result + ((getMemberNum() == null) ? 0 : getMemberNum().hashCode());
        result = prime * result + ((getCourseId() == null) ? 0 : getCourseId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", leaderName=").append(leaderName);
        sb.append(", leaderId=").append(leaderId);
        sb.append(", memberNum=").append(memberNum);
        sb.append(", courseId=").append(courseId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}