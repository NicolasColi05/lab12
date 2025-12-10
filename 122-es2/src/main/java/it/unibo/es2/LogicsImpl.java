package it.unibo.es2;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * implementation of Logics.
 */
public final class LogicsImpl implements Logics, Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private static final String SIGNED = "*";
    private static final String NOT_SIGNED = " ";
    private final Map<Pair<Integer, Integer>, String> map = new LinkedHashMap<>();

    @Override
    public String getText(final String s, final Pair<Integer, Integer> pair) {
        if (" ".equals(s)) {
            map.replace(pair, SIGNED);
            return SIGNED;
        } else {
            map.replace(pair, NOT_SIGNED);
            return NOT_SIGNED; 
        }
    }

    @Override
    public Boolean toQuit() {
    return this.map.entrySet().stream()
            .filter(v -> SIGNED.equals(v.getValue()))
            .collect(Collectors.groupingBy(v -> v.getKey().x(), Collectors.counting()))
            .entrySet().stream()
            .anyMatch(v -> v.getValue().equals(4L)) || this.map.entrySet().stream()
            .filter(v -> SIGNED.equals(v.getValue()))
            .collect(Collectors.groupingBy(v -> v.getKey().y(), Collectors.counting()))
            .entrySet().stream()
            .anyMatch(v -> v.getValue().equals(4L));

    }

    @Override
    public void addPair(final Pair<Integer, Integer> pair, final String text) {
        map.put(pair, text);
    }
}
