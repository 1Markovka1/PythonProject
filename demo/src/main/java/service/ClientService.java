package service;

import model.Client;
import org.springframework.stereotype.Service;
import repository.ClientRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    // Создание нового клиента
    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    // Получение клиента по ID
    public Optional<Client> getClientById(Long id) {
        return clientRepository.findById(id);
    }

    // Получение клиента по email
    public Optional<Client> getClientByEmail(String email) {
        return clientRepository.findByEmail(email);
    }

    // Получение клиента по номеру телефона
    public Optional<Client> getClientByPhone(String phone) {
        return clientRepository.findByPhone(phone);
    }

    // Получение всех клиентов
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    // Поиск клиентов по фамилии
    public List<Client> searchClientsByLastName(String lastName) {
        return clientRepository.findByLastNameIgnoreCase(lastName);
    }

    // Поиск клиентов по имени/фамилии
    public List<Client> searchClientsByName(String name) {
        return clientRepository.findByFullNameContaining(name);
    }

    // Обновление данных клиента
    public Client updateClient(Long id, Client updatedClient) {
        return clientRepository.findById(id)
                .map(client -> {
                    client.setFirstName(updatedClient.getFirstName());
                    client.setLastName(updatedClient.getLastName());
                    client.setEmail(updatedClient.getEmail());
                    client.setPhone(updatedClient.getPhone());
                    client.setDocumentNumber(updatedClient.getDocumentNumber());
                    return clientRepository.save(client);
                })
                .orElseThrow(() -> new RuntimeException("Client not found with id: " + id));
    }

    // Удаление клиента
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // Проверка существования клиента по email
    public boolean existsByEmail(String email) {
        return clientRepository.existsByEmail(email);
    }

    // Проверка существования клиента по номеру документа
    public boolean existsByDocumentNumber(String docNumber) {
        return clientRepository.existsByDocumentNumber(docNumber);
    }
}
