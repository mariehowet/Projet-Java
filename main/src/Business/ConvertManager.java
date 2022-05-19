package Business;

import Model.Locality;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import Exception.*;

public class ConvertManager {
    static public Locality stringIntoLocality(String localite) throws LocalityException {

        Pattern patternCity = Pattern.compile("^([\\w\\s]+)", Pattern.CASE_INSENSITIVE);
        Matcher matcherCity = patternCity.matcher(localite);
        Pattern patternCountry = Pattern.compile("-(.*)-", Pattern.CASE_INSENSITIVE);
        Matcher matcherCountry = patternCountry.matcher(localite);
        Pattern patternPostCode = Pattern.compile("([\\w]+)$", Pattern.CASE_INSENSITIVE);
        Matcher matcherPostCode = patternPostCode.matcher(localite);

        if (matcherCity.find() && matcherCountry.find() && matcherPostCode.find()) {
            return new Locality (matcherCity.group(1),matcherPostCode.group(1),matcherCountry.group(1));
        } else {
            throw new LocalityException();
        }
    }
    static public int stringIntoId(String string) throws IdException {
        Pattern patternPassenger = Pattern.compile("(\\d+)-", Pattern.CASE_INSENSITIVE);
        Matcher matcher = patternPassenger.matcher(string);
        if (matcher.find()) {
            return  Integer.parseInt(matcher.group(1));
        } else {
            throw new IdException();
        }
    }
}
