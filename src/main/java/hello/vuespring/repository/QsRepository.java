package hello.vuespring.repository;

import hello.vuespring.entity.Qs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QsRepository extends JpaRepository<Qs, Long> {

    @Query("select q from qs q where university_id = :universityId")
    List<Qs> findByUniversityId(@Param("universityId") Long universityId);
}
