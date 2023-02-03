package gr.hua.ds.fanclubrequestsystem.repository;

import gr.hua.ds.fanclubrequestsystem.entity.Authorities;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthoritiesRepository extends JpaRepository<Authorities, String> {

    @Query("SELECT s FROM Authorities s WHERE s.username = ?1")
    Authorities findAuthoritiesByUsername(String username);

}
