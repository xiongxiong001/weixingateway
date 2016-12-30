package org.demoexm.core.service;

import org.demoexm.core.vo.ResponseVo;
import org.demoexm.core.vo.TemplateMessageSendRequest;

/***发送模板消息文档
 * 
 * @author 陈惟鲜
 * @date 2016年10月20日 下午12:53:58
 *
 */
public interface TemplateMessageService {
	
	/**发送模板消息*/
    public ResponseVo send(TemplateMessageSendRequest request);
}
