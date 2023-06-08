public class MyTimer {

    protected static final String filePath = "C:/src.zip";
    private static long startTime;
    private static long endTime;
    protected  static void start(){
        startTime = System.currentTimeMillis();
    }
    protected static void end(String name){
        endTime = System.currentTimeMillis();
        System.out.println("[ "+name+" Time : "+(endTime-startTime)+" ] ");
    }
}
