package codingtechniques.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;

import codingtechniques.model.Responsable;

@CrossOrigin("http://localhost:8080")
@Repository

public interface ResponsableRepositories extends JpaRepository<Responsable, Integer> {

    public Optional<Responsable> findByEmail(String email);
}
