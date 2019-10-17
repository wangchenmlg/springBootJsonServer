package jms;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class TopicProducer {
	private static final String URL = "tcp://10.177.98.16:61616";
    private static final String TOPIC_NAME = "topic-name";
    public static void main(String[] args) throws JMSException {
        //1 创建连接工厂ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //2 使用连接工厂创建连接
        Connection connection = connectionFactory.createConnection();
        //3 启动连接
        connection.start();
        //4 创建会话
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5 创建带有主题的消息发送的目的地
        Destination destination = session.createTopic(TOPIC_NAME);
        //6 创建生产者
        MessageProducer messageProducer = session.createProducer(destination);
        //7 创建消息
        TextMessage textMessage = session.createTextMessage();
        for (int i = 1; i <= 100; i++) {
            //8 创建消息内容
            textMessage.setText("发送者- 1 -发送消息：" + i);
            //9 发送消息
            messageProducer.send(textMessage);
        }
        System.out.println("消息发送成功");
        session.close();
        connection.close();
    }
}
