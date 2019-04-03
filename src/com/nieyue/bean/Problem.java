package com.nieyue.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 问题
 *
 */
@ApiModel(value="问题",description="问题")
public class Problem implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 问题id
     */
    @ApiModelProperty(value="问题id")
    private Integer problemId;

    /**
     * 标题
     */
    @ApiModelProperty(value="标题")
    private String title;
    /**
     * 内容
     */
    @ApiModelProperty(value="内容")
    private String content;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="问题更新时间")
    private Date updateDate;
    /**
     * 学生课程id外键
     */
    @ApiModelProperty(value="学生课程id外键")
    private Integer studentCourseId;
    /**
     * 采纳回答id外键
     */
    @ApiModelProperty(value="采纳回答id外键")
    private Integer answerId;

    public Problem() {
    }
    public Integer getProblemId() {
        return this.problemId;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContent() {
        return this.content;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public Integer getStudentCourseId() {
        return this.studentCourseId;
    }

    public Integer getAnswerId() {
        return this.answerId;
    }

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setStudentCourseId(Integer studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Problem)) return false;
        final Problem other = (Problem) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$problemId = this.getProblemId();
        final Object other$problemId = other.getProblemId();
        if (this$problemId == null ? other$problemId != null : !this$problemId.equals(other$problemId)) return false;
        final Object this$title = this.getTitle();
        final Object other$title = other.getTitle();
        if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
        final Object this$content = this.getContent();
        final Object other$content = other.getContent();
        if (this$content == null ? other$content != null : !this$content.equals(other$content)) return false;
        final Object this$createDate = this.getCreateDate();
        final Object other$createDate = other.getCreateDate();
        if (this$createDate == null ? other$createDate != null : !this$createDate.equals(other$createDate))
            return false;
        final Object this$updateDate = this.getUpdateDate();
        final Object other$updateDate = other.getUpdateDate();
        if (this$updateDate == null ? other$updateDate != null : !this$updateDate.equals(other$updateDate))
            return false;
        final Object this$studentCourseId = this.getStudentCourseId();
        final Object other$studentCourseId = other.getStudentCourseId();
        if (this$studentCourseId == null ? other$studentCourseId != null : !this$studentCourseId.equals(other$studentCourseId))
            return false;
        final Object this$answerId = this.getAnswerId();
        final Object other$answerId = other.getAnswerId();
        if (this$answerId == null ? other$answerId != null : !this$answerId.equals(other$answerId)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $problemId = this.getProblemId();
        result = result * PRIME + ($problemId == null ? 43 : $problemId.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $createDate = this.getCreateDate();
        result = result * PRIME + ($createDate == null ? 43 : $createDate.hashCode());
        final Object $updateDate = this.getUpdateDate();
        result = result * PRIME + ($updateDate == null ? 43 : $updateDate.hashCode());
        final Object $studentCourseId = this.getStudentCourseId();
        result = result * PRIME + ($studentCourseId == null ? 43 : $studentCourseId.hashCode());
        final Object $answerId = this.getAnswerId();
        result = result * PRIME + ($answerId == null ? 43 : $answerId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Problem;
    }

    public String toString() {
        return "Problem(problemId=" + this.getProblemId() + ", title=" + this.getTitle() + ", content=" + this.getContent() + ", createDate=" + this.getCreateDate() + ", updateDate=" + this.getUpdateDate() + ", studentCourseId=" + this.getStudentCourseId() + ", answerId=" + this.getAnswerId() + ")";
    }
}



