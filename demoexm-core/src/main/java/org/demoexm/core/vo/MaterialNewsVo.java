package org.demoexm.core.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 
* @title: 图文素材
* @author zhangfeng
* @date 2016年12月6日 下午4:35:47 
*
 */
public class MaterialNewsVo implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String medial_id ;
	
	private String title;
	
	private String author;
	
	private String digest;
	
	private String content;
	
	private String content_source_url;
	
	private String thumb_media_id;
	
	private String show_cover_pic;
	
	private String url;
	
	private String thumb_url;

	private Date createTime;

	private Date updateTime;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDigest() {
		return digest;
	}

	public void setDigest(String digest) {
		this.digest = digest;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContent_source_url() {
		return content_source_url;
	}

	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}

	public String getThumb_media_id() {
		return thumb_media_id;
	}

	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}

	public String getShow_cover_pic() {
		return show_cover_pic;
	}

	public void setShow_cover_pic(String show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getThumb_url() {
		return thumb_url;
	}

	public void setThumb_url(String thumb_url) {
		this.thumb_url = thumb_url;
	}

	public String getMedial_id() {
		return medial_id;
	}

	public void setMedial_id(String medial_id) {
		this.medial_id = medial_id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
}
