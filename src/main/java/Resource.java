
import utility.BadWords;
import utility.JsonTransformer;

import static spark.Spark.get;
import static spark.Spark.post;

public class Resource {

    private DAO dao;
    private Stats stats = new Stats();
    private BadWords badWords = new BadWords();

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
                -> (dao.update(request.params(":id"))), new JsonTransformer());

        get("/autoGenerate/:id", "applications/json", (request, response)
                -> (dao.autoGenerate(request.params(":id"))), new JsonTransformer());

        get("/searchUrl/:id", "applications/json", (request, response)
                -> (dao.find(request.params(":id"))),new JsonTransformer());

        get("/statsUrl/:id", "applications/json", (request, response)
                -> (stats.statsShortUrl(request.params(":id"))), new JsonTransformer());

        get("/statsGlobal/getUrls/:cont", "applications/json", (request, response)
                -> (stats.getShortUrlsMostClicked(request.params(":cont"))), new JsonTransformer());

        get("/statsGlobal/getCounts/:cont",  "applications/json", (request, response)
                -> (stats.getCountsMostClicked(request.params(":cont"))), new JsonTransformer());

        get("/checkBadWords/:string",  "applications/json", (request, response)
                -> (badWords.checkString(request.params(":string"))), new JsonTransformer());

    }

}
