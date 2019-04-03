package com.nieyue.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 课程
 *
 */
@ApiModel(value="课程",description="课程")
public class Course implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 课程id
     */
    @ApiModelProperty(value="课程id")
    private Integer courseId;
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
    @ApiModelProperty(value="课程更新时间")
    private Date updateDate;
    /**
     * 专业id外键
     */
    @ApiModelProperty(value="专业id外键")
    private Integer majorId;

    public Course() {
    }

    public Integer getCourseId() {
        return this.courseId;
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

    public Integer getMajorId() {
        return this.majorId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
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

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Course)) return false;
        final Course other = (Course) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$courseId = this.getCourseId();
        final Object other$courseId = other.getCourseId();
        if (this$courseId == null ? other$courseId != null : !this$courseId.equals(other$courseId)) return false;
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
        final Object this$majorId = this.getMajorId();
        final Object other$majorId = other.getMajorId();
        if (this$majorId == null ? other$majorId != null : !this$majorId.equals(other$majorId)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $courseId = this.getCourseId();
        result = result * PRIME + ($courseId == null ? 43 : $courseId.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $duty = this.getDuty();
        result = result * PRIME + ($duty == null ? 43 : $duty.hashCode());
        final Object $createDate = this.getCreateDate();
        result = result * PRIME + ($createDate == null ? 43 : $createDate.hashCode());
        final Object $updateDate = this.getUpdateDate();
        result = result * PRIME + ($updateDate == null ? 43 : $updateDate.hashCode());
        final Object $majorId = this.getMajorId();
        result = result * PRIME + ($majorId == null ? 43 : $majorId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Course;
    }

    public String toString() {
        return "Course(courseId=" + this.getCourseId() + ", name=" + this.getName() + ", duty=" + this.getDuty() + ", createDate=" + this.getCreateDate() + ", updateDate=" + this.getUpdateDate() + ", majorId=" + this.getMajorId() + ")";
    }
}



