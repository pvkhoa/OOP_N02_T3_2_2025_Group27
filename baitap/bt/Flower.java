package bt;

public class Flower{
    int petalCount = 0;
    String s = new String("null");
    Flower(int petals) { petalCount = petals; }
    Flower(String ss){s=ss;}
    Flower(String s, int petal){
        this(petal);
        this.s=s;
    }
    Flower() { this("hi",47);}
}