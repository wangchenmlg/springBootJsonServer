package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class MessageConsumer {
	private static final String URL = "tcp://10.177.98.16:61616";
    private static final String QUEUE_NAME = "queue-name";
    public static void main(String[] args) throws JMSException {
        //1 创建连接工厂ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //2 使用连接工厂创建连接
        Connection connection = connectionFactory.createConnection();
        //3 启动连接
        connection.start();
        //4 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5 创建消息发送的目的地
        Destination destination = session.createQueue(QUEUE_NAME);
        //6 创建消费者
        javax.jms.MessageConsumer messageConsumer = session.createConsumer(destination);
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //7 创建消息
                TextMessage textMessage = (TextMessage)message;
                try {
                    //7 接收消息
                    System.out.println("消费者- 1 -接收消息：【" + textMessage.getText() + "】");
                }
                catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }
}
