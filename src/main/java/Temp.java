import static spark.Spark.*;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import java.util.HashMap;
import java.util.Map;


public class Temp {
    public static void main(String[] args) {
        staticFileLocation("/public");
        String layout = "templates/layout.vtl";
        
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
