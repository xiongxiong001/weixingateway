/**
 * @Description 用于返回用于信息列表的VO
 * @author xguan 2016年6月25日
 */
package org.demoexm.core.vo;

import java.util.List;

/**
 * @author gxlly
 *
 */
public class BatchUserInfoListVo extends ResponseVo {
	/* BatchUserInfoVo列表信息 */
	private List<BatchUserInfoVo> user_info_list;

	public List<BatchUserInfoVo> getUser_info_list() {
		return user_info_list;
	}

	public void setUser_info_list(List<BatchUserInfoVo> user_info_list) {
		this.user_info_list = user_info_list;
	}

}
