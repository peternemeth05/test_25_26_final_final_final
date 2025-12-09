package hospital;

public class MedicalNotification {
    private String bay;
    private String service;
    public MedicalNotification(String bay, String service){
        this.bay = bay;
        this.service = service;
    }
    public String getBay() {
        return bay;
    }

    public String getService() {
        return service;
    }
}