public class helloword {
    public static void  main(String[] args) {
        System.out.println("HelloWord");
    for (int i = 0; i < args.length; i++){
        System.out.println(args[i]);
    }
    //another way: for all the string s in args
    for (String s:args){
        System.out.println(s);
    }
    
    }
//test

}
