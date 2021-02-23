public class Main {

    public static void main(String[] args) {

        /*RailFenceCipher ob = new RailFenceCipher(3, "I_REALLY_LIKE_PUZZLES");
        System.out.println(ob.toString());

        RailFenceCipherLogic railFenceLogic = new RailFenceCipherLogic();
        railFenceLogic.encode(ob);

        System.out.println(ob.toString());

        railFenceLogic.decode(ob);

        System.out.println(ob.toString());*/

        /*ColumnarTranspositionCipher ob2= new ColumnarTranspositionCipher("aaabbbccc  sdsewer   ","I_REALLY_LIKE_PUZZLES");

        ColumnarTranspositionCipherLogic columnarTranspositionCipherLogic = new ColumnarTranspositionCipherLogic();

        System.out.println(ob2.toString());
        columnarTranspositionCipherLogic.encode(ob2);
        System.out.println(ob2.toString());
        columnarTranspositionCipherLogic.decode(ob2);
        System.out.println(ob2.toString());*/

        PlayFairCipher ob3= new PlayFairCipher("aaabbbccc  sdsewer   ","I_REALLY_LIKE_PUZZLES", PlayFairCipher.Lang.en);
        PlayFairCipherLogic playFairCipherLogic = new PlayFairCipherLogic();
        playFairCipherLogic.encode(ob3);
    }

}
