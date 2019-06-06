import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;

public class App{
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

        get("/", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("template", "templates/index.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("animals/new", (request, response) -> {
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
            model.put("template", "templates/edit-animal.vtl");
            return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("rangers/new", (request, response) -> {
            Map<String, Object> model = new HashMap<String, Object>();
            model.put("rangers", Ranger.all());
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
            model.put("template", "templates/edit-ranger-form.vtl");
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

    }
    
}
