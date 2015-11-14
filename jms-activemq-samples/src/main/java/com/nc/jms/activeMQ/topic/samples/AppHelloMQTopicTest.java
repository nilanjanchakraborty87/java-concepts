package com.nc.jms.activeMQ.topic.samples;

import java.util.Properties;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.apache.activemq.ActiveMQConnection;

public class AppHelloMQTopicTest {

	private static HelloMQTopicPublisher publisher, publisherForMultiSubscriber, publisherForDurableSubscriber;
	private static HelloMQTopicSubscriber subscriber, multiSubscriber[] = new HelloMQTopicSubscriber[2];
	private static HelloMQTopicDurableSubscriber durableSubscriber;

	private static TopicConnectionFactory factory;

	static {
		Properties props = new Properties();
		props.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		props.put(Context.PROVIDER_URL, ActiveMQConnection.DEFAULT_BROKER_URL);

		InitialContext ctx;
		try {
			ctx = new InitialContext(props);
			factory = (TopicConnectionFactory) ctx.lookup("TopicConnectionFactory");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {

		try {

			// publisher with single subscriber
			publisher = new HelloMQTopicPublisher("publisher-publishsubscribe", "HelloMQTopicSingleSubscriber",
					factory);

			publisherForMultiSubscriber = new HelloMQTopicPublisher("publisher-multipleconsumer",
					"HelloMQTopicMultipleSubscriber", factory);

			publisherForDurableSubscriber = new HelloMQTopicPublisher("publisher-durableSubscriber",
					"HelloMQTopicDurableSubscriber", factory);

			subscriber = new HelloMQTopicSubscriber(factory, "subscriber-0", "HelloMQTopicSingleSubscriber");

			for (int i = 0; i < 2; i++)
				multiSubscriber[i] = new HelloMQTopicSubscriber(factory, "multiSubscriber-" + i,
						"HelloMQTopicMultipleSubscriber");

			testGetGreeting();
			testMultipleConsumers();
			trestdurableSubscriber();

			removesubscription();
			closeConnections();
			

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void testGetGreeting() {
		try {
			publisher.send("Shrabanti", "Chakraborty");

			String greeting1 = subscriber.getGreeting();
			System.out.println("Greeting received \"" + greeting1 + "\"");

		} catch (JMSException e) {
			System.err.println("a JMS Exception occurred");
		}
	}

	public static void testMultipleConsumers() {
		try {
			publisherForMultiSubscriber.send("Nilanjan", "Chakraborty");

			for (int i = 0; i < 2; i++) {
				String greeting = multiSubscriber[i].getGreeting();
				System.out.println("Greeting \"" + greeting + "\" received from multiSubscriber-" + i);
			}

		} catch (JMSException e) {
			System.err.println("a JMS Exception occurred");
		}
	}

	public static void trestdurableSubscriber() {
		try {
			publisherForDurableSubscriber.send("Hira / Anjan", "Chakraborty");

			durableSubscriber = new HelloMQTopicDurableSubscriber(factory, "durable-subscriber",
					"HelloMQTopicDurableSubscriber", "durable-subscription-0");

			System.out.println(durableSubscriber.getGreeting());
		} catch (JMSException e) {
			System.err.println("a JMS Exception occurred");
		}
	}

	public static void closeConnections() throws JMSException {
		publisher.closeConnections();
		publisherForMultiSubscriber.closeConnections();

		subscriber.closeConnections();
		for (int i = 0; i < 2; i++)
			multiSubscriber[i].closeConnections();

		publisherForDurableSubscriber.closeConnections();
		durableSubscriber.closeConnection();

	}

	public static void removesubscription() throws JMSException {
		durableSubscriber.removeSubscriber();
	}

}
