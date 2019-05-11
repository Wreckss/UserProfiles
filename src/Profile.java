public class Profile {

    //variables of a User subclass, must be public
    public String name;
    public int id;
    public boolean single;
    private String relationshipStatus;

    //variables of a Profile
    private boolean active;
    private User createdUser;

    //create a profile for each userID, add that profile to allProfiles
    public Profile(User user, boolean active) {
        this.createdUser = user;
        this.active = active;
    }

    public Profile() {}

    public User getCreatedUser() {
        return createdUser;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public boolean isSingle() {
        return single;
    }

    public String getRelationshipStatus() {
        return relationshipStatus;
    }

    public void setRelationshipStatus(boolean single) {
        if (single) {
            relationshipStatus = "Single";
        } else  {
            relationshipStatus = "Taken";
        }
    }

} // end Profile class
