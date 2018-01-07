package com.activemq.messaging;

public class ActiveMQApp {

    public static void main(String[] args) throws Exception {

        (new Thread(new ActiveMQHelloWorldProducer())).start();
        (new Thread(new ActiveMQHelloWorldProducer())).start();
        (new Thread(new HelloWorldConsumer())).start();
        Thread.sleep(10000);
        (new Thread(new HelloWorldConsumer())).start();
        (new Thread(new ActiveMQHelloWorldProducer())).start();
        (new Thread(new ActiveMQHelloWorldProducer())).start();
        (new Thread(new HelloWorldConsumer())).start();

    }
}