package ac.id.ukdw.te;

public class Kalkulator {
    public int tambah (int a, int b) {
        return a + b;
        }
       public int bagi (int a, int b) {
        if (b == 0) throw new IllegalArgumentException("Division by zero");
        return a / b;
        }
       

}
