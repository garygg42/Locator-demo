package demo.client;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.common.StringWrapper;
import demo.common.StringWrapperService;

@SuppressWarnings("restriction")
public final class StringWrapperClient {

    public static String getRandomString() {
        String generatedRandomString = "";
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            generatedRandomString += (char) (rand.nextInt(25) + 97);
        }
        return generatedRandomString;
    }

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context = null;

        context = new ClassPathXmlApplicationContext(
                new String[] { "META-INF/spring/beans.xml" });

        StringWrapperService client = (StringWrapperService) context
                .getBean("stringWrapperClient");

        if (args[0].equals("25")) {
            ArrayList<String> requests = new ArrayList<String>();

            for (int i = 0; i < 25; i++) {
                requests.add(StringWrapperClient.getRandomString());
            }
            System.out.println("#####################################");
            System.out.println("#Testing service with 25 random words");
            System.out.println("#####################################");
            for (String string : requests) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(StringWrapper.class);

                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                    jaxbMarshaller.setProperty(
                            Marshaller.JAXB_FORMATTED_OUTPUT, true);

                    jaxbMarshaller.marshal(client.getParsedString(string),
                            System.out);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("#####################################");
            System.out.println("#Throwing business exception");
            System.out.println("#####################################");
            try {
                client.getParsedString("AAA123");
            } catch (RuntimeException e) {
                e.printStackTrace();
            }
        }

        else if (args[0].equals("1000")) {
            ArrayList<String> requests = new ArrayList<String>();

            for (int i = 0; i < 1000; i++) {
                requests.add(StringWrapperClient.getRandomString());
            }
            System.out
                    .println("#################################################");
            System.out
                    .println("#Testing service with 1000 random words (~10 sec)");
            System.out
                    .println("#################################################");

            for (String string : requests) {
                client.getParsedString(string);
            }
        }

        else if (args[0].equals("loop")) {
            while (true) {
                JAXBContext jaxbContext;
                try {
                    jaxbContext = JAXBContext.newInstance(StringWrapper.class);

                    Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                    jaxbMarshaller.setProperty(
                            Marshaller.JAXB_FORMATTED_OUTPUT, true);

                    jaxbMarshaller.marshal(client
                            .getParsedString(StringWrapperClient
                                    .getRandomString()), System.out);
                } catch (JAXBException e) {
                    e.printStackTrace();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        else if (args[0].equals("manual")) {
            Scanner in = new Scanner(System.in);
            JAXBContext jaxbContext;
            try {
                jaxbContext = JAXBContext.newInstance(StringWrapper.class);

                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT,
                        true);

                while (true) {
                    System.out.print("Enter string to parse: ");
                    String stp = in.nextLine();
                    jaxbMarshaller.marshal(client.getParsedString(stp),
                            System.out);
                }
            } catch (JAXBException e) {
                e.printStackTrace();
            }
        }
    }

}
