package kg.ebooks.eBook.db.service.impl;


import com.amazonaws.AmazonServiceException;
import com.google.gson.Gson;
import kg.ebooks.eBook.aws.model.FileInfo;
import kg.ebooks.eBook.aws.service.FileService;
import kg.ebooks.eBook.db.domain.dto.Response;
import kg.ebooks.eBook.db.domain.dto.basket.TotalAmount;
import kg.ebooks.eBook.db.domain.dto.client.ClientDto;
import kg.ebooks.eBook.db.domain.dto.client.ClientDtoResponse;
import kg.ebooks.eBook.db.domain.dto.client.ClientUpdateRequest;
import kg.ebooks.eBook.db.domain.dto.security.SignupRequestClnt;
import kg.ebooks.eBook.db.domain.mapper.SignupRequestClntMapper;
import kg.ebooks.eBook.db.domain.model.books.Book;
import kg.ebooks.eBook.db.domain.model.enums.TypeOfBook;
import kg.ebooks.eBook.db.domain.model.users.AuthenticationInfo;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.db.repository.AuthenticationInfoRepository;
import kg.ebooks.eBook.db.repository.ClientRepository;
import kg.ebooks.eBook.db.service.BasketService;
import kg.ebooks.eBook.db.service.ClientService;
import kg.ebooks.eBook.db.service.EmailService;
import kg.ebooks.eBook.exceptions.AlreadyExistsException;
import kg.ebooks.eBook.exceptions.ClientNotFoundException;
import kg.ebooks.eBook.exceptions.DoesNotExistsException;
import kg.ebooks.eBook.exceptions.InvalidPasswordException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.entity.ContentType;
import org.modelmapper.ModelMapper;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.mail.MessagingException;
import javax.mail.util.ByteArrayDataSource;
import javax.transaction.Transactional;
import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationInfoRepository authenticationInfoRepository;
    private final EmailService emailService;
    private final BasketService basketService;
    private final FileService fileService;
    private final Gson gson;


    @Override
    public SignupRequestClnt registerClient(SignupRequestClnt signupRequest) {
        String email = signupRequest.getEmail();
        log.info("Saving new user {} to the database", email);

        Optional<AuthenticationInfo> byEmail =
                authenticationInfoRepository.findByEmail(email);

        if (byEmail.isPresent()) {
            throw new AlreadyExistsException(
                    "user with email = " + email + "has already exists"
            );
        }

        Client client = SignupRequestClntMapper.makeClient(signupRequest);
        client.setDateOfRegistration(LocalDate.now());
        client.getAuthenticationInfo().setPassword(
                passwordEncoder.encode(client.getAuthenticationInfo().getPassword())
        );
        return SignupRequestClntMapper.makeSignupRequestsClnt(clientRepository.save(client));
    }


    @Override
    public List<ClientDtoResponse> getClients() {
        log.info("ClientController  - getClients -: {}");
        return clientRepository.findAll()
                .stream()
                .map(this::clientDto)
                .collect(Collectors.toList());
    }

    public ClientDtoResponse clientDto(Client client) {
        ClientDtoResponse clientDto = new ClientDtoResponse();
        clientDto.setClientId(client.getClientId());
        clientDto.setName(client.getName());
        clientDto.setEmail(client.getEmail());
//        clientDto.setPassword(client.getAuthenticationInfo().getPassword());
        return clientDto;
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository
                .findById(id)
                .orElseThrow(() -> {
                    ClientNotFoundException notFoundException = new ClientNotFoundException(
                            "client with id  " + id + "  not found");
                    log.error("error in getting client {}", id, notFoundException);
                    return notFoundException;
                });
    }


    @Override
    @Transactional
    public ClientDto updateClient(Long id, ClientUpdateRequest clientDto) {

        Client clientFromDatabase = clientRepository.findById(id)
                .orElseThrow(() -> new DoesNotExistsException(
                        "client with id = " + id + " does not exists"
                ));
        if (!clientDto.getEmail().equals(clientFromDatabase.getEmail())) {
            Optional<Client> clientOptional = clientRepository.findByEmail(clientDto.getEmail());

            if (clientOptional.isPresent()) {
                log.error("client with email {} has already exists", clientDto.getEmail());
                throw new AlreadyExistsException(
                        "client with email = " + clientDto.getEmail() + " has already exists"
                );
            }
        }
        String currentPassword = clientFromDatabase.getAuthenticationInfo().getPassword();
        String currentPassword2 = passwordEncoder.encode(clientDto.getCurrentPassword());
        System.out.println(currentPassword);
        System.out.println(currentPassword2);

        boolean matches = passwordEncoder.matches(clientDto.getCurrentPassword(), clientFromDatabase.getPassword());

        if (!matches) {
            throw new InvalidPasswordException(
                    "invalid current password "
            );
        }

        clientFromDatabase.setName(clientDto.getName());
        clientFromDatabase.setEmail(clientDto.getEmail());
        clientFromDatabase.getAuthenticationInfo().setPassword(passwordEncoder.encode(clientDto.getNewPassword()));

        return modelMapper.map(clientFromDatabase, ClientDto.class);
    }

    @Override
    public void deleteClientById(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ClientNotFoundException(
                    "Client with id " + id + " does not exists"
            );
        }
        clientRepository.deleteById(id);
    }

    @Override
    public ClientDtoResponse getInfo(String email) {
        Client client = getByEmailOrElseThrow(email);
        return modelMapper.map(client, ClientDtoResponse.class);
    }

    private Client getByEmailOrElseThrow(String email) {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new ClientNotFoundException(
                        String.format("client with email = %s does not exists", email)
                ));
    }

    @Override
    public String makeAPurchase(String email) throws MessagingException, IOException {
        StringBuilder stringBuilder = new StringBuilder();

        Client client = getByEmailOrElseThrow(email);

        TotalAmount totalAmount = basketService.getTotalAmount(email);
        stringBuilder.append(totalAmount);

        for (Book book : client.getBasket().getBooks()) {
            log.info("book name {}", book.getBookName());
            switch (book.getTypeOfBook()) {
                case PAPER_BOOK:
                    log.info("hello");
                    break;
                case AUDIO_BOOK:
                    try {
                        FileInfo audioBook = book.getAudioBook().getAudioBook();

                        byte[] downloadFile = fileService.downloadFile(audioBook.getId());

                        emailService.send(client.getEmail(), new ByteArrayDataSource(downloadFile, String.valueOf(ContentType.MULTIPART_FORM_DATA)));
                        break;
                    } catch (AmazonServiceException amazonServiceException) {
                        log.info("just can't download file from amazon because it just doesn't exist");
                    }
                    break;
                case ELECTRONIC_BOOK:
                    try {
                        FileInfo electronicBook = book.getElectronicBook().getElectronicBook();

                        byte[] downloadFile1 = fileService.downloadFile(electronicBook.getId());

                        System.out.println(Arrays.toString(downloadFile1));

                        emailService.send(client.getEmail(), new ByteArrayDataSource(downloadFile1, String.valueOf(ContentType.MULTIPART_FORM_DATA)));
                        break;
                    } catch (AmazonServiceException amazonServiceException) {
                        log.info("just can't download file from amazon because it just doesn't exist");
                    }
                    break;
            }
        }

        client.getBasket().clear();

        return gson.toJson(new Response("purchase completed successfully"));
    }
}

