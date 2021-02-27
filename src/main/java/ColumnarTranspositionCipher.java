public class ColumnarTranspositionCipher {

    private String key;
    private String cipher;

    public ColumnarTranspositionCipher(String key, String cipher) {
        this.key = key;
        this.cipher = cipher;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getCipher() {
        return cipher;
    }

    public void setCipher(String cipher) {
        this.cipher = cipher;
    }

    @Override
    public String toString() {
        return "ColumnarTranspositionCipher{" +
                "key='" + key + '\'' +
                ", cipher='" + cipher + '\'' +
                '}';
    }
}
