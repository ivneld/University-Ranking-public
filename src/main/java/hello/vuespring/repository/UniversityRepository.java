package hello.vuespring.repository;

import hello.vuespring.entity.University;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UniversityRepository extends JpaRepository<University, Long> {

    @Query("select u from university u where id = :universityId")
    public University findOne(@Param("universityId") Long id);

    @Query("select u from university u where engName = :engName")
    public University findByEngName(@Param("engName") String engName);

    @Query("select u from university u where engName is null")
    public List<University> findByEngNameIsNull();

    @Query("select u from university u where name = ''")
    public List<University> findByNameIsNull();

    public University findByName(String name);

    @Query("select u from university u where citation = '-'")
    public List<University> findNoInfo();
}
