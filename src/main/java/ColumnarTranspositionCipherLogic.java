import java.util.*;

public class ColumnarTranspositionCipherLogic {

    public void encode(ColumnarTranspositionCipher ob) {

        String word = ob.getCipher();
        String key =ob.getKey();

        key=keyConvert(key);
        int[] order = keyOrder(key);

        char[][] matrix = matrixEncodeInit(key,word);

        String newWord = encodeWord(matrix,order);
        ob.setCipher(newWord);

    }

    public static String keyConvert(String key){

        StringBuilder result = new StringBuilder();

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (Character c : key.toCharArray()){
            map.putIfAbsent(c, map.size());
        }

        Set<Character> values = map.keySet();
        for(Object c : values.toArray()){
           result.append(c);
        }
        return result.toString();
    }

    private  int[] keyOrder(String key){

        char[] word= key.toCharArray();
        int[] order = new int[key.length()];
        boolean[] orderUse= new boolean[key.length()];

        for(int i=0;i<key.length();i++){

            int minIndex=firstFreeCharIndex(word,orderUse);
            char minChar= word[minIndex];
                for(int j=0;j<key.length();j++){
                    if(!orderUse[j]){
                        if(minChar>word[j]){
                            minChar=word[j];
                            minIndex=j;
                        }
                    }
                }
            orderUse[minIndex]=true;
            order[minIndex]=i;



        }

        return order;
    }

    private int firstFreeCharIndex(char[] word, boolean[] orderUse){

        for (int i=0;i< word.length;i++){
            if(!orderUse[i]){
                return  i;
            }
        }
        return -1;
    }

    private char[][] matrixEncodeInit(String key,String word){

        int numOfRows = word.length()/key.length()+1;
        char[][] matrix = new char[numOfRows][key.length()];

        int row=0;
        int column=0;
        for(char c: word.toCharArray()){

            matrix[row][column++]=c;

            if(column==key.length()){
                column=0;
                row++;
            }
        }

        return matrix;
    }

    private  String encodeWord(char[][] matrix, int[] order){
        StringBuilder result = new StringBuilder();

        for(int i=0;i<order.length;i++){

            int currIndex=currColIndex(order,i);

            for(int j=0;j< matrix.length;j++){
                if(matrix[j][currIndex]!=0)
                {
                    result.append(matrix[j][currIndex]);
                }

            }
        }

        return  result.toString();
    }

    private int currColIndex(int[] orderArr, int currIndex){

        for(int i=0;i<orderArr.length;i++){
            if(orderArr[i]==currIndex){
                return i;
            }
        }
        return -1;
    }

    public void decode(ColumnarTranspositionCipher ob){

        String word = ob.getCipher();
        String key =ob.getKey();

        key=keyConvert(key);

        int[] order = keyOrder(key);
        char[][] matrix = matrixDecodeInit(key,word,order);

        String newWord=decodeWord(matrix);
        ob.setCipher(newWord);


    }

    private char[][] matrixDecodeInit(String key,String word, int[] order)
    {
        int fullRows= word.length()/key.length();
        int remainder = word.length()-fullRows*key.length();

        int numOfRows=fullRows;
        if(remainder!=0){
            numOfRows++;
        }

        char[][] matrix = new char[numOfRows][key.length()];

        int wordIndex=0;
        for (int i=0;i<key.length();i++){

            int currIndex=currColIndex(order,i);

            int numOfLetInCol=fullRows;
            if(currIndex<remainder){
                numOfLetInCol++;
            }

            for(int j=0;j<numOfLetInCol;j++){
                matrix[j][currIndex]=word.charAt(wordIndex++);
            }


        }

        return  matrix;
    }

    private String decodeWord(char[][] matrix){
        StringBuilder res = new StringBuilder();

        for(char[] rows:matrix){
            for (char c: rows){
                if(c!=0){
                    res.append(c);
                }
            }
        }

        return  res.toString();
    }
}
