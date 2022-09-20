package gwt.material.design.client.sanitizer;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class ValueSanitizer {

    public static void main(String[] args) {
        System.out.println("Reserved Words : " + containsReservedString("abstract"));
        System.out.println("Special : " + containsSpecial("$"));
        System.out.println("Numeric : " + containsNumeric("12"));
        System.out.println("Unicode : " + containsUnicode("∫"));
        System.out.println("Chinese : " + containsChinese("汉字"));
    }

    public static boolean containsReservedString(String value) {
        return contains(Keywords.RESERVED_WORDS, value);
    }

    public static boolean containsSpecial(String value) {
        return matches(Patterns.SPECIAL, value);
    }

    public static boolean containsNumeric(String value) {
        return true;
    }

    //TODO: Non Whitespace C0
    //TODO: Non Whitespace C1
    //TODO: Whitespace
    //TODO: Unicode additional control characters
    //TODO: Byte order marks

    public static boolean containsUnicode(String value) {
       return true;
    }

    public static boolean containsChinese(String value) {
        return matches(Patterns.CHINESE, value);
    }

    public static boolean matches(String pattern, String value) {
        RegExp regExp = RegExp.compile(pattern);
        MatchResult matcher = regExp.exec(value);
        return matcher != null;
    }

    public static boolean contains(String[] strings, String value) {
        for (String string : strings) {
            if (value.equals(string)) {
                return true;
            }
        }
        return false;
    }
}
