package repository;

import model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // Поиск клиента по email
    Optional<Client> findByEmail(String email);

    // Поиск клиента по номеру телефона
    Optional<Client> findByPhone(String phone);

    // Поиск клиента по номеру документа
    Optional<Client> findByDocumentNumber(String documentNumber);

    // Поиск клиентов по фамилии (без учета регистра)
    List<Client> findByLastNameIgnoreCase(String lastName);

    boolean existsByEmail(String email);
    boolean existsByDocumentNumber(String documentNumber);

    // Поиск клиентов по имени и фамилии
    @Query("SELECT c FROM Client c WHERE " +
            "LOWER(c.firstName) LIKE LOWER(CONCAT('%', :name, '%')) OR " +
            "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Client> findByFullNameContaining(@Param("name") String name);

    // Поиск клиентов с активными бронированиями
    @Query("SELECT DISTINCT b.client FROM Booking b WHERE b.status = 'CONFIRMED'")
    List<Client> findClientsWithActiveBookings();
}
