package hospital;

import services.Services;

import java.util.ArrayList;

public class SystemLogger {

    private static final int count = 24;

    public void LogToSystem(ArrayList<Bay> bayList, int hours){


        if(count == hours){

            for(Bay bay : bayList){

                for(Services services :  bay.getBayServices()){

                    if(bay.getBayServices().size()>0){
                        System.out.println("Service: " + services.getName());
                        System.out.println("Bay " + bay.getBayName());
                        System.out.println("Active: " + services.isActive());
                    }
                }
            }

            System.out.println();
            System.out.println();


        }

    }

}
