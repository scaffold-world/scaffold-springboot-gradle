public class Main {
    public static void main(String[] args) {
        parent p = new son("");
        p.say();
        System.out.println(p.name);
        System.out.println(((son) p).name);
    }
}
