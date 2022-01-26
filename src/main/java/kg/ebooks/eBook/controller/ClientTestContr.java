package kg.ebooks.eBook.controller;


import kg.ebooks.eBook.db.domain.dto.ClientDto;
import kg.ebooks.eBook.db.domain.model.users.Client;
import kg.ebooks.eBook.service.ClientServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

//@RestController
//@RequestMapping
public class ClientTestContr {
    @Autowired
    private ModelMapper modelMapper;

    private ClientServices postService;

    public ClientTestContr(@Qualifier("clientServiceImpls") ClientServices postService) {
        super();
        this.postService = postService;
    }

    @GetMapping
    public List<ClientDto> getAllPosts() {

//        return postService.getClients().stream().map(post -> modelMapper.map(post, ClientDto.class))
//                .collect(Collectors.toList());
    return null;}

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getPostById(@PathVariable(name = "id") Long id) {
        Client post = postService.getClientById(id);

        // convert entity to DTO
        ClientDto postResponse = modelMapper.map(post, ClientDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

    @PostMapping
    public ResponseEntity<ClientDto> createPost(@RequestBody ClientDto postDto) {

        // convert DTO to entity
        Client postRequest = modelMapper.map(postDto, Client.class);

        Client post = postService.saveClient(postRequest);

        // convert entity to DTO
        ClientDto postResponse = modelMapper.map(post, ClientDto.class);

        return new ResponseEntity<ClientDto>(postResponse, HttpStatus.CREATED);
    }

    // change the request for DTO
    // change the response for DTO
    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> updatePost(@PathVariable long id, @RequestBody ClientDto postDto) {

        // convert DTO to Entity
        Client postRequest = modelMapper.map(postDto, Client.class);

        Client post = postService.updateClient(id, postRequest);

        // entity to DTO
        ClientDto postResponse = modelMapper.map(post, ClientDto.class);

        return ResponseEntity.ok().body(postResponse);
    }

//    @DeleteMapping("/{id}")
//    public ResponseEntity<ApiResponse> deletePost(@PathVariable(name = "id") Long id) {
//        postService.getClientById(id);
//        ApiResponse apiResponse = new ApiResponse(Boolean.TRUE, "Post deleted successfully", HttpStatus.OK);
//        return new ResponseEntity<ApiResponse>(apiResponse, HttpStatus.OK);
//
}

