public class View {


    public void showMatrix(char[][] matrix) {
        for (char[] chars : matrix) {
            for (char aChar : chars) {
                System.out.printf("%3c", aChar);
            }
            System.out.println();
        }

    }

}
