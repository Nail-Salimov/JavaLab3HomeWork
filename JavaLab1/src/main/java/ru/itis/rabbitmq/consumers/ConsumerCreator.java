package ru.itis.rabbitmq.consumers;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.rabbitmq.consumers.callbacks.coordinates.FireDeliverCallback;
import ru.itis.rabbitmq.consumers.callbacks.forms.VocationDeliverCallback;
import ru.itis.rabbitmq.consumers.consumer.Consumer;

@SpringBootApplication
public class ConsumerCreator {

    public static void main(String[] args) {
        String srcFire = "/home/nail/Progy/JavaLab3/JavaLab1/src/main/resources/patterns/fire.pdf";
        String destFire = "/home/nail/Progy/JavaLab3/JavaLab1/src/main/resources/fire/";

        String srcVocation = "/home/nail/Progy/JavaLab3/JavaLab1/src/main/resources/patterns/vocationPattern.pdf";
        String destVocation = "/home/nail/Progy/JavaLab3/JavaLab1/src/main/resources/vocation/";

        String russianFont = "src/main/resources/fonts/FreeSans.ttf";

        VocationDeliverCallback vocationDeliverCallback = new VocationDeliverCallback(srcVocation, destVocation, russianFont);
        Consumer deliverConsumer = new Consumer(vocationDeliverCallback);
        deliverConsumer.start();

        FireDeliverCallback fireDeliverCallback = new FireDeliverCallback(srcFire, destFire, russianFont);
        Consumer fireConsumer = new Consumer(fireDeliverCallback);
        fireConsumer.start();

    }
}
