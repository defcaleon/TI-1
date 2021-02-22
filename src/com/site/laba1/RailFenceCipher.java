package com.site.laba1;

public class RailFenceCipher {

    private int height;
    private String cipher;

    public RailFenceCipher(int height, String cipher) {
        this.height = height;
        this.cipher = cipher;
    }

    public int getHeight() {
        return height;
    }

    public String getCipher() {
        return cipher;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    @Override
    public String toString() {
        return "RailFenceCipher{" +
                "height=" + height +
                ", cipher='" + cipher + '\'' +
                '}';
    }
}
