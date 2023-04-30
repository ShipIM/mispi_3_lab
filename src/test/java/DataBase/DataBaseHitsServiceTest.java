package DataBase;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class DataBaseHitsServiceTest {
    private final DataBaseHitDAO dao = new DataBaseHitDAO();
    private final List<DataBaseHit> hits = new ArrayList<>(Arrays.asList(
            new DataBaseHit(1, 1, 1, true, LocalDateTime.now(), 1),
            new DataBaseHit(2, 2, 2, true, LocalDateTime.now(), 2),
            new DataBaseHit(3, 3, 3, true, LocalDateTime.now(), 3)
    ));

    @Test
    void getAllEntitiesFromDataBase() {
        hits.forEach(hit -> {
            if (dao.getAll().contains(hit))
                dao.delete(hit);

            dao.create(hit);
        });

        Assertions.assertEquals(3, dao.getAll().size());
    }

    @Test
    void addNewEntityToADataBase() {
        hits.forEach(hit -> {
            if (dao.getAll().contains(hit))
                dao.delete(hit);

            dao.create(hit);
        });

        List<Boolean> conditions = new ArrayList<>();
        hits.forEach(hit -> conditions.add(dao.getAll().contains(hit)));

        Assertions.assertTrue(conditions.stream().allMatch(Boolean::booleanValue));
    }

    @Test
    void deleteEntityFromADataBase() {
        hits.forEach(dao::create);

        hits.forEach(dao::delete);

        List<Boolean> conditions = new ArrayList<>();
        hits.forEach(hit -> conditions.add(!dao.getAll().contains(hit)));

        Assertions.assertTrue(conditions.stream().allMatch(Boolean::booleanValue));
    }
}
