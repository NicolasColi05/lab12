package it.unibo.es3;

import java.util.List;

/**
 * interface that model the logics.
 */
public interface Logics {

    /**
     * @return a list whit all the button that have to change.
     */
    List<Integer> getNewStatus();

    /**
     * @return a boolean that explain if the gui has to quit.
     */
    boolean toQuit();

}
