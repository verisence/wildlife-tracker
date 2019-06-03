import org.sql2o.Connection;

import java.util.List;
import java.util.Objects;

public class Animal {
    public int id;
    public String name;
    public String type;
    public static final String ANIMAL_TYPE = "animal";

    public Animal(String name){
        this.name=name;
        this.type = ANIMAL_TYPE;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal)) return false;
        Animal animal = (Animal) o;
        return getId() == animal.getId() &&
                getName().equals(animal.getName()) &&
                getType().equals(animal.getType());
    }


    public void save() {
        String sql = "INSERT INTO animals (name, type) VALUES (:name, :type)";
        try(Connection con = DB.sql2o.open()) {
            this.id = (int) con.createQuery(sql, true)
                    .addParameter("name", name)
                    .addParameter("type", type)
                    .throwOnMappingFailure(false)
                    .executeUpdate()
                    .getKey();
        }
    }
    
    public static List<Animal> all(){

        try(Connection con = DB.sql2o.open()) {
            String sql = "SELECT * FROM animals";
            return con.createQuery(sql).executeAndFetch(Animal.class);
        }
    }

    public static Animal find(int id){
        try(Connection con = DB.sql2o.open()){
            String sql = "SELECT * FROM animals where id=:id AND type=:type";
            return con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("type", "animal")
                    .throwOnMappingFailure(false)
                    .executeAndFetchFirst(Animal.class);
        }
    }

    public void update(String name) {
        String sql = "UPDATE animals SET name = :name WHERE id = :id";

        try(Connection con = DB.sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("name", name)
                    .addParameter("id", id)
                    .throwOnMappingFailure(false)
                    .executeUpdate();
        }
    }

    public void delete() {
        try(Connection con = DB.sql2o.open()){
            String sql = "DELETE FROM animals WHERE id = :id;";
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        }
    }
}
