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
        //1 �������ӹ���ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //2 ʹ�����ӹ�����������
        Connection connection = connectionFactory.createConnection();
        //3 ��������
        connection.start();
        //4 �����Ự
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5 ���������������Ϣ���͵�Ŀ�ĵ�
        Destination destination = session.createTopic(TOPIC_NAME);
        //6 ����������
        MessageProducer messageProducer = session.createProducer(destination);
        //7 ������Ϣ
        TextMessage textMessage = session.createTextMessage();
        for (int i = 1; i <= 100; i++) {
            //8 ������Ϣ����
            textMessage.setText("������- 1 -������Ϣ��" + i);
            //9 ������Ϣ
            messageProducer.send(textMessage);
        }
        System.out.println("��Ϣ���ͳɹ�");
        session.close();
        connection.close();
    }
}
