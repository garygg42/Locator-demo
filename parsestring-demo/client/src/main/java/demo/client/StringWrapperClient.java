package demo.client;

import java.util.Random;
import java.util.Scanner;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import demo.common.StringWrapperService;

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

		if (args[0].equals("loop")) {
			while (true) {
				System.out.println(client.getParsedString(StringWrapperClient
				        .getRandomString()));
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} else if (args[0].equals("manual")) {
			@SuppressWarnings("resource")
			Scanner input = new Scanner(System.in);
			while (true) {
				System.out.print("Enter string to parse: ");
				String stp = input.nextLine();
				System.out.println(client.getParsedString(stp));
			}
		}
	}

}
