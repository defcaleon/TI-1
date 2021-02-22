package com.site.laba1;

public class LogicRailFenceCipher {

    public void encode(RailFenceCipher ob){

        String word =ob.getCipher();
        int height =ob.getHeight();

        int rowLen=word.length()/height+1;

        char[][] matrix = new char[height][rowLen];

    }

    private void matrixInit(char[][] matrix, String word) {


    }


}
