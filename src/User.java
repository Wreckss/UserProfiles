public class User extends Profile {
    //build users to create profiles
    public User(String name, int id, boolean single) {
        super.name = name;
        super.id = id;
        super.single = single;

        super.setRelationshipStatus(single);
    }
}
