Compilation

javac -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar JmsProducer.java


Run sans parametre

java -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar JmsProducer
No arguments! Mandatory arguments must be specified.

Usage:
JmsProducer -m queueManagerName -d destinationName [-h host -p port -l channel -c connamelist -s cipher suite]




Run avec parametre

java -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar JmsProducer -m TEST -d SOURCE
Sent message:

  JMSMessage class: jms_text
  JMSType:          null
  JMSDeliveryMode:  2
  JMSDeliveryDelay: 0
  JMSDeliveryTime:  1645169664938
  JMSExpiration:    0
  JMSPriority:      4
  JMSMessageID:     ID:414d5120544553542020202020202020446e0b62011e0240
  JMSTimestamp:     1645169664938
  JMSCorrelationID: null
  JMSDestination:   queue:///SOURCE
  JMSReplyTo:       null
  JMSRedelivered:   false
    JMSXAppID: JmsProducer                 
    JMSXDeliveryCount: 0
    JMSXUserID: testmq      
    JMS_IBM_PutApplType: 28
    JMS_IBM_PutDate: 20220218
    JMS_IBM_PutTime: 07342495
JmsProducer: Your lucky number today is 910
SUCCESS
