package hospital;

import services.Services;

import java.security.Provider;
import java.util.ArrayList;

public class Bay {

    private String bayName;
    private ArrayList<Services> bayService;
    private boolean serviceActive;

    public Bay(String bayName, ArrayList<Services> bayService) {
        this.bayName = bayName;
        this.bayService = bayService;
    }


    public String getBayName() {
        return bayName;
    }

    public String getBayServiceLabel() {
        String line = "";
        for (Services services : bayService) {
            line+="<br>";
            line += services.getName() +" ";
            line += services.isActive()+ " ";
            if(services.isActive()){
                line+=" - " + services.getCurDuration() + " / " + services.getDuration()+ " hrs";
            }

        }
        return line;
    }

    public ArrayList<Services> getBayServices() {
        return bayService;
    }

    public void removeBayService(String serviceToRemove) {
        int indexToRemove = -1;
        // find the index of the service
        for (Services services : bayService) {
            if (services.getName().equals(serviceToRemove)) {
                indexToRemove = bayService.indexOf(services);

                System.out.println("Remove");
            }
        }
        if(indexToRemove>-1){
            bayService.remove(indexToRemove);
        }


        this.bayService = bayService;

    }






}
