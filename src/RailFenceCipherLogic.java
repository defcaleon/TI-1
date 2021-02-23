public class RailFenceCipherLogic {

    public void encode(RailFenceCipher ob) {

        String word = ob.getCipher();
        int height = ob.getHeight();


        char[][] matrix = new char[height][word.length()];

        matrixEncodeInit(matrix, word, height);
        word = encodeWord(matrix);

        ob.setCipher(word);


    }

    private int incJ(boolean flag, int j) {

        if (flag) {
            j++;
        } else {
            j--;
        }
        return j;
    }

    private boolean flagStatus(int height, int currIter, boolean flag) {

        if (currIter + 1 == height) {
            flag = !flag;
        } else {
            if (currIter == 0) {
                flag = !flag;
            }

        }
        return flag;
    }

    private void matrixEncodeInit(char[][] matrix, String word, int height) {


        int j = 0;
        boolean flag = true;

        for (int i = 0; i < word.length(); i++) {

            matrix[j][i] = word.charAt(i);

            j = incJ(flag, j);
            flag = flagStatus(height, j, flag);
        }

    }

    private String encodeWord(char[][] matrix) {
        StringBuilder word = new StringBuilder();
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                if (aChar != 0) {
                    word.append(aChar);
                }
            }
        }
        return word.toString();
    }

    public void decode(RailFenceCipher ob) {
        String word = ob.getCipher();
        int height = ob.getHeight();

        char[][] matrix = new char[height][word.length()];

        matrixDecodeInit(matrix, word, height);
        ob.setCipher(decodeWord(matrix, height, word.length()));

    }

    private void matrixDecodeInit(char[][] matrix, String word, int height) {

        int cycle = height * 2 - 2;

        int lineIndex = 0;
        for (int i = 0; i < height; i++) {

            if (i == 0 || i == height - 1) {
                lineIndex += fillLine(matrix[i], lineIndex, word, cycle, i);
            } else {
                lineIndex = fillLine(matrix[i], lineIndex, word, cycle / 2, i);
            }
        }
    }

    private int fillLine(char[] line, int lineStart, String word, int step, int start) {


        int i = start;
        int index = lineStart;
        while (i < line.length) {
            line[i] = word.charAt(index++);

            i += step;
        }
        return index;
    }

    private String decodeWord(char[][] matrix, int height, int length) {
        StringBuilder result = new StringBuilder();

        boolean flag = true;
        int j = 0;
        for (int i = 0; i < length; i++) {

            result.append(matrix[j][i]);

            j = incJ(flag, j);
            flag = flagStatus(height, j, flag);

        }

        return result.toString();
    }




}