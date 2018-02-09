package jsonb.repository;

import jsonb.model.Entity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import java.util.Collection;

public interface EntityRepository extends PagingAndSortingRepository<Entity, Long> {
    @Query(
            nativeQuery = true,
            value = "SELECT * FROM entity e JOIN (SELECT id, MAX(revision) AS maxrevision FROM entity WHERE name = :name AND revision <= :revision AND data @> CAST(:json AS jsonb) GROUP BY id) AS revisions ON e.id = revisions.id AND e.revision = revisions.maxrevision"
    )
    //SELECT * FROM entity e JOIN (SELECT id, MAX(revision) AS maxrevision FROM entity WHERE name = 'test' AND revision <= 100 AND data @> '{"a":1}' GROUP BY id) AS revisions ON e.id = revisions.id AND e.revision = revisions.maxrevision;
    Collection<Entity> dynamicQuery(@Param("name") final String name, @Param("json") final String json, @Param("revision") final long revision);
}
