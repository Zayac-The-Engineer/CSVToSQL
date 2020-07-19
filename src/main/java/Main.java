import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String...args) throws IOException {
        UserService us = new UserService();

        BufferedReader br = new BufferedReader(new FileReader("residents.csv"));
        try {
            String line = br.readLine();

            List<User> users = new ArrayList<User>();

            while (line != null) {
                String[] sa = line.split(",");
                if(Integer.parseInt(sa[1])>=0 && validEmail(sa[2])){
                    users.add(new User(sa[0], Integer.parseInt(sa[1]), sa[2]));
                }

                line = br.readLine();
            }
            users.stream().sorted(Comparator.comparing(User::getName));
            users.forEach(us::saveUser);

        } finally {
            br.close();
        }
    }
    public static boolean validEmail(String mail) {
        return mail.matches("(.*)@(.*).[a-z]");
    }
}
