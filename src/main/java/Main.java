public class Main {

    public static void main(String[] args) {

        /*RailFenceCipher ob = new RailFenceCipher(3, "I_REALLY_LIKE_PUZZLES");
        System.out.println(ob.toString());

        RailFenceCipherLogic railFenceLogic = new RailFenceCipherLogic();
        railFenceLogic.encode(ob);

        System.out.println(ob.toString());

        railFenceLogic.decode(ob);

        System.out.println(ob.toString());*/

        /*ColumnarTranspositionCipher ob2= new ColumnarTranspositionCipher("aaabbbccc23ывы  sdsewer   ","I_REALLY_LIKE_PUZZLES");

        ColumnarTranspositionCipherLogic columnarTranspositionCipherLogic = new ColumnarTranspositionCipherLogic();

        System.out.println(ob2.toString());
        columnarTranspositionCipherLogic.encode(ob2);
        System.out.println(ob2.toString());
        columnarTranspositionCipherLogic.decode(ob2);
        System.out.println(ob2.toString());*/

       /* PlayFairCipher ob3= new PlayFairCipher("playfair23ваывы example","Hide the gold in the tree stump", PlayFairCipher.Lang.en);
        PlayFairCipherLogic playFairCipherLogic = new PlayFairCipherLogic();

        System.out.println(ob3.toString());
        playFairCipherLogic.encode(ob3);
        System.out.println(ob3.toString());
        playFairCipherLogic.decode(ob3);
        System.out.println(ob3.toString());*/

        boolean[][] a = {{false,true,false,true,false,false},{false,false,true,false,false,false},{false,true,false,false,false,true}
                ,{false,false,true,false,false,false},{false,true,false,false,false,true},{true,false,false,false,false,false}};

        RotaryCipher ob= new RotaryCipher(a,"I_REALLY_LIKE_PUZZLESasdasdasdwesaeaseqweasdasdasdasd1111111");
       RotaryCIpherLogic logic = new RotaryCIpherLogic();
        logic.encode(ob);
        View view = new View();
        view.showMatrix(ob.getCipherMatrix());
        logic.decode(ob);
        System.out.println(ob.getWord());

    }

}
