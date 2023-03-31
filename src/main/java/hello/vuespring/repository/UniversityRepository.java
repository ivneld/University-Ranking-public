package hello.vuespring.repository;

import hello.vuespring.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UniversityRepository extends JpaRepository<University, Long> {

    @Query("select u from university u where id = :universityId")
    public University findOne(@Param("universityId") Long id);

    @Query("select u from university u where engName = :engName")
    public University findByName(@Param("engName") String engName);
}
