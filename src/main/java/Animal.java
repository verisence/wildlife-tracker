public class Animal {
    private int id;
    private String name;
    private String type;
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
}
