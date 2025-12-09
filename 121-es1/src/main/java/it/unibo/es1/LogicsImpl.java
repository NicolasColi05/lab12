package it.unibo.es1;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the Logics interface.
 */
public class LogicsImpl implements Logics {

    private final List<Button> listOfButton = new ArrayList<>();

    /**
     * Constructor.
     *
     * @param size the size of the logics
     */
    public LogicsImpl(final int size) {
        for (int i = 0; i < size; i++) {
            listOfButton.add(new Button(0, size - 1));
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
        for (final Button button : listOfButton) {
            values.add(button.getValue());
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
        return listOfButton.stream().map(Button::isEnebled).toList();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hit(final int elem) {
        this.listOfButton.get(elem).buttonHit();
        return listOfButton.get(elem).getValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String result() {
       final StringBuilder result = new StringBuilder("<<");
       for (final Button button : listOfButton) {
            result.append('|').append(button.getValue());
       }
       return result.append(">>").toString();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean toQuit() {
        return listOfButton.stream().map(Button:: getValue).distinct().count() == 1L;
    }

    private final class Button {
        private int value;
        private boolean enabled;
        private final int max;

        Button(final int value, final int max) {
            this.value = value;
            this.enabled = true;
            this.max = max;
        }

        public int getValue() {
            return value;
        }

        public boolean isEnebled() {
            return enabled;
        }

        public void setDisabledStates() {
            this.enabled = false;
        }

        public void buttonHit() {
            if (value < max) {
                this.value = this.value + 1;
            } else {
                setDisabledStates();
            }
        }
    }
}
