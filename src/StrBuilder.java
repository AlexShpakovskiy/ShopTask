public class StrBuilder {

    public static String builder(String[] Array) {
        String constructor = "";
        for (String symbol: Array) {
            constructor += symbol;
        }
        return constructor;
    }
}
