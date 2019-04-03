package com.nieyue.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 专业
 *
 */
@ApiModel(value="专业",description="专业")
public class Major implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 专业id
     */
    @ApiModelProperty(value="专业id")
    private Integer majorId;

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
    @ApiModelProperty(value="专业更新时间")
    private Date updateDate;
    /**
     * 学院id外键
     */
    @ApiModelProperty(value="学院id外键")
    private Integer academyId;

    public Major() {
    }

    public Integer getMajorId() {
        return this.majorId;
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

    public Integer getAcademyId() {
        return this.academyId;
    }

    public void setMajorId(Integer majorId) {
        this.majorId = majorId;
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

    public void setAcademyId(Integer academyId) {
        this.academyId = academyId;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Major)) return false;
        final Major other = (Major) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$majorId = this.getMajorId();
        final Object other$majorId = other.getMajorId();
        if (this$majorId == null ? other$majorId != null : !this$majorId.equals(other$majorId)) return false;
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
        final Object this$academyId = this.getAcademyId();
        final Object other$academyId = other.getAcademyId();
        if (this$academyId == null ? other$academyId != null : !this$academyId.equals(other$academyId)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $majorId = this.getMajorId();
        result = result * PRIME + ($majorId == null ? 43 : $majorId.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $duty = this.getDuty();
        result = result * PRIME + ($duty == null ? 43 : $duty.hashCode());
        final Object $createDate = this.getCreateDate();
        result = result * PRIME + ($createDate == null ? 43 : $createDate.hashCode());
        final Object $updateDate = this.getUpdateDate();
        result = result * PRIME + ($updateDate == null ? 43 : $updateDate.hashCode());
        final Object $academyId = this.getAcademyId();
        result = result * PRIME + ($academyId == null ? 43 : $academyId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Major;
    }

    public String toString() {
        return "Major(majorId=" + this.getMajorId() + ", name=" + this.getName() + ", duty=" + this.getDuty() + ", createDate=" + this.getCreateDate() + ", updateDate=" + this.getUpdateDate() + ", academyId=" + this.getAcademyId() + ")";
    }
}



