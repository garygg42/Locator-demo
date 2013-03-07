package demo.commands;

import java.util.Random;

import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import demo.common.StringWrapperService;

@Command(scope = "parsestring", name = "loop", description = "parses string")
public final class StringWrapperClientLoop extends OsgiCommandSupport {
    private StringWrapperService service;

    @Override
    protected Object doExecute() throws Exception {
        loop();
        return null;
    }
    
    public void loop() {
        while (true) {
            System.out.println(service.getParsedString(getRandomString()));
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getRandomString() {
        String generatedRandomString = "";
        Random rand = new Random();
        for (int i = 0; i < 10; i++) {
            generatedRandomString += (char) (rand.nextInt(25) + 97);
        }
        return generatedRandomString;
    }

    public void setService(StringWrapperService service) {
        this.service = service;
    }

}
