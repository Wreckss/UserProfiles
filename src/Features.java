import com.google.common.primitives.Ints;
import java.util.ArrayList;
import java.util.Optional;
import java.util.Scanner;

public class Features {

    private ArrayList<Profile> allProfiles = new ArrayList<>();
    private Scanner stdIn = new Scanner(System.in);

    private void displaySpecs(Profile pro) {
        if (pro.isActive()) {
            System.out.println("Active Profile:");
            System.out.printf("\tName: %s\n", pro.getCreatedUser().getName());
            System.out.printf("\tID: %s\n", pro.getCreatedUser().getId());
            System.out.printf("\tStatus: %s\n\n", pro.getCreatedUser().getRelationshipStatus());
        } else {
            System.out.println("Inactive Profile:");
            System.out.printf("\tName: %s\n", pro.getCreatedUser().getName());
            System.out.printf("\tID: %s\n", pro.getCreatedUser().getId());
            System.out.printf("\tStatus: %s\n\n", pro.getCreatedUser().getRelationshipStatus());
        }
    }

    private void displaySpecs(int index) {
            System.out.println("Active Profile:");
            System.out.printf("\tName: %s\n", getAllProfiles().get(index).getCreatedUser().getName());
            System.out.printf("\tID: %s\n", getAllProfiles().get(index).getCreatedUser().getId());
            System.out.printf("\tStatus: %s\n\n", getAllProfiles().get(index).getCreatedUser().getRelationshipStatus());
    }

    public int makeInt(String unvalidated) {
        return Optional.of(unvalidated)
                .map(Ints::tryParse)
                .orElse(0);
    }

    private void displayAll(ArrayList allProfiles) {
        for (int i = 0; i < allProfiles.size(); i++) {
            if (getAllProfiles().get(i).isActive()) {
                System.out.println("Active profile:");
            } else {
                System.out.println("Inactive profile:");
            }
            System.out.printf("\tName: %s\n", getAllProfiles().get(i).getCreatedUser().getName());
            System.out.printf("\tID: %s\n", getAllProfiles().get(i).getCreatedUser().getId());
            System.out.printf("\tStatus: %s\n\n", getAllProfiles().get(i).getCreatedUser().getRelationshipStatus());
        }
    }

    public ArrayList<Profile> getAllProfiles() {
        return allProfiles;
    }

    private Profile createProfile() {
        boolean invalid = true;
        boolean single = true;
        String name;
        int response;
        int id;

        System.out.println("Submit your name");
        name = stdIn.next();
        while (invalid) {
            System.out.println("Provide your relationship status");
            System.out.println("\t1 - Single");
            System.out.println("\t2 - Taken");
            response = makeInt(stdIn.next());
            switch (response) {
                case 1:
                    invalid = false;
                    single = true;
                    break;
                case 2:
                    invalid = false;
                    single = false;
                    break;
                default:
                    System.out.println("Invalid response");
            }
        }
        id = getAllProfiles().size() + 1;
        return new Profile(new User(name, id, single), true);
    }

    private int askWho(ArrayList profiles) {
        boolean profileFound = false;
        int id;


        displayAll(profiles);       //should I pass in the array to display as profiles? or should i just use getAllProfiles()?
        System.out.println("Provide an ID: ");
        //System.out.print("ID to remove: ");
        return makeInt(stdIn.next());

//        for (int i = 0; i < profiles.size(); i++) {
//            if (getAllProfiles().get(i).getCreatedUser().getId() == id) {
//                System.out.println("Removing profile:");
//                displaySpecs(i);
//                getAllProfiles().remove(i);
//                profileFound = true;
//                break;
//            } else {
//                System.out.println("searching..");
//            }
//        }
//        if (!profileFound) {
//            System.out.printf("ID: %s does not exist\n\n", id);
//        }
    }

    private void removeProfile(ArrayList profiles, int index) {
        boolean profileFound = false;

//        System.out.println("Submit the ID of the profile you'd like to remove");
//        //askwho
//        //displayAll(profiles);       //should I pass in the array to display as profiles? or should i just use getAllProfiles()?
//        System.out.print("ID to remove: ");
//        id = makeInt(stdIn.next());

        for (int i = 0; i < profiles.size(); i++) {
            if (getAllProfiles().get(i).getCreatedUser().getId() ==
                    getAllProfiles().get(index).getCreatedUser().getId()) {
                System.out.println("Removing profile:");
                displaySpecs(index);
                getAllProfiles().remove(index);
                profileFound = true;
                break;
            } else {
                System.out.println("searching..");
            }
        }
        if (!profileFound) {
            System.out.printf("ID: %s does not exist\n\n", index);
        }
    }

    private void makeInactive(ArrayList profiles, int index) {
        //displayAll(getAllProfiles());
        int answer = askWho(getAllProfiles());
        System.out.println("Select the ID to make inactive: ");
        //int answer = makeInt(stdIn.next());

        for (int i = 0; i < profiles.size(); i++) {
            if (getAllProfiles().get(i).getCreatedUser().getId() == answer)
            {
                if (getAllProfiles().get(i).isActive()) {
                    getAllProfiles().get(index).setActive(false);
                    System.out.printf("ID: %s deactivated\n", getAllProfiles().get(i).getCreatedUser().getId());
                } else if (!getAllProfiles().get(i).isActive()){
                    System.out.printf("ID: %s already inactive\n", getAllProfiles().get(i).getCreatedUser().getId());
                }
            }

        }

    }

    public void mainMenu() {
        boolean quit = false;
        String[] menuOptions = new String[5];
        menuOptions[0] = "create a new profile";
        menuOptions[1] = "make your profile inactive";
        menuOptions[2] = "remove a profile";
        menuOptions[3] = "view all profiles";
        menuOptions[4] = "quit";

        while (!quit) {
            System.out.println("Welcome to the Facebook killer");
            for (int i = 0; i < menuOptions.length; i++) {
                System.out.printf("\t%s - %s\n", i+1, menuOptions[i]);
            }
            switch (makeInt(stdIn.next())) {
                case 1:
                    getAllProfiles().add(createProfile());
                    System.out.println("Profile added!");
                    displaySpecs(getAllProfiles().size()-1);
                    break;
                case 2:
                    makeInactive(getAllProfiles(), askWho(getAllProfiles()));
                    //makeInactive(getAllProfiles());
                    break;
                case 3:
                    removeProfile(getAllProfiles(), askWho(getAllProfiles()));
                    //askWho(getAllProfiles());
                    break;
                case 4:
                    displayAll(getAllProfiles());
                    break;
                case 5:
                    quit = true;
                    System.out.println("Goodbye");
                    break;
                default:
                    System.out.println("yo wtf");
            }
        }
    } // end mainMenu

} // end Features class
