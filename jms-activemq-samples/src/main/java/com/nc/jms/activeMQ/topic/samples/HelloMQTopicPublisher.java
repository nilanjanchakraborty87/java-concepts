package com.nc.jms.activeMQ.topic.samples;

import static javax.jms.DeliveryMode.NON_PERSISTENT;

import javax.jms.Connection;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

public class HelloMQTopicPublisher {

	private Connection connection;
	private Session session;
	private MessageProducer producer;

	public HelloMQTopicPublisher(String clientId, String topicName, TopicConnectionFactory factory)
			throws JMSException {

		connection = factory.createTopicConnection();
		connection.setClientID(clientId);

		// creates the session
		session = ((TopicConnection) this.connection).createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

		// create the topic
		Topic topic = session.createTopic(topicName);

		this.producer = session.createProducer(topic);
		this.producer.setDeliveryMode(NON_PERSISTENT);
	}

	public void send(String fName, String lName) throws JMSException {
		String text = fName + " " + lName;

		TextMessage tm = this.session.createTextMessage(text);

		// send the message to the topic destination
		this.producer.send(tm);
	}

	public void closeConnections() throws JMSException {
		this.session.close();
		this.connection.close();
	}
}
