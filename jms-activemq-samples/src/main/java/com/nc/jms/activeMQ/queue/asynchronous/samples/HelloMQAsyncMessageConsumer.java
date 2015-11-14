package com.nc.jms.activeMQ.queue.asynchronous.samples;

import java.util.Properties;

import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.Context;
import javax.naming.InitialContext;

public class HelloMQAsyncMessageConsumer implements MessageListener, ExceptionListener {

	static QueueConnection queueCon = null;

	public static void main(String[] args) {
		// JNDI configurations to setup the ActiveMQ broker
		Properties env = new Properties();
		env.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		env.put(Context.PROVIDER_URL, "tcp://localhost:61616");
		env.put("queue.QUEUE_NAME", "HelloMQAsync");

		try {
			InitialContext ctx = new InitialContext(env);

			// lookup the Queue object
			Queue queue = (Queue) ctx.lookup("QUEUE_NAME");

			// lookup the Queue connection factory
			QueueConnectionFactory queueConFactory = (QueueConnectionFactory) ctx.lookup("QueueConnectionFactory");

			// create a queue connection
			queueCon = queueConFactory.createQueueConnection();

			// create a queue session
			QueueSession session = queueCon.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

			// create a queue receiver
			QueueReceiver qReceiver = session.createReceiver(queue);

			// set as Async message Listener
			HelloMQAsyncMessageConsumer asyncReceiver = new HelloMQAsyncMessageConsumer();
			qReceiver.setMessageListener(asyncReceiver);

			queueCon.setExceptionListener(asyncReceiver);

			queueCon.start();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void onException(JMSException exception) {
		// TODO Auto-generated method stub
		System.err.println("an error occurred: " + exception);
	}

	public void onMessage(Message message) {
		// TODO Auto-generated method stub
		TextMessage msg = (TextMessage) message;
		try {
			if (msg.getText().equals("exit")) {
				queueCon.close();
				System.out.println("Application Exits");
			} else {
				System.out.println("received: " + msg.getText());
			}
		} catch (JMSException ex) {
			ex.printStackTrace();
		}
	}

}
