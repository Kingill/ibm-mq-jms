Compilation

javac -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar JmsJndiProducerURL.java



Run test

java -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar JmsJndiProducer
No arguments! Mandatory arguments must be specified.

Usage:
JmsJndiProducer -i initialContext -c connectionFactory -d destination


Run avec TLS

/opt/IBM/WebSphere/AppServer/java/bin/java -Dcom.ibm.CORBA.ConfigURL="file:sas.client.props" -Dcom.ibm.SSL.ConfigURL="file:ssl.client.props" -Dcom.ibm.CORBA.Debug.Output=debug -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar:lib-was855/com.ibm.ws.ejb.thinclient_8.5.0.jar:lib-was855/com.ibm.ws.orb_8.5.0.jar:lib-was855/com.ibm.ws.admin.client_8.5.0.jar:lib-was855/com.ibm.ws.runtime.jar  JmsJndiProducerURL -i iiop://node1:2809 -c QueueFactory -d SOURCE
Initial context found!
Sent message:

  JMSMessage class: jms_text
  JMSType:          null
  JMSDeliveryMode:  2
  JMSDeliveryDelay: 0
  JMSDeliveryTime:  1645088662967
  JMSExpiration:    0
  JMSPriority:      4
  JMSMessageID:     ID:414d5120544553542020202020202020446e0b6201180240
  JMSTimestamp:     1645088662967
  JMSCorrelationID: null
  JMSDestination:   queue://TEST/SOURCE
  JMSReplyTo:       null
  JMSRedelivered:   false
    JMSXAppID: JmsJndiProducerURL
    JMSXDeliveryCount: 0
    JMSXUserID: testmq
    JMS_IBM_PutApplType: 28
    JMS_IBM_PutDate: 20220217
    JMS_IBM_PutTime: 09042298
JmsJndiProducer: Your lucky number today is 925
