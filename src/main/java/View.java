public class View {


    public void showMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                System.out.printf("%3c", aChar);
            }
            System.out.println();
        }

    }

    public void showMatrix(boolean[][] matrix) {
        for (boolean[] chars : matrix) {
            for (boolean aChar : chars) {
                if(aChar){
                    System.out.print("1 ");
                }else
                {
                    System.out.print("0 ");
                }

            }
            System.out.println();
        }
        System.out.println();
    }
    public void showMatrix(int[][] matrix) {
        for (int[] chars : matrix) {
            for (int aChar : chars) {
                System.out.printf("%2d", aChar);
            }
            System.out.println();
        }

    }

}
