package com.nc.jms.activeMQ.topic.samples;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;

public class HelloMQTopicSubscriber {

	private Connection connection;

	private Session session;
	private MessageConsumer consumer;

	public HelloMQTopicSubscriber(TopicConnectionFactory factory, String clientId, String topicName)
			throws JMSException {
		// TODO Auto-generated constructor stub
		this.connection = factory.createTopicConnection();
		this.connection.setClientID(clientId);

		this.session = ((TopicConnection)this.connection).createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		Topic topic = this.session.createTopic(topicName);
		this.consumer = ((TopicSession)this.session).createConsumer(topic);
		this.connection.start();
	}

	public String getGreeting() throws JMSException {
		String greeting = "no greeting";

		Message message = this.consumer.receive();

		// check if a message was received
		if (message != null) {
			// cast the message to the correct type
			TextMessage textMessage = (TextMessage) message;

			// retrieve the message content
			String text = textMessage.getText();

			// create greeting
			greeting = "Hello " + text + "!";
		}
		return greeting;
	}

	public void closeConnections() throws JMSException {
		this.session.close();
		this.connection.close();
	}
}
