package com.nc.jms.activeMQ.queue.asynchronous.samples;

import java.util.Properties;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class HelloMQAsyncMessageProducer {

	public static void main(String[] args) {
		try {
			Properties env = new Properties();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
			env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
			env.put("queue.QUEUE_NAME", "HelloMQAsync");

			InitialContext ctx = new InitialContext(env);

			// creating the Queue from JNDI lookup
			Queue queue = (Queue) ctx.lookup("QUEUE_NAME");

			// creating queueConnectionFactory
			QueueConnectionFactory qConnFactory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");

			// creating QueueConnection
			QueueConnection qConn = qConnFactory.createQueueConnection();

			// create QueueSession
			QueueSession qSession = qConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// create the QueueMessaageProducer
			QueueSender qSender = qSession.createSender(queue);

			for (int i = 0; i < 5; i++) {
				String msg = "exit";
				int priority = 0;
				if (i < 4){
					priority = i + 5;
					msg = "Hello ActiveMQ.." + i;
				}

				TextMessage text = qSession.createTextMessage(msg);
				qSender.send(text, DeliveryMode.NON_PERSISTENT, priority, 40000);

			}

			qSession.close();
			qConn.close();

		} catch (Exception e) {
			System.err.println(e);
		}

	}

}
