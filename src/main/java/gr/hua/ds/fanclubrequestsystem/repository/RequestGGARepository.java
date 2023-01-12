package gr.hua.ds.fanclubrequestsystem.repository;

import gr.hua.ds.fanclubrequestsystem.entity.RequestGGA;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RequestGGARepository extends JpaRepository<RequestGGA, Integer> {

    @Query("SELECT s FROM RequestGGA s WHERE s.ID = ?1")
    Optional<RequestGGA> findRequestGGAByID(int ID);

    @Query("SELECT s FROM RequestGGA s WHERE s.GGA.ID = ?1")
    List<RequestGGA> findRequestGGAByGGAID(int GGAID);

    @Query("SELECT r FROM RequestGGA r LEFT JOIN Fanclub s ON r.fanclub.ID=s.ID WHERE r.fanclub.ID = ?1")
    List<RequestGGA> findRequestGGAsByFanclubID(int ID);

}
