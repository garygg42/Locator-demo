package demo.common;

import javax.jws.WebService;

@WebService(targetNamespace = "http://StringWrapper.com")
public interface StringWrapperService {

    public StringWrapper getParsedString(String string);

}
