package demo.service;

import javax.jws.WebService;

import demo.common.StringWrapper;
import demo.common.StringWrapperService;

@WebService(endpointInterface = "demo.common.StringWrapperService", serviceName = "StringWrapper")
public class StringWrapperServiceImpl implements StringWrapperService {

    public StringWrapper getParsedString(String string) {

        return new StringWrapper(string);
    }

}
