package pl.wsikora.petahp.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsikora.petahp.model.entities.Preference;

public interface PreferenceRepo extends JpaRepository<Preference, Long> {
}
