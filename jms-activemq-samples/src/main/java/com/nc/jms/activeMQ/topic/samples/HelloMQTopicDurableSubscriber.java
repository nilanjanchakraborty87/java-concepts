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

public class HelloMQTopicDurableSubscriber {

	private Connection connection;

	private Session session;
	private MessageConsumer consumer;
	private String subscriptionName;

	/**
	 * For Durable consumer has to set clientId and the subscriptionName
	 * 
	 * @param factory
	 * @param clientId
	 * @param topicName
	 * @param subscriptionName
	 * @throws JMSException
	 */
	public HelloMQTopicDurableSubscriber(TopicConnectionFactory factory, String clientId, String topicName,
			String subscriptionName) throws JMSException {
		this.connection = factory.createTopicConnection();
		this.connection.setClientID(clientId);

		this.session = ((TopicConnection) this.connection).createTopicSession(false, Session.CLIENT_ACKNOWLEDGE);
		Topic topic = this.session.createTopic(topicName);
		this.subscriptionName = subscriptionName;

		this.consumer = this.session.createDurableSubscriber(topic, subscriptionName);
		this.connection.start();
	}

	public void closeConnection() throws JMSException {
		this.connection.close();

	}

	public void removeSubscriber() throws JMSException {
		this.consumer.close();
		this.session.unsubscribe(this.subscriptionName);
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

		message.acknowledge();

		return greeting;
	}

}
