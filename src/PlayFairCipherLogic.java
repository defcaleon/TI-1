import java.util.*;

public class PlayFairCipherLogic {

    public void encode(PlayFairCipher ob){

        String word = ob.getCipher();
        String key = ob.getKey();
        PlayFairCipher.Lang lang = ob.getLang();

        Set<Character> set=setInit(lang);
        key=ColumnarTranspositionCipherLogic.keyConvert(key);

        char[][] matrix = matrixInit(lang);

        System.out.println(key);

    }

    private Set<Character> setInit(PlayFairCipher.Lang lang){
        Set<Character> set;
        if(lang== PlayFairCipher.Lang.en){
            set = enSetInit();
        }else
        {
            set = ruSetInit();
        }
        for(Character c:set){
            System.out.print(c+" ");
        }
        return set;
    }
    private Set<Character>  enSetInit(){
        Set<Character> enSet = new HashSet<Character>();
        for(char c = 'A'; c <= 'Z'; c++) {
                enSet.add(c);

        }
        enSet.remove('J');

        return enSet;
    }
    private Set<Character>  ruSetInit(){
        Set<Character> ruSet = new LinkedHashSet<>();
        for(char c = 'А'; c <= 'Я'; c++) {
            ruSet.add(c);

        }
        ruSet.add('-');
        ruSet.add('1');
        ruSet.add('2');
        return ruSet;
    }

    private char[][] matrixInit(PlayFairCipher.Lang lang){
        final int RUMATRIXSIZE=6;
        final int ENMATRIXSIZE=5;

        char[][] matrix;
        if(lang== PlayFairCipher.Lang.ru){
            matrix= new char[RUMATRIXSIZE][RUMATRIXSIZE];
        }
        else
        {
            matrix= new char[ENMATRIXSIZE][ENMATRIXSIZE];
        }
        return  matrix;
    }

}
