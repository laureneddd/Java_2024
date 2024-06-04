import java.util.Scanner;

public class UserService {
    private Scanner scanner;
    private UserDAO userDao;
    private String userLogName;
    private String visitorLog;
    private User[] users = new User[5];

    public UserService(){
        scanner = new Scanner(System.in);
        userDao = new UserDAO();
    }

    public User[] visitorLog(User[] users){
        System.out.println("\n<Log In> Enter Visitor Name: ");
        String visitorName = scanner.nextLine();
        visitorLog = visitorName;
        userDao.roleV(visitorName);
        System.out.println("You have been successfully log in as a Visitor.");
        return users;
    }

    public User[] clientLog(User[] users){
        boolean login = false;
        
        while (!login) { 
            System.out.println("\n<Log In> Enter Username: ");
            String username = scanner.nextLine();
            userLogName = username;
            System.out.println("\n<Log In> Enter Password: ");
            String password = scanner.nextLine();
            if(userDao.search(username, password)) {
                userDao.roleC(username);
                login = true;
                System.out.println("You have been successfully log in as a Client.");
            }else {
                System.out.println("You failed to log in.");
            }
        }

        return users;
    }

    public void logOut(){
        userDao.roleV(userLogName);
        userLogName = null;
        System.out.println("You have been successfully logged out.");
    }

    public User[] add(){
        System.out.println("\n<Add User> Enter First Name: ");
        String first = scanner.nextLine();
        System.out.println("\n<Add User> Enter Last Name: ");
        String last = scanner.nextLine();
        System.out.println("\n<Add User> Enter Username: ");
        String username = scanner.nextLine();
        System.out.println("\n<Add User> Enter Password: ");
        String password = scanner.nextLine();
        userDao.add(first, last, username, password);
        return users;
    }

    public void delete(){
        System.out.println("\n<Register> Enter the username to delete this account: ");
        String userDel = scanner.nextLine();
        userDao.delete(userDel);
        userLogName = null;
        System.out.println("\nThis account has been deleted.");
    }

    public User[] register(User[] users){
        
        System.out.println("\nPlease register first. <Register> Enter First Name: ");
        String firstnm = scanner.nextLine();
        System.out.println("\n<Register> Enter Last Name: ");
        String lastnm = scanner.nextLine();
        System.out.println("\n<Register> Enter User Name: ");
        String usernm = scanner.nextLine();
        System.out.println("\n<Register> Enter Password: ");
        String pass = scanner.nextLine();

        this.users = userDao.add(firstnm, lastnm, usernm, pass);
        System.out.println("\n" + firstnm + ", you have been successfully registered.");
        return this.users;
    }

    public String checkAssign(){
        return visitorLog;
    }

    public void updateUserName(){
        System.out.println("\nEnter your new username: ");
        String newname = scanner.nextLine(); 
        userDao.update(userLogName, newname);
        System.out.println("\nYour username has been changed.");
    }

    public String visitorName(){
        return this.visitorLog;
    }

    public String clientName(){
        return this.userLogName;
    }
}
