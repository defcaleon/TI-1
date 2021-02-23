public class PlayFairCipher {

    public enum Lang{en,ru};

    private String key;
    private String cipher;
    private Lang lang;


    public PlayFairCipher(String key, String cipher,Lang lang) {
        this.key = key;
        this.cipher = cipher;
        this.lang=lang;
    }

    public Lang getLang() {
        return lang;
    }

    public void setLang(Lang lang) {
        this.lang = lang;
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
        return "PlayFairCipher{" +
                "key='" + key + '\'' +
                ", cipher='" + cipher + '\'' +
                ", lang=" + lang +
                '}';
    }
}
