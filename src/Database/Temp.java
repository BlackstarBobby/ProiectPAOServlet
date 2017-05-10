package Database;

import java.sql.SQLException;

/**
 * Created by bobby on 09-05-2017.
 */
public class Temp {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Database db = new Database();

        System.out.println(db.serialExists("ds2p5qh916ikvubm87gn0jns4b"));
    }

}
