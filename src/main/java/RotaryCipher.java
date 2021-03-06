import java.util.Arrays;

public class RotaryCipher {

    private char[][] cipherMatrix;
    private boolean[][] matrix;
    private String word;

    public RotaryCipher(boolean[][] matrix, String word) {
        this.matrix = matrix;
        this.word = word;
    }

    public boolean[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(boolean[][] matrix) {
        this.matrix = matrix;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public char[][] getCipherMatrix() {
        return cipherMatrix;
    }

    public void setCipherMatrix(char[][] cipherMatrix) {
        this.cipherMatrix = cipherMatrix;
    }

    @Override
    public String toString() {
        return "RotaryCipher{" +
                "matrix=" + Arrays.toString(matrix) +
                ", word='" + word + '\'' +
                '}';
    }
}
