package hospital;

public class HospitalInformation {
    public static String[] bays={"Nightingale", "Barton", "Seacole", "Dix", "Henderson", "Cavell", "Sanger","Breckinridge"};
    public static String[] services={"Ultrasound","ECG","Dialysis","Massage"};
    private static int millisPerHour=3000;

    private static long startTime=-1;
    public static MedicalNotification getMedicalNotificationFromDoctor(){
        int NFAR= (int)(Math.random() * 4);
        int bay= (int)(Math.random() * 8);
        int service =(int)(Math.random() * 4);

        if (NFAR!=1) {
            MedicalNotification n=new MedicalNotification("None","None");
            return n;
        }
        else {
            MedicalNotification n=new MedicalNotification(bays[bay],services[service]);
            System.out.println("Notification issued:"+bays[bay]+":"+services[service]);
            return n;
        }
    }
    public static int getHourCount(){
        if (startTime==-1){
            startTime = System.currentTimeMillis();
        }
        return (int)((System.currentTimeMillis()-startTime)/millisPerHour);
    }
    public static void waitAnHour(){
        try {Thread.sleep(millisPerHour);}
        catch(Exception e){}
    }
    public static void requestPorter(String bay, String service){
        System.out.println("Porter request: take "+service+" to "+bay);
    }

}