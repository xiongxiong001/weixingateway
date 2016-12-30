package org.demoexm.core.mq;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.apache.log4j.Logger;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

public class ActiveMqUtil {	
	private Logger logger = Logger.getLogger(ActiveMqUtil.class);
	
    private JmsTemplate jmsTemplate;
    
	/**发送消息
	 * 
	 * @param messageString
	 */
    public void sendMq(String destinationName, final String messageString) {
    	
        jmsTemplate.send(destinationName, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
            	// 消息内容
            	TextMessage message = session.createTextMessage(messageString);
                return message;
            }
        });
    }
    
    /**接收消息系统启动的时候
     * 
     * @param messageString
     */
    public String receiveMq(String destinationName) {
    	String result = "";
    	ActiveMQTextMessage message = (ActiveMQTextMessage)jmsTemplate.receive(destinationName);
    	try {
    		result = message.getText();
		} catch (JMSException e) {
			logger.error(e);
		}
    	return result;
    }

	public JmsTemplate getJmsTemplate() {
		return jmsTemplate;
	}

	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
}