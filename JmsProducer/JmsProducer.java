// SCCSID "@(#) samples/jms/JmsProducer.java, jmscc.samples, k701, k701-104-110104  1.7.1.1 09/08/17 07:37:42"
/*
 * <N_OCO_COPYRIGHT>
 * Licensed Materials - Property of IBM
 *
 * 5724-H72, 5655-R36, 5724-L26, 5655-L82   
 *
 * (c) Copyright IBM Corp. 2008, 2009 All Rights Reserved.
 *
 * US Government Users Restricted Rights - Use, duplication or
 * disclosure restricted by GSA ADP Schedule Contract with
 * IBM Corp.
 * <NOC_COPYRIGHT>
 */
 
import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
 
import com.ibm.msg.client.jms.JmsConnectionFactory;
import com.ibm.msg.client.jms.JmsFactoryFactory;
import com.ibm.msg.client.wmq.WMQConstants;
 
/**
 * A JMS producer (sender or publisher) application that sends a simple message to the named
 * destination (queue or topic).
 *
 * Notes:
 *
 * API type: IBM JMS API (v1.1, unified domain)
 *
 * Messaging domain: Point-to-point or Publish-Subscribe
 *
 * Provider type: WebSphere MQ
 *
 * Connection mode: Client connection
 *
 * JNDI in use: No
 *
 * Usage:
 *
 * JmsProducer -m queueManagerName -d destinationName [-h host -p port -l channel]
 *
 * for example:
 *
 * JmsProducer -m QM1 -d Q1
 *
 * JmsProducer -m QM1 -d topic://foo -h localhost -p 1414
 */
public class JmsProducer {
 
  private static String host = "localhost";
  private static String conNameList = "node1(50240),node2(50240)";
  private static int port = 50240;
  private static String channel = "CLIENTTLS";
  private static String queueManagerName = "TEST";
  private static String destinationName = "SOURCE";
  private static String sslSuite = "";
  private static String sslSpec = null;
  private static boolean isTopic = false;
  
 
  // System exit status value (assume unset value to be 1)
  private static int status = 1;
 
  /**
   * Main method
   *
   * @param args
   */
  public static void main(String[] args) {
    // Parse the arguments
    parseArgs(args);
 
    // Variables
    Connection connection = null;
    Session session = null;
    Destination destination = null;
    MessageProducer producer = null;
 
    try {
      // Create a connection factory
      JmsFactoryFactory ff = JmsFactoryFactory.getInstance(WMQConstants.WMQ_PROVIDER);
      JmsConnectionFactory cf = ff.createConnectionFactory();
 
      // Set the properties
      cf.setStringProperty(WMQConstants.WMQ_HOST_NAME, host);
      cf.setStringProperty(WMQConstants.WMQ_CONNECTION_NAME_LIST, conNameList);
      cf.setIntProperty(WMQConstants.WMQ_PORT, port);
      cf.setStringProperty(WMQConstants.WMQ_CHANNEL, channel);
      cf.setIntProperty(WMQConstants.WMQ_CONNECTION_MODE, WMQConstants.WMQ_CM_CLIENT);
      cf.setStringProperty(WMQConstants.WMQ_QUEUE_MANAGER, queueManagerName);
      cf.setStringProperty(WMQConstants.WMQ_SSL_CIPHER_SUITE, sslSuite);
      cf.setStringProperty(WMQConstants.WMQ_SSL_CIPHER_SPEC, sslSpec);
       
 
      
  
      
      // Create JMS objects
      connection = cf.createConnection();
      session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
      if (isTopic) {
        destination = session.createTopic(destinationName);
      }
      else {
        destination = session.createQueue(destinationName);
      }
      producer = session.createProducer(destination);
 
      long uniqueNumber = System.currentTimeMillis() % 1000;
      TextMessage message = session.createTextMessage("JmsProducer: Your lucky number today is "
          + uniqueNumber);
 
      // Start the connection
      connection.start();
 
      // And, send the message
      producer.send(message);
      System.out.println("Sent message:\n" + message);
 
      recordSuccess();
    }
    catch (JMSException jmsex) {
      recordFailure(jmsex);
    }
    finally {
      if (producer != null) {
        try {
          producer.close();
        }
        catch (JMSException jmsex) {
          System.out.println("Producer could not be closed.");
          recordFailure(jmsex);
        }
      }
 
      if (session != null) {
        try {
          session.close();
        }
        catch (JMSException jmsex) {
          System.out.println("Session could not be closed.");
          recordFailure(jmsex);
        }
      }
 
      if (connection != null) {
        try {
          connection.close();
        }
        catch (JMSException jmsex) {
          System.out.println("Connection could not be closed.");
          recordFailure(jmsex);
        }
      }
    }
    System.exit(status);
    return;
  } // end main()
 
  /**
   * Process a JMSException and any associated inner exceptions.
   *
   * @param jmsex
   */
  private static void processJMSException(JMSException jmsex) {
    System.out.println(jmsex);
    Throwable innerException = jmsex.getLinkedException();
    if (innerException != null) {
      System.out.println("Inner exception(s):");
    }
    while (innerException != null) {
      System.out.println(innerException);
      innerException = innerException.getCause();
    }
    return;
  }
 
  /**
   * Record this run as successful.
   */
  private static void recordSuccess() {
    System.out.println("SUCCESS");
    status = 0;
    return;
  }
 
  /**
   * Record this run as failure.
   *
   * @param ex
   */
  private static void recordFailure(Exception ex) {
    if (ex != null) {
      if (ex instanceof JMSException) {
        processJMSException((JMSException) ex);
      }
      else {
        System.out.println(ex);
      }
    }
    System.out.println("FAILURE");
    status = -1;
    return;
  }
 
  /**
   * Parse user supplied arguments.
   *
   * @param args
   */
  private static void parseArgs(String[] args) {
    try {
      int length = args.length;
      if (length == 0) {
        throw new IllegalArgumentException("No arguments! Mandatory arguments must be specified.");
      }
      if ((length % 2) != 0) {
        throw new IllegalArgumentException("Incorrect number of arguments!");
      }
 
      int i = 0;
 
      while (i < length) {
        if ((args[i]).charAt(0) != '-') {
          throw new IllegalArgumentException("Expected a '-' character next: " + args[i]);
        }
 
        char opt = (args[i]).toLowerCase().charAt(1);
 
        switch (opt) {
          case 'h' :
            host = args[++i];
            break;
          case 'p' :
            port = Integer.parseInt(args[++i]);
            break;
          case 'l' :
            channel = args[++i];
            break;
          case 'c' :
             conNameList = args[++i];
              break;
          case 's' :
            sslSuite = args[++i];
               break;
          case 'm' :
            queueManagerName = args[++i];
            break;
          case 'd' :
            destinationName = args[++i];
            break;
          default : {
            throw new IllegalArgumentException("Unknown argument: " + opt);
          }
        }
 
        ++i;
      }
 
      if (queueManagerName == null) {
        throw new IllegalArgumentException("A queueManager name must be specified.");
      }
 
      if (destinationName == null) {
        throw new IllegalArgumentException("A destination name must be specified.");
      }
 
      // Whether the destination is a queue or a topic. Apply a simple check.
      if (destinationName.startsWith("topic://")) {
        isTopic = true;
      }
      else {
        // Otherwise, let's assume it is a queue.
        isTopic = false;
      }
    }
    catch (Exception e) {
      System.out.println(e.getMessage());
      printUsage();
      System.exit(-1);
    }
    return;
  }
 
  /**
   * Display usage help.
   */
  private static void printUsage() {
    System.out.println("\nUsage:");
    System.out
        .println("JmsProducer -m queueManagerName -d destinationName [-h host -p port -l channel -c connamelist -s cipher suite]");
    return;
  }
 
} // end class