package demo.client;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.common.StringWrapperService;

public final class StringWrapperClientManyInv {

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

        ArrayList<String> requests = new ArrayList<String>();

        for (int i = 0; i < 1000; i++) {
            requests.add(StringWrapperClientManyInv.getRandomString());
        }
        System.out.println("#################################################");
        System.out.println("#Testing service with 1000 random words (~10 sec)");
        System.out.println("#################################################");

        for (String string : requests) {
            client.getParsedString(string);
        }
    }

}
