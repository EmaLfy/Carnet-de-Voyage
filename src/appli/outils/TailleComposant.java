package appli.outils;

public class TailleComposant {
    private static final TailleComposant instance = new TailleComposant();

    private TailleComposant(){
    }

    public static TailleComposant getInstance() {
        return instance;
    }

    public int windHeight(){
        return 600;
    }
    public int windWidth(){
        return 900;
    }
}
