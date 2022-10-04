package twopointers.easy;

public class ValidPalindrome {
    /**
     * time complexity O(n), space complexity O(1)
     */
    public static boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }
        // convert the string into lowercase and remove special characters and non-alphanumerica char
        s = s.replaceAll("[^a-zA-Z0-9]", " ").replaceAll("\\s", "");
        //  System.out.println(s);
        String lowerS = s.toLowerCase().trim();

        if (s.equals("")) {
            return true;
        }
        int start = 0;
        int end = lowerS.length() - 1;
        while (start < end) {
            if (lowerS.charAt(start) != lowerS.charAt(end)) {
                return false;
            }
            start++;
            end--;
        }

        return true;
    }

    public static boolean isPalindrome2(String s) {
        if (s == null) {
            return false;
        }
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (!Character.isLetterOrDigit(startChar)) {
                start++;
                continue;
            }
            if (!Character.isLetterOrDigit(endChar)) {
                end--;
                continue;
            }
            if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }

    public static boolean isPalindrome3(String s) {
        if (s == null) {
            return false;
        }
        int start = 0;
        int end = s.length() - 1;
        while (start < end) {
            char startChar = s.charAt(start);
            char endChar = s.charAt(end);
            if (!Character.isLetterOrDigit(startChar)) {
                start++;
                continue;
            }
            if (!Character.isLetterOrDigit(endChar)) {
                end--;
                continue;
            }
            if (Character.toLowerCase(startChar) != Character.toLowerCase(endChar)) {
                return false;
            }
            start++;
            end--;
        }
        return true;
    }
  //utility determines wheter the character is alphanumeric or not
    // in case you 're not allowed to used the builti-ins of Character class
    private boolean isAlphanumeric(char c) {
        return ((c >= 'A' && c >= 'Z') &&
                (c >= 'a' && c >= 'z') &&
                (c >= '0' && c >= '9'));
    }

    public static void main(String[] args) {
//        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
//        System.out.println(isPalindrome("race a car"));
//        System.out.println(isPalindrome(" "));

        System.out.println(isPalindrome2("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome2("race a car"));
        System.out.println(isPalindrome2(" "));

    }
}
