package com.ecp.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "mall_notice")
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date created;

    @Column(name = "is_recommend")
    private Byte isRecommend;

    private Date modified;

    @Column(name = "notice_type")
    private Integer noticeType;

    @Column(name = "platform_id")
    private Long platformId;

    @Column(name = "sort_num")
    private Byte sortNum;

    private Byte status;

    @Column(name = "theme_id")
    private Integer themeId;

    @Column(name = "theme_type")
    private Byte themeType;

    private String title;

    private String url;

    private String content;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return created
     */
    public Date getCreated() {
        return created;
    }

    /**
     * @param created
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * @return is_recommend
     */
    public Byte getIsRecommend() {
        return isRecommend;
    }

    /**
     * @param isRecommend
     */
    public void setIsRecommend(Byte isRecommend) {
        this.isRecommend = isRecommend;
    }

    /**
     * @return modified
     */
    public Date getModified() {
        return modified;
    }

    /**
     * @param modified
     */
    public void setModified(Date modified) {
        this.modified = modified;
    }

    /**
     * @return notice_type
     */
    public Integer getNoticeType() {
        return noticeType;
    }

    /**
     * @param noticeType
     */
    public void setNoticeType(Integer noticeType) {
        this.noticeType = noticeType;
    }

    /**
     * @return platform_id
     */
    public Long getPlatformId() {
        return platformId;
    }

    /**
     * @param platformId
     */
    public void setPlatformId(Long platformId) {
        this.platformId = platformId;
    }

    /**
     * @return sort_num
     */
    public Byte getSortNum() {
        return sortNum;
    }

    /**
     * @param sortNum
     */
    public void setSortNum(Byte sortNum) {
        this.sortNum = sortNum;
    }

    /**
     * @return status
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * @param status
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * @return theme_id
     */
    public Integer getThemeId() {
        return themeId;
    }

    /**
     * @param themeId
     */
    public void setThemeId(Integer themeId) {
        this.themeId = themeId;
    }

    /**
     * @return theme_type
     */
    public Byte getThemeType() {
        return themeType;
    }

    /**
     * @param themeType
     */
    public void setThemeType(Byte themeType) {
        this.themeType = themeType;
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", created=").append(created);
        sb.append(", isRecommend=").append(isRecommend);
        sb.append(", modified=").append(modified);
        sb.append(", noticeType=").append(noticeType);
        sb.append(", platformId=").append(platformId);
        sb.append(", sortNum=").append(sortNum);
        sb.append(", status=").append(status);
        sb.append(", themeId=").append(themeId);
        sb.append(", themeType=").append(themeType);
        sb.append(", title=").append(title);
        sb.append(", url=").append(url);
        sb.append(", content=").append(content);
        sb.append("]");
        return sb.toString();
    }
}