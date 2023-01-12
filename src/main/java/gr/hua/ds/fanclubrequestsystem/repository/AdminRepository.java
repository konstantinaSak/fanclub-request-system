package gr.hua.ds.fanclubrequestsystem.repository;

import gr.hua.ds.fanclubrequestsystem.entity.Admin;
import gr.hua.ds.fanclubrequestsystem.entity.Fanclub;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
}
