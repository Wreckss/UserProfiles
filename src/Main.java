public class Main {
    public static void main(String[] args) {

        User alphaUser = new User("Valar", 5435435, true);
        User betaUser = new User("fng", 0, true);

        Features features = new Features();

        Profile alphaProfile = new Profile(alphaUser, true);
        Profile betaProfile = new Profile(betaUser, false);

        features.getAllProfiles().add(alphaProfile);
        features.getAllProfiles().add(betaProfile);


        features.mainMenu();
    } // end main method

} // end Main class
