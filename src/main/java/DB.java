import org.sql2o.*;

public class DB {
//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:5432/wildlife_tracker", "verisence", "pass");

    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-23-23-228-132.compute-1.amazonaws.com:5432/dc19jg91e5b977",
            "sawjlsguknrqjn",  "13e44a1eeae20a23ceff7d8ead6f20824c3ed7f77694099ed21cf5f448ebfb8a");
}

//    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://ec2-54-225-72-238.compute-1.amazonaws.com:5432/d67gtoq0dgcau6",
//            "dsmllcgqurfjzx",  "d8d37976bb0320f15a6c1e7ae901c47cfc31dacbfe4969464c1e7066018a56ea");
