package demo.commands;

import org.apache.felix.gogo.commands.Argument;
import org.apache.felix.gogo.commands.Command;
import org.apache.karaf.shell.console.OsgiCommandSupport;

import demo.common.StringWrapperService;

@Command(scope = "parsestring", name = "parse", description = "parses string")
public final class StringWrapperClientOSGI extends OsgiCommandSupport {
    private StringWrapperService service;

    @Argument(index = 0, name = "stringToParse", description = "stringToParse", required = true, multiValued = false)
    String stringToParse;

    @Override
    protected Object doExecute() throws Exception {
        System.out.println(service.getParsedString(this.stringToParse));
        return null;
    }

    public void setService(StringWrapperService service) {
        this.service = service;
    }

}
