package it.unibo.es2;

/**
 * iterface of Logics.
 */
public interface Logics {

    /**
     * @param s stringa del testo.
     * @param pair pair corrispondente.
     * @return testo da sostituire nella gui
     */
    String getText(String s, Pair<Integer, Integer> pair);

    /**
     * @return if it has to quit.
     */
    Boolean toQuit();

    /**
     * @param pair chiave della mappa.
     * @param text valore della mappa associato al pair.
     */
    void addPair(Pair<Integer, Integer> pair, String text);
}
