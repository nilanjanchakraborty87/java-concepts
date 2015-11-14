package com.nc.jms.activeMQ.queue.synchronous.samples;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class HelloMQMessageProducer implements Runnable {

	private ActiveMQConnectionFactory factory;

	private String queueName;

	public HelloMQMessageProducer(ActiveMQConnectionFactory factory, String queueName) {
		this.factory = factory;
		this.queueName = queueName;
	}

	public void run() {
		// TODO Auto-generated method stub
		try {
			// Establishing the connection
			Connection connection = factory.createConnection();
			connection.start();

			// Now creating the session
			Session jmsSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

			// Create the destination (Topic or Queue)
			Queue destination = jmsSession.createQueue(this.queueName);

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = jmsSession.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// Create a messages
			String text = "Hello ActiveMQ! From: " + Thread.currentThread().getName() + " : " + this.hashCode();
			TextMessage message = jmsSession.createTextMessage(text);

			// Tell the producer to send the message
			System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName());
			producer.send(message);
			
			jmsSession.close();
			connection.close();

		} catch (JMSException e) {
			System.err.println("Caught : " + e);
			e.printStackTrace();

		}

	}
}
