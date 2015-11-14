package com.nc.jms.activeMQ.queue.reply.samples;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * sends the message to the MOM and wait for the reply to arrive
 * 
 * @author nchak2
 *
 */
public class JMSSender {

	private Connection connection;
	private Session session;
	private Queue sendingQueue;
	private Queue replyQueue;
	private MessageProducer producer;
	private MessageConsumer replyConsumer;

	public JMSSender(QueueConnectionFactory factory, String queueName) throws JMSException {

		this.connection = factory.createQueueConnection();
		this.connection.setClientID("JMS-replyto-sender");

		this.session = ((QueueConnection) this.connection).createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		// create the sending queue
		this.sendingQueue = ((QueueSession) this.session).createQueue(queueName);

		// create the reply queue
		this.replyQueue = ((QueueSession) this.session).createTemporaryQueue();

		// creating the message producer to send the message to the sending
		// queue
		this.producer = this.session.createProducer(sendingQueue);

		// creating the message consumer
		this.replyConsumer = this.session.createConsumer(this.replyQueue);
		this.replyConsumer.setMessageListener(new MessageListener() {

			public void onMessage(Message message) {
				try {
					if (message != null && message instanceof TextMessage) {
						TextMessage textMessage = (TextMessage) message;
						System.out.println("Received reply message \"" + textMessage.getText() + "\"");
					}
				} catch (JMSException e) {
					// DO NOTHING
				}
			}
		});

		// start the connection to enable the reply consumer
		this.connection.start();
	}

	public void sendMessage() throws JMSException {
		String str = "Hello ActiveMQ. Please reply to my message";

		TextMessage tm = this.session.createTextMessage(str);
		tm.setJMSReplyTo(this.replyQueue);
		tm.setJMSCorrelationID(Long.toString(System.currentTimeMillis()));
		this.producer.send(tm);

	}

	public static void main(String[] args) throws JMSException {
		JMSSender sender = new JMSSender(new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL),
				"JMS_REPLY.QUEUE");
		sender.sendMessage();
	}

}
