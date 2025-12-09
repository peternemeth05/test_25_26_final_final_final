import hospital.Bay;
import hospital.HospitalInformation;
import hospital.MedicalNotification;
import hospital.SystemLogger;
import services.*;
import ui.BayDisplay;

import javax.swing.*;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        MedicalNotification medicalNotification = new MedicalNotification("Barton", "ECG");
        System.out.print(medicalNotification.getBay());

        Ultrasound ultrasound = new Ultrasound();
        ECG ecg = new ECG();
        Dialysis dialysis = new Dialysis();
        Masseur massage = new Masseur();

        ArrayList<Services> nightingaleServices = new ArrayList<>();
        nightingaleServices.add(ultrasound);
        Bay nightingale = new Bay("Nightingale", nightingaleServices);

        ArrayList<Services> bartonServices = new ArrayList<>();
        Bay barton = new Bay("Barton", bartonServices);

        ArrayList<Services> seacoleServices = new ArrayList<>();
        seacoleServices.add(ecg);
        seacoleServices.add(massage);
        Bay seacole = new Bay("Seacole", seacoleServices);

        ArrayList<Services> dixServices = new ArrayList<>();
        Bay dix = new Bay("Dix", dixServices);

        ArrayList<Services> hendersonServices = new ArrayList<>();
        hendersonServices.add(dialysis);
        Bay henderson = new Bay("Henderson", hendersonServices);

        ArrayList<Services> cavellServices = new ArrayList<>();
        Bay cavell = new Bay("Cavell", cavellServices);

        ArrayList<Services> breckinridgeServices = new ArrayList<>();
        Bay breckinridge = new Bay("Breckinridge", breckinridgeServices);

        ArrayList<Services> sangerServices = new ArrayList<>();
        Bay sanger = new Bay("Sanger", sangerServices);


        ArrayList<Bay> bayList = new ArrayList<>();
        bayList.add(nightingale);
        bayList.add(barton);
        bayList.add(seacole);
        bayList.add(dix);
        bayList.add(henderson);
        bayList.add(cavell);
        bayList.add(breckinridge);
        bayList.add(sanger);

        BayDisplay bayDisplay = new BayDisplay(bayList);

        // --- SETTING UP FRAME ---
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,800);
        frame.setTitle("ADD TITLE");

        frame.add(bayDisplay);

        frame.setVisible(true); // PUT AT THE END OF FRAME CODE

        HospitalInformation hi = new HospitalInformation();
        SystemLogger systemLogger = new SystemLogger();
        ArrayList<MedicalNotification> allNotification = new ArrayList<>();

        // MAIN LOGIC
        while (true) {

            hi.waitAnHour();

            int hourCount = hi.getHourCount();
            System.out.println(hourCount);

            allNotification.add(hi.getMedicalNotificationFromDoctor());

            for (MedicalNotification notification : allNotification) {
                String BayToAddSerivceTo = notification.getBay();
                String ServiceToAdd = notification.getService();

                // GET ARRAY LIST OF ALL SERVICES
                ArrayList<Services> allServices = new ArrayList<>();
                for (Bay bay : bayList) {
                    for (Services service : bay.getBayServices()) {
                        allServices.add(service);
                    }
                }

                // INCREASE COUNT OF ALL ACTIVE SERVICES
                for (Services service : allServices) {
                    service.increaseCurDuration();

                    if (service.getDuration() == service.getCurDuration() ) {
                        service.setActive(false);
                    }
                }


                Services ServiceObjectToAdd = null;

                for (Services service : allServices) {
                    if (service.getName().equals(ServiceToAdd)) {
                        ServiceObjectToAdd = service;
                    }
                }

                // ONLY WORKS IF THERE IS A NEED FOR IT
                if (BayToAddSerivceTo.equals("None") == false) {
                    System.out.println(BayToAddSerivceTo);

                    Bay baytoremovefrom = null;

                    // REMOVING SERVICE FROM CURRENT BAY
                    for (Bay bay : bayList) {
                        for (Services service : bay.getBayServices()) {
                            // checking which current bay has the service
                            if (service.getName().equals(ServiceToAdd)) {
                                // need to make sure not active before removing
                                if(service.isActive() == false ) {
                                    baytoremovefrom = bay;
                                    hi.requestPorter(BayToAddSerivceTo, ServiceToAdd);
                                    continue;
                                }
                            }
                        }
                    }

                    if (baytoremovefrom != null) {
                        baytoremovefrom.removeBayService(ServiceToAdd);
                    }


                    // ADDING SERVICE TO NEW BAY
                    for (Bay bay : bayList) {
                        if(bay.getBayName().equals(BayToAddSerivceTo) && !(ServiceObjectToAdd == null)) {
                            bay.getBayServices().add(ServiceObjectToAdd);
                            ServiceObjectToAdd.setActive(true);
                        }
                    }

                    // REMOVE NOTIFIACTION IS ADDRESSED
                    allNotification.remove(notification);

                }
            }



            // NOW ALL THE BAYS SHOULD HAVE THE CORRECT SERVICES ASSIGNED
            // NEED TO REFRESH DISPLAY
            bayDisplay.refreshDisplay();


            // will log to system every 24 hours
            systemLogger.LogToSystem(bayList, hourCount);

            for (Bay bay : bayList) {
                System.out.println(bay.getBayName()+ bay.getBayServices().size());
            }








        }

    }
}