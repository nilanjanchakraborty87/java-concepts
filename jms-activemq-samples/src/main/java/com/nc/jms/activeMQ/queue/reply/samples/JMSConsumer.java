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

public class JMSConsumer implements MessageListener {

	private Connection connection;
	private Session session;
	private Queue receivingQueue;
	private MessageProducer replyProducer;
	private MessageConsumer consumer;

	public JMSConsumer(QueueConnectionFactory factory, String queueName) throws JMSException {

		this.connection = factory.createQueueConnection();
		this.connection.setClientID("JMS-replyto-consumer");

		this.session = ((QueueConnection) this.connection).createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

		// create the actual queue
		this.receivingQueue = ((QueueSession) this.session).createQueue(queueName);

		// creating the message consumer to receive the message to the sending
		// queue
		this.consumer = this.session.createConsumer(receivingQueue);
		this.consumer.setMessageListener(this);

		// creating the reply producer
		this.replyProducer = this.session.createProducer(null);

		// start the connection to enable the reply consumer
		this.connection.start();
	}

	public void onMessage(Message message) {
		try {
			TextMessage response = this.session.createTextMessage();
			if (message instanceof TextMessage) {
				System.out.println("Consumed message -> \"" + ((TextMessage) message).getText() + "\"");
				response.setText("Received the message successfully!!!");
			}

			// setting the same JMSCorrelationId while sending the reply back
			response.setJMSCorrelationID(message.getJMSCorrelationID());

			this.replyProducer.send(message.getJMSReplyTo(), response);
		} catch (JMSException e) {
			// Handle the exception appropriately
		}
	}

	public static void main(String[] args) throws JMSException {
		new JMSConsumer(new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL), "JMS_REPLY.QUEUE");

	}

}
