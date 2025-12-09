package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final List<Integer> listOfButton = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        for (int i = 0; i < size; i++) {
            listOfButton.add(0);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.listOfButton.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Integer> values() {
        final List<Integer> values = new ArrayList<>();
        for (final Integer i : listOfButton) {
            values.add(i);
        }
        return values;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Boolean> enabledStates() {
        // final List<Boolean> states = new ArrayList<>();
        // for (final Button button : listOfButton) {
        //     states.add(button.isEnebled());
        // }
        // return listOfButton.stream().map(Button::isEnebled).toList();
        return listOfButton.stream()
            .map(v -> { 
                return v < listOfButton.size() - 1; 
            })
            .toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        final int temp = listOfButton.get(elem);
        if (temp < listOfButton.size() - 1) {
            listOfButton.remove(elem);
            listOfButton.add(elem, temp + 1);
        }

        return listOfButton.get(elem);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
       final StringBuilder result = new StringBuilder("<<");
       for (final Integer i : listOfButton) {
            result.append('|').append(i);
       }
       return result.append(">>").toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return listOfButton.stream().distinct().count() == 1L;
    }
}
