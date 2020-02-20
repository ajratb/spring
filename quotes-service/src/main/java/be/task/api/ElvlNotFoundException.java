package be.task.api;

/**
 *
 * @author ayrat
 */
public class ElvlNotFoundException extends RuntimeException {

    public ElvlNotFoundException(String isin) {
        super("Could not get elvl for isin: " + isin);
    }
}
