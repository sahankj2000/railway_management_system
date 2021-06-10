package sample;

public class strtest {
    public static void main(String[] args) {
        String out="";
        String in="7002   jgDSBASB";
        int i=0;
        while(in.charAt(i)!=' '){
            System.out.println(in.charAt(i));
            i++;
        }
        System.out.println("done");
    }
}
