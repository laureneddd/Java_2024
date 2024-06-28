import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/*
 * 6. Coding exercise: Availity receives enrollment files from various benefits management and enrollment solutions (I.e. HR platforms, payroll platforms). 
 * Most of these files are typically in EDI format. However, there are some files in CSV format. 
 * For the files in CSV format, write a program that will read the content of the file and separate enrollees by insurance company in its own file. 
 * Additionally, sort the contents of each file by last and first name (ascending), Lastly. 
 * If there are duplicate User lds for the same Insurance Company, then only the record with the highest version should be included. 
 * The following data points are included in the file:
 * user id (integer)
 * First Name (string)
 * last Name (string)
 * Version (integer)
 * Insurance Company (string)
 */
class Enrollee implements Comparable<Enrollee> {

    int userId;
    String firstname;
    String lastname;
    int version;
    String insuranceCom;

    public Enrollee(int userId, String firstname, String lastname, int version, String insuranceCom) {
        this.userId = userId;
        this.firstname = firstname;
        this.lastname = lastname;
        this.version = version;
        this.insuranceCom = insuranceCom;
    }

    //sort in ascending order of last name, if last name is same, then compare first name
    @Override
    public int compareTo(Enrollee enrollee) {
        if (this.lastname.equals(enrollee.lastname)) {
            return this.firstname.compareTo(enrollee.firstname);
        }
        return this.lastname.compareTo(enrollee.lastname);
    }

    @Override
    public String toString() {
        return userId + "," + firstname + "," + lastname + "," + version + "," + insuranceCom;
    }

}

class EnrollmentCheck {
    public static void main(String[] args) throws IOException {
                
        String filePath = "/Users/laureend./Desktop/Code Exercise/data.csv";
        Map<String, List<Enrollee>> map = new HashMap<>();

        try (Scanner scan = new Scanner(new File(filePath))) {
            while (scan.hasNextLine()) {
                String content = scan.nextLine().trim();
                if (content.isEmpty()) continue; 

                String[] contentSplit = content.split(",");
                if (contentSplit.length < 5) continue;

                int id = Integer.parseInt(contentSplit[0]);
                String first = contentSplit[1];
                String last = contentSplit[2];
                int version = Integer.parseInt(contentSplit[3]);
                String company = contentSplit[4];
                Enrollee enrollee = new Enrollee(id, first, last, version, company);

                // Check if the list for users from this company exists or not
                if (!map.containsKey(company)) {
                    map.put(company, new ArrayList<>());
                }

                // Check duplicates with id for insurance company, and keep highest version
                List<Enrollee> userList = map.get(company);
                boolean exist = false;
                for (int i = 0; i < userList.size(); i++) {
                    Enrollee u = userList.get(i);
                    if (id == u.userId && version > u.version) {
                        exist = true;
                        userList.set(i, enrollee);
                        break;
                    }
                }
                if (!exist) {
                    userList.add(enrollee);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        writeByCompany(map);
    }

    //output csv files with the category of insurance company
    public static void writeByCompany(Map<String, List<Enrollee>> map) throws IOException {
        for (Map.Entry<String, List<Enrollee>> entry : map.entrySet()) {
            String company = entry.getKey();
            List<Enrollee> users = entry.getValue();

            users.sort(null);
            
            FileWriter writer = new FileWriter(company + ".csv");
            writer.write("user id,First Name,last Name,Version,Insurance Company\n");
            for (Enrollee enrollee : users) {
                writer.write(enrollee.toString() + "\n");
            }   
            writer.close();
        }
        
    }
}
