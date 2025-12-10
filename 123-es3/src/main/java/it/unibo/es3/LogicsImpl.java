package it.unibo.es3;

import java.io.Serial;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

/**
 * implemtation of logics.
 */
public final class LogicsImpl implements Logics, Serializable {

    @Serial
    private static final long serialVersionUID = 2L;
    private int count;
    private final int[][] matrix;

    /**
     * @param size of matrix.
     */
    public LogicsImpl(final int size) {
        this.count = 0;
        matrix = new int[size][size];
    }

    @Override
    public List<Integer> getNewStatus() {
        int index;
        if (count == 0) {
            count++;
            final Random random = new Random();
            final List<Integer> list = new LinkedList<>();
            for (int i = 0; i < 3; i++) {
                list.add(random.nextInt(100));
                index = 0;
                for (int z = 0; z < matrix.length; z++) { //NOPMD i prefer for in this case
                    for (int j = 0; j < matrix.length; j++) {
                        if (index == list.getLast()) {
                            matrix[z][j] = 1;
                        }
                        index++;
                    }
                }
            }
            return list;
        } else {
            final List<Pair<Integer, Integer>> list = new LinkedList<>();
            for (int i = 0; i < matrix.length; i++) {
                for (int j = 0; j < matrix.length; j++) {
                    if (matrix[i][j] == 1) {
                        list.add(new Pair<>(i, j));
                    }
                }
            }
            return expandMatrix(list);
        }
    }

    /**
     * @param list a list of pair, that rappresent the position of the * that we have to expand 
     * @return a list of the changed index of the matrix.
     */
    private List<Integer> expandMatrix(final List<Pair<Integer, Integer>> list) {

        final List<Integer> list2 = new LinkedList<>();
        for (final Pair<Integer, Integer> pair : list) {
            for (int x = pair.x() - 1 > 0 ? pair.x() - 1 : 0; x <= pair.x() + 1 && x < matrix.length; x++) {
                for (int y = pair.y() - 1 > 0 ? pair.y() - 1 : 0; y <= pair.y() + 1 && y < matrix.length; y++) {
                    matrix[x][y] = 1;
                    list2.add(x * matrix.length + y);
                }
            }
        }
        return list2;
    }

    @Override
    public boolean toQuit() {
        for (final int[] is : matrix) {
            for (final Integer i : is) {
                if (!i.equals(1)) {
                    return false;
                }
            }
        }
        return true;
    }
}
