public class User {
    public String email, password;
    public boolean uservegan, userglutenfree, uservegetarian, userdairyfree, usernutfree;

    public User() {
    }

    public User(String email, String password, boolean uservegan, boolean userglutenfree, boolean uservegetarian, boolean userdairyfree, boolean usernutfree) {
        this.email = email;
        this.password = password;
        this.uservegan = uservegan;
        this.userglutenfree = userglutenfree;
        this.uservegetarian = uservegetarian;
        this.userdairyfree = userdairyfree;
        this.usernutfree = usernutfree;
    }
}
