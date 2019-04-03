package com.nieyue.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 回答
 *
 */
@ApiModel(value="回答",description="回答")
public class Answer implements Serializable{
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 回答id
     */
    @ApiModelProperty(value="回答id")
    private Integer answerId;

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
    @ApiModelProperty(value="回答更新时间")
    private Date updateDate;
    /**
     * 问题id外键
     */
    @ApiModelProperty(value="问题id外键")
    private Integer problemId;

    public Answer() {
    }

    public Integer getAnswerId() {
        return this.answerId;
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

    public Integer getProblemId() {
        return this.problemId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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

    public void setProblemId(Integer problemId) {
        this.problemId = problemId;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Answer)) return false;
        final Answer other = (Answer) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$answerId = this.getAnswerId();
        final Object other$answerId = other.getAnswerId();
        if (this$answerId == null ? other$answerId != null : !this$answerId.equals(other$answerId)) return false;
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
        final Object this$problemId = this.getProblemId();
        final Object other$problemId = other.getProblemId();
        if (this$problemId == null ? other$problemId != null : !this$problemId.equals(other$problemId)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $answerId = this.getAnswerId();
        result = result * PRIME + ($answerId == null ? 43 : $answerId.hashCode());
        final Object $title = this.getTitle();
        result = result * PRIME + ($title == null ? 43 : $title.hashCode());
        final Object $content = this.getContent();
        result = result * PRIME + ($content == null ? 43 : $content.hashCode());
        final Object $createDate = this.getCreateDate();
        result = result * PRIME + ($createDate == null ? 43 : $createDate.hashCode());
        final Object $updateDate = this.getUpdateDate();
        result = result * PRIME + ($updateDate == null ? 43 : $updateDate.hashCode());
        final Object $problemId = this.getProblemId();
        result = result * PRIME + ($problemId == null ? 43 : $problemId.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Answer;
    }

    public String toString() {
        return "Answer(answerId=" + this.getAnswerId() + ", title=" + this.getTitle() + ", content=" + this.getContent() + ", createDate=" + this.getCreateDate() + ", updateDate=" + this.getUpdateDate() + ", problemId=" + this.getProblemId() + ")";
    }
}



