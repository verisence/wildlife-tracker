import org.sql2o.*;

public class DB {
    public static Sql2o sql2otest = new Sql2o("jdbc:postgresql://localhost:5432/hair_salon", "verisence", "pass");
}
