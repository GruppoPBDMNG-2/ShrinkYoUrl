package Esempio;

import Esempio.Todo;
import com.google.gson.Gson;
import com.mongodb.*;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Gestisce le operazioni CRUD
 */
public class TodoService {

    private final DB db;
    private final DBCollection collection;

    public TodoService(DB db) {
        //prende il db di mongo
        this.db = db;
        //prende la collection todos. Tutte le operazioni sono sulla collection todos
        this.collection = db.getCollection("todos");
    }

    /**
     * fetch di tutti i documenti del db
     * @return
     */
    public List<Todo> findAll() {
        List<Todo> todos = new ArrayList<>();
        DBCursor dbObjects = collection.find();
        while (dbObjects.hasNext()) {
            DBObject dbObject = dbObjects.next();
            todos.add(new Todo((BasicDBObject) dbObject));
        }
        return todos;
    }

    public void createNewTodo(String body) {
        //riceve una stringa json. GSON converte da json a oggetto
        Todo todo = new Gson().fromJson(body, Todo.class);
        //inserisce una BasicDBObject nella collection
        collection.insert(new BasicDBObject("title", todo.getTitle()).append("done", todo.isDone()).append("createdOn", new Date()));
    }

    public Todo find(String id) {
        return new Todo((BasicDBObject) collection.findOne(new BasicDBObject("_id", new ObjectId(id))));
    }

    public Todo update(String todoId, String body) {
        Todo todo = new Gson().fromJson(body, Todo.class);
        collection.update(new BasicDBObject("_id", new ObjectId(todoId)), new BasicDBObject("$set", new BasicDBObject("done", todo.isDone())));
        return this.find(todoId);
    }
}
