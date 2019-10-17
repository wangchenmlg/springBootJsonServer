https://www.iteye.com/blog/langgufu-2157403
https://www.jb51.net/article/131797.htm

1、JMS是一个由AS提供的Message服务。它能接受消息产生者(Message Provider)所发出的消息，并把消息转发给消息消费者(Message  Consumer)。 
2、JMS提供2种类型的消息服务：(1)Queue，即点对点，每个消息只转发给一个消息消费者使用。(2)Topic，即发布和订阅，每个消息可以转发给所有的订阅者(消费者)。 
3、WEBLOGIC 8下的JMS配置： 
  (1)配置JMS Connection Factory 
  (2)配置JMS File Store(目前所找到的文档都是配置File Store,其实在具体的应用中，可能JMS JDBC Store更广泛，但暂时没有找到资料) 
  (3)配置JMS Server 
  (4)在JMS Server的destinations中配置JMS Queue或者JMS Topic 
    其中提供给消息产生者和消息消费者使用的是JMS Connection Factory的JNDI和JMS Queue或者JMS Topic的JNDI。 
4、消息产生者向JMS发送消息的步骤： 
  (1)使用JNDI查询对象JMS ConnectionFactory和Destination(JMS Queue/Topic) 
  (2)使用管理对象JMS ConnectionFactory建立连接Connection 
  (3)使用连接Connection 建立会话Session 
  (4)使用会话Session和管理对象Destination创建消息生产者MessageSender 
  (5)使用消息生产者MessageSender发送消息 
5、消息消费者从JMS接受消息的步骤： 
  (1)使用JNDI查询对象JMS ConnectionFactory和Destination(JMS Queue/Topic) 
  (2)使用管理对象JMS ConnectionFactory建立连接Connection 
  (3)使用连接Connection 建立会话Session 
  (4)使用会话Session和管理对象Destination创建消息消费者MessageReceiver 
  (5)使用消息消费者MessageReceiver接受消息，需要用setMessageListener将MessageListener接口绑定到MessageReceiver 
    消息消费者必须实现了MessageListener接口，需要定义onMessage事件方法。 
