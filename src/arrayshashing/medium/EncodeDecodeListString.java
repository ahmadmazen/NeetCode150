package arrayshashing.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Design an algorithm to encode a list of strings to a string.
 * The encoded string is then sent over the network and is decoded back to the original list of strings.
 * Implement the {} encode and decode methods.
 * Note:
 * The string may contain any possible characters out of 256 valid ascii characters.
 * Your algorithm should be generalized enough to work on any possible characters.
 * Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
 * Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
 * <p>
 * Input: [“lint”,“code”,“love”,“you”] Output: [“lint”,“code”,“love”,“you”]
 * Explanation: One possible encode method is: “lint:;code:;love:;you”
 * <p>
 * Example2
 * <p>
 * Input: [“we”, “say”, “:”, “yes”] Output: [“we”, “say”, “:”, “yes”]
 * Explanation: One possible encode method is: “we:;say:;:::;yes”
 */

/*
 * Easiest way to do that - using out of the box stream Api and Arrays utilities
 * I implemented them down for learning purposes
 * second approach will comply to the problem constraints
 *
 */
public class EncodeDecodeListString {


    public static String encode_v1(List<String> strs) {
        final String DELIMETER = ":;";
        return strs.stream().collect(Collectors.joining(DELIMETER));
    }

    public static List<String> decode_v1(String str) {
        final String DELIMETER = ":;";
        return Arrays.asList(str.split(DELIMETER));
    }

    public static String encode_v2(List<String> strs) {
        StringBuilder encoded = new StringBuilder();
        for (String str : strs) {
            encoded.append(str.length()).append("#").append(str);
        }
        return encoded.toString();
    }

    public static List<String> decode_v2(String str) {
        List<String> outputlist = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int j = i;
            while (str.charAt(j) != '#') {
                j++;
            }
            int beginIdx = j + 1;
            int length = Integer.valueOf(str.substring(i, j));
            int endIndx = j + 1 + length;
            i = endIndx;
            outputlist.add(str.substring(beginIdx, endIndx));
        }
        return outputlist;
    }


    public static void main(String[] args) {
        List<String> input = Arrays.asList("lint", "code", "love", "you");
        System.out.println(encode_v1(input));
        decode_v1(encode_v1(input)).stream().forEach(System.out::println);

        input = Arrays.asList("we", "say", ":", "yes");
        System.out.println(encode_v2(input));
        decode_v2(encode_v2(input)).stream().forEach(System.out::println);

        input = Arrays.asList("we", "say", "#", "yes");
        System.out.println(encode_v2(input));
        decode_v2(encode_v2(input)).stream().forEach(System.out::println);
    }

}
