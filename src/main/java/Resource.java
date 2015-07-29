
import utility.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;

public class Resource {

    private DAO dao;
    private Stats stats = new Stats();

    public Resource(DAO dao){
        this.dao = dao;
        setupEndPoints();
    }

    private void setupEndPoints() {
        post("/addShortUrl", "application/json", (request, response) -> {
            dao.addShortUrl(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        post("/visitUrl/:id", "applications/json", (request, response)
                ->(dao.update(request.params(":id"))), new JsonTransformer());

        get("/autoGenerate/:id", "applications/json", (request, response)
                -> (dao.autoGenerate(request.params(":id"))), new JsonTransformer());

        get("/searchUrl/:id", "applications/json", (request, response)
                -> (dao.find(request.params(":id"))),new JsonTransformer());

        get("/statsGlobal/getUrls/:cont", "applications/json", (request, response)
                -> (stats.getShortUrlMostClicked(request.params(":cont"))), new JsonTransformer());

        get("/statsGlobal/getCounts/:cont",  "applications/json", (request, response)
                -> (stats.getCountsMostClicked(request.params(":cont"))), new JsonTransformer());

    }

}
