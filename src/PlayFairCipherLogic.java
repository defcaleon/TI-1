import java.util.*;

public class PlayFairCipherLogic {

    public void encode(PlayFairCipher ob) {

        PlayFairCipher.Lang lang = ob.getLang();
        String word = ob.getCipher();
        String key = ob.getKey();

        word = makeBigrams(word,lang);

        Set<Character> set = setInit(lang);
        key = ColumnarTranspositionCipherLogic.keyConvert(key);
        char[][] matrix = matrixInit(lang);
        fillMatrix(matrix, set, key);

        String newWord=makeEncodeWord(matrix,word);
        ob.setCipher(newWord);

    }

    private Set<Character> setInit(PlayFairCipher.Lang lang) {
        Set<Character> set;
        if (lang == PlayFairCipher.Lang.en) {
            set = enSetInit();
        } else {
            set = ruSetInit();
        }
        return set;
    }

    private Set<Character> enSetInit() {
        Set<Character> enSet = new HashSet<>();
        for (char c = 'A'; c <= 'Z'; c++) {
            enSet.add(c);

        }
        enSet.remove('J');

        return enSet;
    }

    private Set<Character> ruSetInit() {
        Set<Character> ruSet = new LinkedHashSet<>();
        for (char c = 'А'; c <= 'Я'; c++) {
            ruSet.add(c);

        }
        ruSet.add('-');
        ruSet.add('1');
        ruSet.add('2');
        return ruSet;
    }

    private char[][] matrixInit(PlayFairCipher.Lang lang) {
        final int RUMATRIXSIZE = 6;
        final int ENMATRIXSIZE = 5;

        char[][] matrix;
        if (lang == PlayFairCipher.Lang.ru) {
            matrix = new char[RUMATRIXSIZE][RUMATRIXSIZE];
        } else {
            matrix = new char[ENMATRIXSIZE][ENMATRIXSIZE];
        }
        return matrix;
    }

    private void fillMatrix(char[][] matrix, Set<Character> set, String key) {

        int index = 0;
        String word = key.toUpperCase();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (index < key.length()) {
                    while (index < key.length() && !set.contains(word.charAt(index))) {
                        index++;
                    }
                    matrix[i][j] = word.charAt(index++);
                } else {
                    matrix[i][j] = set.iterator().next();

                }
                set.remove(matrix[i][j]);
            }
        }

    }

    private String makeBigrams(String word, PlayFairCipher.Lang lang) {

        if (word == null) {
            return null;
        }


        word=word.toUpperCase();
        word = word.replaceAll("\\s+", ""); //delete all spaces in word
        if(lang==PlayFairCipher.Lang.en){
            word = word.replaceAll("J", "I"); //repalce J into I
        }

        if (word.length() > 0) {


            StringBuilder result = new StringBuilder();
            result.append(word.charAt(0));

            int i = 1;
            while (i < word.length()) {
                if (word.charAt(i) == result.charAt(i - 1)) {
                    result.append(letterToInput(lang));
                    result.append(word.charAt(i));
                } else {
                    result.append(word.charAt(i));

                }
                i++;


            }

            if (result.length() % 2 == 1) {
                result.append(letterToInput(lang));
            }

            return result.toString();
        } else {
            return word;
        }

    }

    private char letterToInput(PlayFairCipher.Lang lang) {
        return lang == PlayFairCipher.Lang.en ? 'X' : 'Я';
    }

    private String makeEncodeWord(char[][] matrix, String biagram){

        StringBuilder result = new StringBuilder();

        char[] chars=new char[2];

        for(int i=0;i<biagram.length();i+=2){

            chars[0]=biagram.charAt(i);
            chars[1]=biagram.charAt(i+1);

            String pair = new String(chars);

            chars=encodeBiagram(matrix,pair);

            result.append(chars[0]);
            result.append(chars[1]);
        }

        return  result.toString();
    }

    private char[] encodeBiagram(char[][] matrix, String pair){

        if(matrix==null||pair==null){
            return null;
        }

        MatrixPos let1 = getPosition(matrix,pair.charAt(0));
        MatrixPos let2 = getPosition(matrix,pair.charAt(1));
        char[] chars = new char[2];

        int rowSize=matrix.length;
        int colSize=matrix[0].length;

        if(let1.getRow()==let2.getRow()){
               chars[0]=let1.getCol()+1>=colSize? matrix[let1.getRow()][0]:matrix[let1.getRow()][let1.getCol()+1];
               chars[1]=let2.getCol()+1>=colSize? matrix[let2.getRow()][0]:matrix[let2.getRow()][let2.getCol()+1];
        }else
        {
            if(let1.getCol()==let2.getCol()){
                chars[0]=let1.getRow()+1>=rowSize? matrix[0][let1.getCol()]:matrix[let1.getRow()+1][let1.getCol()];
                chars[1]=let2.getRow()+1>=rowSize? matrix[0][let2.getCol()]:matrix[let2.getRow()+1][let2.getCol()];
            }else
            {
                chars[0]=matrix[let1.getRow()][let2.getCol()];
                chars[1]=matrix[let2.getRow()][let1.getCol()];

            }
        }
        return chars;
    }

    private MatrixPos getPosition(char[][] matrix, char c){

        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                if(matrix[i][j]==c){
                    return new MatrixPos(i,j);
                }
            }
        }
        return  null;
    }

    public void decode(PlayFairCipher ob){

        PlayFairCipher.Lang lang = ob.getLang();
        String word = ob.getCipher();
        String key = ob.getKey();

        word = word.toUpperCase();
        if(word.length()%2==1){
            word=word+"X";
        }



        Set<Character> set = setInit(lang);
        key = ColumnarTranspositionCipherLogic.keyConvert(key);
        char[][] matrix = matrixInit(lang);
        fillMatrix(matrix, set, key);


        String newWord=makeDecodeWord(matrix,word);
        ob.setCipher(newWord);
    }

    private String makeDecodeWord(char[][] matrix, String biagram){

        StringBuilder result = new StringBuilder();

        char[] chars=new char[2];

        for(int i=0;i<biagram.length();i+=2){

            chars[0]=biagram.charAt(i);
            chars[1]=biagram.charAt(i+1);

            String pair = new String(chars);

            chars=decodeBiagram(matrix,pair);

            result.append(chars[0]);
            result.append(chars[1]);
        }

        return  result.toString();
    }

    private char[] decodeBiagram(char[][] matrix, String pair){

        if(matrix==null||pair==null){
            return null;
        }



        MatrixPos let1 = getPosition(matrix,pair.charAt(0));
        MatrixPos let2 = getPosition(matrix,pair.charAt(1));
        char[] chars = new char[2];


        int rowSize=matrix.length;
        int colSize=matrix[0].length;

        if(let1.getRow()==let2.getRow()){
            chars[0]=let1.getCol()-1<0? matrix[let1.getRow()][colSize-1]:matrix[let1.getRow()][let1.getCol()-1];
            chars[1]=let2.getCol()-1<0? matrix[let2.getRow()][colSize-1]:matrix[let2.getRow()][let2.getCol()-1];
        }else
        {
            if(let1.getCol()==let2.getCol()){
                chars[0]=let1.getRow()-1<0? matrix[rowSize-1][let1.getCol()]:matrix[let1.getRow()-1][let1.getCol()];
                chars[1]=let2.getRow()-1<0? matrix[rowSize-1][let2.getCol()]:matrix[let2.getRow()-1][let2.getCol()];
            }else
            {
                chars[0]=matrix[let1.getRow()][let2.getCol()];
                chars[1]=matrix[let2.getRow()][let1.getCol()];

            }
        }
        return chars;
    }
}
