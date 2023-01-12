package gr.hua.ds.fanclubrequestsystem.repository;

import gr.hua.ds.fanclubrequestsystem.entity.RequestELAS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestELASRepository extends JpaRepository<RequestELAS, Integer> {

    @Query("SELECT s FROM RequestELAS s WHERE s.ID = ?1")
    Optional<RequestELAS> findRequestELASByID(int ID);

    @Query("SELECT s FROM RequestELAS s WHERE s.ELAS.ID = ?1")
    List<RequestELAS> findRequestElasByElasID(int ElasID);

    @Query("SELECT r FROM RequestELAS r LEFT JOIN Fanclub s ON r.fanclub.ID=s.ID WHERE r.fanclub.ID = ?1")
    List<RequestELAS> findRequestELASsByFanclubID(int ID);

}
