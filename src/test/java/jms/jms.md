https://www.iteye.com/blog/langgufu-2157403
https://www.jb51.net/article/131797.htm

1��JMS��һ����AS�ṩ��Message�������ܽ�����Ϣ������(Message Provider)����������Ϣ��������Ϣת������Ϣ������(Message  Consumer)�� 
2��JMS�ṩ2�����͵���Ϣ����(1)Queue������Ե㣬ÿ����Ϣֻת����һ����Ϣ������ʹ�á�(2)Topic���������Ͷ��ģ�ÿ����Ϣ����ת�������еĶ�����(������)�� 
3��WEBLOGIC 8�µ�JMS���ã� 
  (1)����JMS Connection Factory 
  (2)����JMS File Store(Ŀǰ���ҵ����ĵ���������File Store,��ʵ�ھ����Ӧ���У�����JMS JDBC Store���㷺������ʱû���ҵ�����) 
  (3)����JMS Server 
  (4)��JMS Server��destinations������JMS Queue����JMS Topic 
    �����ṩ����Ϣ�����ߺ���Ϣ������ʹ�õ���JMS Connection Factory��JNDI��JMS Queue����JMS Topic��JNDI�� 
4����Ϣ��������JMS������Ϣ�Ĳ��裺 
  (1)ʹ��JNDI��ѯ����JMS ConnectionFactory��Destination(JMS Queue/Topic) 
  (2)ʹ�ù������JMS ConnectionFactory��������Connection 
  (3)ʹ������Connection �����ỰSession 
  (4)ʹ�ûỰSession�͹������Destination������Ϣ������MessageSender 
  (5)ʹ����Ϣ������MessageSender������Ϣ 
5����Ϣ�����ߴ�JMS������Ϣ�Ĳ��裺 
  (1)ʹ��JNDI��ѯ����JMS ConnectionFactory��Destination(JMS Queue/Topic) 
  (2)ʹ�ù������JMS ConnectionFactory��������Connection 
  (3)ʹ������Connection �����ỰSession 
  (4)ʹ�ûỰSession�͹������Destination������Ϣ������MessageReceiver 
  (5)ʹ����Ϣ������MessageReceiver������Ϣ����Ҫ��setMessageListener��MessageListener�ӿڰ󶨵�MessageReceiver 
    ��Ϣ�����߱���ʵ����MessageListener�ӿڣ���Ҫ����onMessage�¼������� 
