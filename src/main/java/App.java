import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;

public class App{
    static int getHerokuAssignedPort() {
        ProcessBuilder processBuilder = new ProcessBuilder();
        if (processBuilder.environment().get("PORT") != null) {
            return Integer.parseInt(processBuilder.environment().get("PORT"));
        }
        return 4567; //return default port if heroku-port isn't set (i.e. on localhost)
    }
    public static void main(String[] args) {
        port(getHerokuAssignedPort());
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/animals/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.ANIMAL_TYPE);
            model.put("template", "templates/animal-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");

            Animal animal = new Animal(name);
            animal.save();
            model.put("animals", Animal.all());
            model.put("template", "templates/animals.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/animals", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("template", "templates/animals.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/animals/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Animal animal = Animal.find(Integer.parseInt(request.params(":id")));
            animal.delete();
            model.put("animals", Animal.all());
            model.put("template", "templates/animals.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/animals/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Animal animal = Animal.find(Integer.parseInt(request.params(":id")));
            model.put("animal", animal);
            model.put("animals", Animal.all());
            model.put("template", "templates/edit-animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/animals/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Animal animal = Animal.find(Integer.parseInt(request.params(":id")));
            String name = request.queryParams("name");
            animal.update(name);
            model.put("animals", Animal.all());
            model.put("template", "templates/animals.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("endangered/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.ANIMAL_TYPE);
            model.put("template", "templates/endangered-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            String age = request.queryParams("age");
            String health = request.queryParams("health");
            EndangeredAnimal eAnimal = new EndangeredAnimal(name,age,health);
            eAnimal.save();
            model.put("endangered", EndangeredAnimal.allEndangered());
            model.put("template", "templates/endangered.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/endangered", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("endangered", EndangeredAnimal.allEndangered());
            model.put("template", "templates/endangered.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/endangered/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            EndangeredAnimal animal = (EndangeredAnimal) EndangeredAnimal.findEndangered(Integer.parseInt(request.params(":id")));
            animal.delete();
            model.put("animals", EndangeredAnimal.allEndangered());
            model.put("template", "templates/animals.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/endangered/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            EndangeredAnimal animal = (EndangeredAnimal) EndangeredAnimal.find(Integer.parseInt(request.params(":id")));
            model.put("animal", animal);
            model.put("endangered", EndangeredAnimal.allEndangered());
            model.put("template", "templates/edit-endangered.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("rangers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/ranger-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            int badge = Integer.parseInt(request.queryParams("badge"));

            Ranger ranger = new Ranger(name,badge);
            ranger.save();
            model.put("rangers", Ranger.all());
            model.put("template", "templates/rangers.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/rangers", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("rangers", Ranger.all());
            model.put("template", "templates/rangers.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/rangers/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
            ranger.delete();
            model.put("rangers", Ranger.all());
            model.put("template", "templates/rangers.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/rangers/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
            model.put("ranger", ranger);
            model.put("rangers", Ranger.all());
            model.put("template", "templates/edit-ranger.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/rangers/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Ranger ranger = Ranger.find(Integer.parseInt(request.params(":id")));
            String name = request.queryParams("name");
            int badge = Integer.parseInt(request.queryParams("badge"));
            ranger.update(name,badge);
            model.put("rangers", Ranger.all());
            model.put("template", "templates/rangers.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("locations/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/location-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            String name = request.queryParams("name");
            Location location = new Location(name);
            location.save();
            model.put("locations", Location.all());
            model.put("template", "templates/locations.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/locations", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("locations", Location.all());
            model.put("template", "templates/locations.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/locations/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Location location = Location.find(Integer.parseInt(request.params(":id")));
            location.delete();
            model.put("locations", Location.all());
            model.put("template", "templates/locations.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/locations/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Location location = Location.find(Integer.parseInt(request.params(":id")));
            model.put("location", location);
            model.put("locations", Location.all());
            model.put("template", "templates/edit-location.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/locations/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Location location = Location.find(Integer.parseInt(request.params(":id")));
            String name = request.queryParams("name");
            location.update(name);
            model.put("locations", Location.all());
            model.put("template", "templates/locations.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("sightings/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("animals", Animal.all());
            model.put("locations", Location.all());
            model.put("endangereds", EndangeredAnimal.all());
            model.put("rangers", Ranger.all());
            model.put("template", "templates/sighting-form.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            int animalId = Integer.parseInt(request.queryParams("animalId"));
            int rangerId = Integer.parseInt(request.queryParams("rangerId"));
            int locationId = Integer.parseInt(request.queryParams("locationId"));
            String type = request.queryParams("type");
            Sighting sighting = new Sighting(animalId,rangerId,locationId,type);
            sighting.save();
            model.put("sightings", Sighting.all());
            model.put("template", "templates/sightings.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/sightings", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("sightings", Sighting.all());
            model.put("template", "templates/sightings.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/sightings/:id/delete", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
            sighting.delete();
            model.put("sightings", Sighting.all());
            model.put("template", "templates/sightings.vtl");
            return new ModelAndView(model,layout);
        }, new VelocityTemplateEngine());

        get("/sightings/:id/edit", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
            model.put("sighting", sighting);
            model.put("sightings", Sighting.all());
            model.put("template", "templates/edit-sighting.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

//        post("/sightings/:id/edit", (request, response) -> {
//            Map<String, Object> model = new HashMap<String, Object>();
//            Sighting sighting = Sighting.find(Integer.parseInt(request.params(":id")));
//            int animalId = Integer.parseInt(request.queryParams("badge"));
//            int rangerId = Integer.parseInt(request.queryParams("badge"));
//            int locationId = Integer.parseInt(request.queryParams("badge"));
//            String type = request.queryParams("type");
//            sighting.update(animalId,rangerId,locationId,type);
//            model.put("sightings", Sighting.all());
//            model.put("template", "templates/sightings.vtl");
//            return new ModelAndView(model, layout);
//        }, new VelocityTemplateEngine());

    }
    
}
