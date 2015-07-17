
import static spark.Spark.get;
import static spark.Spark.post;

public class Resource {

    private DAO dao;

    public Resource(DAO dao){
        setupEndPoints();
    }

    private void setupEndPoints() {
        post("/addShortUrl", "application/json", (request, response) -> {
            dao.addShortUrl(request.body());
            response.status(201);
            return response;
        }, new JsonTransformer());

        get("/showLongUrl:shortUrl", "applications/json", (request, response)
                -> (dao.findLongUrl(request.params(":shortUrl"))),new JsonTransformer());
    }

}
