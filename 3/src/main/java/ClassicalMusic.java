public class ClassicalMusic implements Music {
    private String composer;

    private ClassicalMusic(String composer) {
        this.composer = composer;
    }
    public static ClassicalMusic getClassicalMusic(String composer) {
        return new ClassicalMusic(composer);
    }

    @Override
    public String getSong() {
        return "Симфония композитора: " + composer;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }
}