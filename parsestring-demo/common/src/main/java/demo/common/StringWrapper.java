package demo.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "stringwrapper")
public class StringWrapper {

    String source;
    String consonants;
    String vowels;

    public String getSource() {
        return source;
    }

    @XmlElement
    public void setSource(String source) {
        this.source = source;
    }

    public String getConsonants() {
        return consonants;
    }

    @XmlElement
    public void setConsonants(String source) {
        this.consonants = StringWrapper.getConsonants(source);
    }

    public String getVowels() {
        return vowels;
    }

    @XmlElement
    public void setVowels(String source) {
        this.vowels = StringWrapper.getVowels(source);
    }

    public StringWrapper() {
        super();
    }

    public StringWrapper(String source) {
        super();
        if (StringWrapper.isContainsNumbers(source)) {
            throw new IllegalArgumentException("request contains numbers");
        }
        this.source = source;
        this.consonants = StringWrapper.getConsonants(source);
        this.vowels = StringWrapper.getVowels(source);
    }

    public static boolean isVowel(char arg) {
        Pattern p = Pattern.compile("[aeiouyAEIOUY]");
        Matcher m = p.matcher(Character.toString(arg));
        return m.matches();
    }

    public static String getVowels(String arg) {
        String tempStr = "";
        char[] tempChar = arg.toCharArray();
        for (char c : tempChar) {
            if (isVowel(c)) {
                tempStr += c;
            }
        }
        return tempStr;
    }

    public static String getConsonants(String arg) {
        String tempStr = "";
        char[] tempChar = arg.toCharArray();
        for (char c : tempChar) {
            if (!isVowel(c)) {
                tempStr += c;
            }
        }
        return tempStr;
    }

    public static boolean isContainsNumbers(String source) {
        char[] tempChar = source.toCharArray();
        for (char c : tempChar) {
            Pattern p = Pattern.compile("[0-9]");
            Matcher m = p.matcher(Character.toString(c));
            if (m.matches()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "<stringwrapper>\n" + 
                    "\t<source>" + source + "</source>\n"+ 
                    "\t<consonants>" + consonants + "</consonants>\n"+ 
                    "\t<vowels>" + vowels + "</vowels>\n" + 
                "</stringwrapper>\n";

    }

}
