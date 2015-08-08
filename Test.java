import java.util.List;

class Test {
    static VesselService vesselService = new VesselService();

    public static void main(String[] args) {
        
        printNames("456");
        printNames("140");
        printNames("999");
        
   }
   
   private static void printNames(String mmsi) {
        List<String> names = vesselService.getNames(mmsi);
        
        System.out.println("\nFound the following names for mmsi " + mmsi + ":");
        for(String name : names) {
            System.out.println(name);
        }
       
   }
}