package com.activemq.messaging;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMQHelloWorldProducer implements Runnable {
    public void run() {
        try {
            // Create ConnectionFactory
            ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory("tcp://localhost:61616");

            // Create Connection
            Connection connection = activeMQConnectionFactory.createConnection();
            connection.start();

            // Create Session
            Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);

            // Create the destination (Topic or Queue)
            Destination destination = session.createQueue("MyQueue1");

            // Create MessageProducer from the Session to the Topic or
            // Queue
            MessageProducer producer = session.createProducer(destination);
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // Create messages
            String text = "ActiveMQ Hello world! From: "+ Thread.currentThread().getName() + " : "+ this.hashCode();
            TextMessage message = session.createTextMessage(text);

            // Tell the producer to send the message
            System.out.println("Sent message: " + message.hashCode()+ " : " + Thread.currentThread().getName());
            producer.send(message);

            // Clean up
            session.close();
            connection.close();
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e);
            e.printStackTrace();
        }
    }
}