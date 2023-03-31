package hello.vuespring.repository;

import hello.vuespring.entity.The;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TheRepository extends JpaRepository<The, Long> {

    @Query("select t from the t where university_id = :universityId")
    List<The> findByUniversityId(@Param("universityId") Long universityId);
}
