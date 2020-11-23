package ru.vallione.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vallione.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
