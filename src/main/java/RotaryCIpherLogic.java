public class RotaryCIpherLogic {

    public boolean isCorrectMatrix(boolean[][] matrix) {
        if (matrix == null || matrix[0] == null) {
            return false;
        }
        if (matrix.length != matrix[0].length) {
            return false;
        }

        boolean[][] matrixCheck = new boolean[matrix.length][matrix.length];

        for (int i = 0; i < 4; i++) {
            if (!fillMatrixFromTraff(matrix, matrixCheck)) {
                return false;
            }
            matrix = rotation90(matrix);
        }
        return true;
    }

    private boolean fillMatrixFromTraff(boolean[][] traff, boolean[][] matrix) {

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (traff[i][j]) {
                    if (!matrix[i][j]) {
                        matrix[i][j] = true;
                    } else {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean[][] rotation90(boolean[][] matrix) {
        boolean[][] temp = new boolean[matrix.length][matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = matrix.length - 1; j >= 0; j--) {
                temp[i][matrix.length - 1 - j] = matrix[j][i];
            }
        }
        return temp;
    }

    public void encode (RotaryCipher ob){
        char[][] matrix =new char[ob.getMatrix().length][ob.getMatrix().length];
        int wordIndex=0;
        for(int i=0;i<4;i++){
            wordIndex=fillCharMatrix(matrix,ob.getMatrix(),ob.getWord(),wordIndex);
            ob.setMatrix(rotation90(ob.getMatrix()));
        }

        ob.setCipherMatrix(matrix);

    }

    private int fillCharMatrix(char[][] matrix, boolean[][] boolMatr,String word, int wordIndex){
        for(int i=0;i< boolMatr.length;i++){
            for(int j=0;j< boolMatr[i].length;j++){
                if(boolMatr[i][j]){
                    if(wordIndex>=word.length()){
                        return wordIndex;
                    }
                    matrix[i][j]=word.charAt(wordIndex++);
                }
            }
        }
        return  wordIndex;
    }

    public void decode(RotaryCipher ob){

        StringBuilder decodeWord = new StringBuilder();
        for(int i=0;i<4;i++){
            decodeWord.append(makeDecodeWord(ob.getCipherMatrix(),ob.getMatrix()));
            ob.setMatrix(rotation90(ob.getMatrix()));
        }
       ob.setWord(decodeWord.toString());
    }

    private StringBuilder  makeDecodeWord(char[][] matrix, boolean[][] boolMatr){
        StringBuilder str = new StringBuilder();
        for(int i=0;i< boolMatr.length;i++) {
            for (int j = 0; j < boolMatr[i].length; j++) {
                if(boolMatr[i][j]&&matrix[i][j]!=0){
                    str.append(matrix[i][j]);
                }
            }
        }
        return str;
    }
}
