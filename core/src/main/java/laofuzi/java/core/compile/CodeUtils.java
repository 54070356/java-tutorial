
package laofuzi.java.core.compile;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * 
 *
 */
class CodeUtils {
    static URI toURI(String name) {
        try {
            return new URI(name);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
