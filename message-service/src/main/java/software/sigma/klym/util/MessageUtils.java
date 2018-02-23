package software.sigma.klym.util;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Utility class for converting messages.
 *
 * @author Andriy Klymenko
 */
public final class MessageUtils {

    private static final String HASH_TAG = "#";

    private MessageUtils() {
        throw new IllegalAccessError("Utility class!");
    }

    /**
     * Returns a list of tags from the text.
     *
     * @param text text
     * @return list of tag names
     */
    public static List<String> extractTags(String text) {
        List<String> tags = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(text);
        while (tokenizer.hasMoreTokens()) {
            String token = tokenizer.nextToken();
            if (token.startsWith(HASH_TAG)) {
                tags.add(token);
            }
        }
        return tags;
    }
}
