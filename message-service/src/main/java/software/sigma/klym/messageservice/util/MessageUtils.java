package software.sigma.klym.messageservice.util;

import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Utility class for converting messages.
 *
 * @author Andriy Klymenko
 */
public final class MessageUtils {

    public static final String HASH_TAG = "#";

    private MessageUtils() {
        throw new IllegalAccessError("Utility class!");
    }

    /**
     * Returns a list of tags from the text.
     *
     * @param text text
     * @return list of tag names
     */
    public static Set<String> extractTags(String text) {
        Set<String> tags = new HashSet<>();
        StringTokenizer tokenizer = new StringTokenizer(text);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.startsWith(HASH_TAG) && token.length() > 1) {
                tags.add(token.replace(HASH_TAG, ""));
            }
        }
        return tags;
    }
}
