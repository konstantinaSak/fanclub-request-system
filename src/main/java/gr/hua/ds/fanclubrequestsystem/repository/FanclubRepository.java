package gr.hua.ds.fanclubrequestsystem.repository;

import gr.hua.ds.fanclubrequestsystem.entity.Fanclub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FanclubRepository extends JpaRepository<Fanclub, Integer> {

    @Query("SELECT s FROM Fanclub s WHERE s.ID = ?1")
    Optional<Fanclub> findFanclubByID(int ID);

    @Query("SELECT s FROM Fanclub s WHERE s.ID = ?1")
    Fanclub findFanclubByID2(int ID);

    @Query("SELECT s FROM Fanclub s WHERE s.username = ?1")
    Fanclub findFanclubByUsername(String username);

}
