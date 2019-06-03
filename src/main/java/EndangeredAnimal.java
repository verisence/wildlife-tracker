import java.util.Objects;

public class EndangeredAnimal extends Animal {
    private String health;
    private String age;

    public static final String ANIMAL_TYPE = "endangered";
    public static final String ADULT = "adult";
    public static final String YOUNG = "youthful";
    public static final String NEWBORN = "newborn";
    public static final String HEALTHY = "very healthy";
    public static final String AVERAGE = "averagely unhealthy";
    public static final String ILL = "very unhealthy";

    public EndangeredAnimal(String name, String health, String age) {
        super(name);
        this.type = ANIMAL_TYPE;
        this.health = health;
        this.age = age;
    }
    public String getHealth() {
        return health;
    }

    public String getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EndangeredAnimal)) return false;
        if (!super.equals(o)) return false;
        EndangeredAnimal that = (EndangeredAnimal) o;
        return getHealth().equals(that.getHealth()) &&
                getAge().equals(that.getAge());
    }
}
