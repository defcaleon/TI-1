public class Main {

    public static void main(String[] args){

        RailFenceCipher ob = new RailFenceCipher(3,"I_REALLY_LIKE_PUZZLES");
        System.out.println(ob.toString());

        RailFenceCipherLogic railFenceLogic = new RailFenceCipherLogic();
        railFenceLogic.encode(ob);

        System.out.println(ob.toString());

        railFenceLogic.decode(ob);

        System.out.println(ob.toString());
    }

}
