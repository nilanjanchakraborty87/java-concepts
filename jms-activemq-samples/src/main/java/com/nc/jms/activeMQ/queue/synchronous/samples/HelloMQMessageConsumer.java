package com.nc.jms.activeMQ.queue.synchronous.samples;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloMQMessageConsumer implements Runnable {

	private ActiveMQConnectionFactory factory;

	private String queueName;

	public HelloMQMessageConsumer(ActiveMQConnectionFactory factory, String queueName) {
		this.factory = factory;
		this.queueName = queueName;
	}

	public void run() {
		// TODO Auto-generated method stub
		// create the Queue in the session
		try {

			// Establishing the connection
			Connection connection = factory.createConnection();
			connection.start();

			// Now creating the session
			Session jmsSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			Queue queue = jmsSession.createQueue(this.queueName);

			// create the MessageConsumer
			MessageConsumer consumer = jmsSession.createConsumer(queue);

			// Receive the message from the consumer
			Message message = consumer.receive();

			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			System.out.println("Received(TextMessage): " + text);

			jmsSession.close();
			connection.close();

		} catch (JMSException e) {
			System.err.println("Caught : " + e);
			e.printStackTrace();

		}

	}

}
