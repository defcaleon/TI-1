import java.util.*;

public class ColumnarTranspositionCipherLogic {

    public void encode(ColumnarTranspositionCipher ob) {

        String word = ob.getCipher();
        String key =ob.getKey();
        System.out.println(key);

        keyConvert(key);
    }

    private void keyConvert(String key){

        StringBuilder result = new StringBuilder();

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (Character c : key.toCharArray()){
            map.putIfAbsent(c, map.size());
        }

        Set<Character> values = map.keySet();
        for(Object c : values.toArray()){
            System.out.print(c);
        }
        System.out.println("|");
    }
}
