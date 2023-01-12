package gr.hua.ds.fanclubrequestsystem.repository;

import gr.hua.ds.fanclubrequestsystem.entity.GGA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GGARepository extends JpaRepository<GGA, Integer> {

    @Query("SELECT s FROM GGA s WHERE s.ID = ?1")
    Optional<GGA> findGGAByID(int ID);

    @Query("SELECT s FROM GGA s WHERE s.username = ?1")
    GGA findGGAByUsername(String username);

}
