package org.demoexm.core.vo;

/**获取关注公众号用户列表
 * 
 * @author : chewneixian 陈惟鲜
 * @create_date 2016年10月24日 下午5:24:53
 *
 */
public class UserListResponseVo extends ResponseVo
{
    
    /**total	 关注该公众账号的总用户数*/
	private Long total;
	/**count	 拉取的OPENID个数，最大值为10000*/
	private Long count;
	/**data	 列表数据，OPENID的列表*/
	private String[] openid;
	/**next_openid	 拉取列表的最后一个用户的OPENID*/
	private String next_openid;
	public Long getTotal() {
		return total;
	}
	public void setTotal(Long total) {
		this.total = total;
	}
	public Long getCount() {
		return count;
	}
	public void setCount(Long count) {
		this.count = count;
	}
	public String[] getOpenid() {
		return openid;
	}
	public void setOpenid(String[] openid) {
		this.openid = openid;
	}
	public String getNext_openid() {
		return next_openid;
	}
	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}
	
}
