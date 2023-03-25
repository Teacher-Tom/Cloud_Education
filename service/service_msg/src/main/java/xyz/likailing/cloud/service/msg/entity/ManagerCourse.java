package xyz.likailing.cloud.service.msg.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName manager_course
 */
@TableName(value ="manager_course")
@Data
public class ManagerCourse implements Serializable {
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
    private Integer creditHour;

    /**
     * 
     */
    private Integer creditNum;

    /**
     * 
     */
    private Boolean type;

    /**
     * 
     */
    private Integer year;

    /**
     * 
     */
    private String department;

    /**
     * 
     */
    private Integer term;

    /**
     * 
     */
    private Integer beginWeek;

    /**
     * 
     */
    private Integer endWeek;

    /**
     * 
     */
    private Date gmtCreate;

    /**
     * 
     */
    private Date gmtModified;

    /**
     * 
     */
    private String coverUrl;

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
        ManagerCourse other = (ManagerCourse) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getCreditHour() == null ? other.getCreditHour() == null : this.getCreditHour().equals(other.getCreditHour()))
            && (this.getCreditNum() == null ? other.getCreditNum() == null : this.getCreditNum().equals(other.getCreditNum()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getYear() == null ? other.getYear() == null : this.getYear().equals(other.getYear()))
            && (this.getDepartment() == null ? other.getDepartment() == null : this.getDepartment().equals(other.getDepartment()))
            && (this.getTerm() == null ? other.getTerm() == null : this.getTerm().equals(other.getTerm()))
            && (this.getBeginWeek() == null ? other.getBeginWeek() == null : this.getBeginWeek().equals(other.getBeginWeek()))
            && (this.getEndWeek() == null ? other.getEndWeek() == null : this.getEndWeek().equals(other.getEndWeek()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getCoverUrl() == null ? other.getCoverUrl() == null : this.getCoverUrl().equals(other.getCoverUrl()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getCreditHour() == null) ? 0 : getCreditHour().hashCode());
        result = prime * result + ((getCreditNum() == null) ? 0 : getCreditNum().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getYear() == null) ? 0 : getYear().hashCode());
        result = prime * result + ((getDepartment() == null) ? 0 : getDepartment().hashCode());
        result = prime * result + ((getTerm() == null) ? 0 : getTerm().hashCode());
        result = prime * result + ((getBeginWeek() == null) ? 0 : getBeginWeek().hashCode());
        result = prime * result + ((getEndWeek() == null) ? 0 : getEndWeek().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getCoverUrl() == null) ? 0 : getCoverUrl().hashCode());
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
        sb.append(", creditHour=").append(creditHour);
        sb.append(", creditNum=").append(creditNum);
        sb.append(", type=").append(type);
        sb.append(", year=").append(year);
        sb.append(", department=").append(department);
        sb.append(", term=").append(term);
        sb.append(", beginWeek=").append(beginWeek);
        sb.append(", endWeek=").append(endWeek);
        sb.append(", gmtCreate=").append(gmtCreate);
        sb.append(", gmtModified=").append(gmtModified);
        sb.append(", coverUrl=").append(coverUrl);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}