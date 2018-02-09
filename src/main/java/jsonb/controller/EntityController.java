package jsonb.controller;

import com.google.gson.Gson;
import jsonb.model.Entity;
import jsonb.model.Query;
import jsonb.repository.EntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
public class EntityController {
    private static final Gson GSON = new Gson();

    private final EntityRepository entityRepository;

    @Autowired
    public EntityController(final EntityRepository entityRepository) {
        this.entityRepository = entityRepository;
    }

    @RequestMapping(path = "/entity", method = RequestMethod.POST)
    public String create(@RequestBody final Entity entity) {
        this.entityRepository.save(entity);

        System.out.println(String.format("saving name: %s", GSON.toJson(entity)));
        return "ok";
    }

    @RequestMapping(path = "/entity/{id}", method = RequestMethod.GET)
    public Entity get(final long id) {
        return this.entityRepository.findOne(id);
    }

    @RequestMapping(path = "/query", method = RequestMethod.POST)
    public Collection<Entity> runQuery(@RequestBody final Query query) {
        return this.entityRepository.dynamicQuery(query.name, GSON.toJson(query.parameters), query.revision);
    }
}
