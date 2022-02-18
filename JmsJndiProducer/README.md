Compilation

javac -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar JmsJndiProducer.java


Run sans parametre

java -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar JmsJndiProducer                                   
No arguments! Mandatory arguments must be specified.

Usage:
JmsJndiProducer -i initialContext -c connectionFactory -d destination



Run avec parametre

java -cp .:lib-mq92/wmq/JavaSE/lib/jms.jar:lib-mq92/wmq/JavaSE/lib/com.ibm.mq.allclient.jar JmsJndiProducer -i file:./cf/ -c TEST-CF -d SOURCE

