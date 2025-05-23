package service;

import model.Administrator;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import repository.AdministratorRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AdministratorService {

    private final AdministratorRepository administratorRepository;
    private final PasswordEncoder passwordEncoder;

    public AdministratorService(AdministratorRepository administratorRepository,
                                PasswordEncoder passwordEncoder) {
        this.administratorRepository = administratorRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Создание нового администратора
    public Administrator createAdministrator(Administrator administrator) {
        administrator.setPassword(passwordEncoder.encode(administrator.getPassword()));
        return administratorRepository.save(administrator);
    }

    // Получение администратора по ID
    public Optional<Administrator> getAdministratorById(Long id) {
        return administratorRepository.findById(id);
    }

    // Получение администратора по username
    public Optional<Administrator> getAdministratorByUsername(String username) {
        return administratorRepository.findByUsername(username);
    }

    // Получение всех администраторов
    public List<Administrator> getAllAdministrators() {
        return administratorRepository.findAll();
    }

    // Обновление данных администратора
    public Administrator updateAdministrator(Long id, Administrator updatedAdmin) {
        return administratorRepository.findById(id)
                .map(admin -> {
                    admin.setFullName(updatedAdmin.getFullName());
                    admin.setUsername(updatedAdmin.getUsername());
                    if (updatedAdmin.getPassword() != null && !updatedAdmin.getPassword().isEmpty()) {
                        admin.setPassword(passwordEncoder.encode(updatedAdmin.getPassword()));
                    }
                    return administratorRepository.save(admin);
                })
                .orElseThrow(() -> new RuntimeException("Administrator not found with id: " + id));
    }

    // Удаление администратора
    public void deleteAdministrator(Long id) {
        administratorRepository.deleteById(id);
    }

    // Проверка существования администратора по username
    public boolean existsByUsername(String username) {
        return administratorRepository.existsByUsername(username);
    }
}
