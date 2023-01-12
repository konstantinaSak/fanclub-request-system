package gr.hua.ds.fanclubrequestsystem.repository;

import gr.hua.ds.fanclubrequestsystem.entity.ELAS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ELASRepository extends JpaRepository<ELAS, Integer> {

    @Query("SELECT s FROM ELAS s WHERE s.ID = ?1")
    Optional<ELAS> findElasByID(int ID);

    @Query("SELECT s FROM ELAS s WHERE s.username = ?1")
    ELAS findElasByUsername(String username);

}
