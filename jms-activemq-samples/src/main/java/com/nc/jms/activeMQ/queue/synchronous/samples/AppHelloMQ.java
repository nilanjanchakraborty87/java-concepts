package com.nc.jms.activeMQ.queue.synchronous.samples;

import org.apache.activemq.ActiveMQConnectionFactory;

/**
 * Hello world!
 *
 */
public class AppHelloMQ {

	private static final String QUEUE_NAME = "HelloMQ";

	public static void main(String[] args) {
		try {
			// Creating the transport connector
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

			Thread consumerThread = new Thread(new HelloMQMessageConsumer(connectionFactory, QUEUE_NAME));
			consumerThread.start();

			try {
				Thread.sleep(1000);
			} catch (InterruptedException ie) {
				ie.printStackTrace();
			}

			Thread producerThread = new Thread(new HelloMQMessageProducer(connectionFactory, QUEUE_NAME));
			producerThread.start();

		} catch (Exception ex) {
			System.err.println("Caught : " + ex);
			ex.printStackTrace();
		}
	}
}
