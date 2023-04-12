package xyz.likailing.cloud.service.exp.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName exp_task_detail
 */
@TableName(value ="exp_task_detail")
@Data
public class TaskDetail implements Serializable {
    /**
     * 
     */
    @TableId
    private String id;

    /**
     * 任务id
     */
    private String taskId;

    /**
     * 队伍id
     */
    private String studentId;

    /**
     * 任务是否完成
     */
    private Boolean hasFinish;

    /**
     * 提交文字
     */
    private String submit;

    /**
     * 提交附件id
     */
    private String submitFileId;

    /**
     * 提交图片url
     */
    private String submitPicId;

    /**
     * 成绩
     */
    private Integer score;

    /**
     * 评语
     */
    private String review;

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
        TaskDetail other = (TaskDetail) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getTaskId() == null ? other.getTaskId() == null : this.getTaskId().equals(other.getTaskId()))
            && (this.getStudentId() == null ? other.getStudentId() == null : this.getStudentId().equals(other.getStudentId()))
            && (this.getHasFinish() == null ? other.getHasFinish() == null : this.getHasFinish().equals(other.getHasFinish()))
            && (this.getSubmit() == null ? other.getSubmit() == null : this.getSubmit().equals(other.getSubmit()))
            && (this.getSubmitFileId() == null ? other.getSubmitFileId() == null : this.getSubmitFileId().equals(other.getSubmitFileId()))
            && (this.getSubmitPicId() == null ? other.getSubmitPicId() == null : this.getSubmitPicId().equals(other.getSubmitPicId()))
            && (this.getScore() == null ? other.getScore() == null : this.getScore().equals(other.getScore()))
            && (this.getReview() == null ? other.getReview() == null : this.getReview().equals(other.getReview()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getTaskId() == null) ? 0 : getTaskId().hashCode());
        result = prime * result + ((getStudentId() == null) ? 0 : getStudentId().hashCode());
        result = prime * result + ((getHasFinish() == null) ? 0 : getHasFinish().hashCode());
        result = prime * result + ((getSubmit() == null) ? 0 : getSubmit().hashCode());
        result = prime * result + ((getSubmitFileId() == null) ? 0 : getSubmitFileId().hashCode());
        result = prime * result + ((getSubmitPicId() == null) ? 0 : getSubmitPicId().hashCode());
        result = prime * result + ((getScore() == null) ? 0 : getScore().hashCode());
        result = prime * result + ((getReview() == null) ? 0 : getReview().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", taskId=").append(taskId);
        sb.append(", teamId=").append(studentId);
        sb.append(", hasFinish=").append(hasFinish);
        sb.append(", submit=").append(submit);
        sb.append(", submitFileId=").append(submitFileId);
        sb.append(", submitPicId=").append(submitPicId);
        sb.append(", score=").append(score);
        sb.append(", review=").append(review);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}