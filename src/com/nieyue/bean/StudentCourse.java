package com.nieyue.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生课程
 *
 */
@ApiModel(value="学生课程",description="学生课程")
public class StudentCourse implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 学生课程id
     */
    @ApiModelProperty(value="学生课程id")
    private Integer studentCourseId;
    /**
     * 名称
     */
    @ApiModelProperty(value="名称")
    private String name;
    /**
     * 描述
     */
    @ApiModelProperty(value="描述")
    private String duty;
    /**
     * 创建时间
     */
    @ApiModelProperty(value="创建时间")
    private Date createDate;
    /**
     * 更新时间
     */
    @ApiModelProperty(value="学生课程更新时间")
    private Date updateDate;
    /**
     * 课程id外键
     */
    @ApiModelProperty(value="课程id外键")
    private Integer courseId;
    /**
     * 账户id外键
     */
    @ApiModelProperty(value="账户id外键")
    private Integer accountId;

    public StudentCourse() {
    }

    public Integer getStudentCourseId() {
        return this.studentCourseId;
    }

    public String getName() {
        return this.name;
    }

    public String getDuty() {
        return this.duty;
    }

    public Date getCreateDate() {
        return this.createDate;
    }

    public Date getUpdateDate() {
        return this.updateDate;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public Integer getAccountId() {
        return this.accountId;
    }

    public void setStudentCourseId(Integer studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDuty(String duty) {
        this.duty = duty;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof StudentCourse)) return false;
        final StudentCourse other = (StudentCourse) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$studentCourseId = this.getStudentCourseId();
        final Object other$studentCourseId = other.getStudentCourseId();
        if (this$studentCourseId == null ? other$studentCourseId != null : !this$studentCourseId.equals(other$studentCourseId))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$duty = this.getDuty();
        final Object other$duty = other.getDuty();
        if (this$duty == null ? other$duty != null : !this$duty.equals(other$duty)) return false;
        final Object this$createDate = this.getCreateDate();
        final Object other$createDate = other.getCreateDate();
        if (this$createDate == null ? other$createDate != null : !this$createDate.equals(other$createDate))
            return false;
        final Object this$updateDate = this.getUpdateDate();
        final Object other$updateDate = other.getUpdateDate();
        if (this$updateDate == null ? other$updateDate != null : !this$updateDate.equals(other$updateDate))
            return false;
        final Object this$courseId = this.getCourseId();
        final Object other$courseId = other.getCourseId();
        if (this$courseId == null ? other$courseId != null : !this$courseId.equals(other$courseId)) return false;
        final Object this$accountId = this.getAccountId();
        final Object other$accountId = other.getAccountId();
        if (this$accountId == null ? other$accountId != null : !this$accountId.equals(other$accountId)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $studentCourseId = this.getStudentCourseId();
        result = result * PRIME + ($studentCourseId == null ? 43 : $studentCourseId.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $duty = this.getDuty();
        result = result * PRIME + ($duty == null ? 43 : $duty.hashCode());
        final Object $createDate = this.getCreateDate();
        result = result * PRIME + ($createDate == null ? 43 : $createDate.hashCode());
        final Object $updateDate = this.getUpdateDate();
        result = result * PRIME + ($updateDate == null ? 43 : $updateDate.hashCode());
        final Object $courseId = this.getCourseId();
        result = result * PRIME + ($courseId == null ? 43 : $courseId.hashCode());
        final Object $accountId = this.getAccountId();
        result = result * PRIME + ($accountId == null ? 43 : $accountId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof StudentCourse;
    }

    public String toString() {
        return "StudentCourse(studentCourseId=" + this.getStudentCourseId() + ", name=" + this.getName() + ", duty=" + this.getDuty() + ", createDate=" + this.getCreateDate() + ", updateDate=" + this.getUpdateDate() + ", courseId=" + this.getCourseId() + ", accountId=" + this.getAccountId() + ")";
    }
}



