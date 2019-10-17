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
        //1 �������ӹ���ConnectionFactory
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(URL);
        //2 ʹ�����ӹ�����������
        Connection connection = connectionFactory.createConnection();
        //3 ��������
        connection.start();
        //4 �����Ự
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5 ������Ϣ���͵�Ŀ�ĵ�
        Destination destination = session.createQueue(QUEUE_NAME);
        //6 ����������
        javax.jms.MessageConsumer messageConsumer = session.createConsumer(destination);
        messageConsumer.setMessageListener(new MessageListener() {
            public void onMessage(Message message) {
                //7 ������Ϣ
                TextMessage textMessage = (TextMessage)message;
                try {
                    //7 ������Ϣ
                    System.out.println("������- 1 -������Ϣ����" + textMessage.getText() + "��");
                }
                catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
        );
    }
}
