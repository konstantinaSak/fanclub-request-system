package gr.hua.ds.fanclubrequestsystem.repository;

import gr.hua.ds.fanclubrequestsystem.entity.Fan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FanRepository extends JpaRepository<Fan, Integer> {

    @Query("SELECT s FROM Fan s WHERE s.ID = ?1")
    Optional<Fan> findFanByID(int ID);

    @Query("SELECT s FROM Fan s WHERE s.fanclub.ID = ?1")
    List<Fan> findFanByFanclubID(int fanclubID);

}
