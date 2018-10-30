public class Myequals {
    public static void main(String[] args) {
        String str1 = "ABC";
        String str2 = "ABC";
        System.out.println("result:" + myEquals1(str1, str2));

        System.out.println("result:" + myEquals2(str1, str2));
    }

    public static boolean myEquals1(String str1, String str2) {
        assert ((str1 != null) && (str2 != null));
        if (str1.length() != str2.length()) {
            return false;
        }

        int length = str1.length();
        char[] data1 = str1.toCharArray();
        char[] data2 = str2.toCharArray();

        for (int i = 0; i < length; i++) {
            if (data1[i] != data2[i]) {
                return false;
            }
        }
        return true;

    }

    public static boolean myEquals2(String str1, String str2) {
        assert ((str1 != null) && (str2 != null));
        str1.intern();
        str2.intern();
        if (str1 != str2) {
            return false;
        }
        return true;
    }
}