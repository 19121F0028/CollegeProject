package com.project.myapp.controller;




import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.myapp.models.Batch;
import com.project.myapp.models.ERole;
import com.project.myapp.models.Role;
import com.project.myapp.models.User;
import com.project.myapp.payload.request.LoginRequest;
import com.project.myapp.payload.request.RegisterUserRequest;
import com.project.myapp.payload.response.JwtResponse;
import com.project.myapp.payload.response.MessageResponse;
import com.project.myapp.repositories.BatchRepository;
import com.project.myapp.repositories.RoleRepository;
import com.project.myapp.repositories.UserRepository;
import com.project.myapp.security.JwtUtils;
import com.project.myapp.security.services.UserDetailsImpl;
import com.project.myapp.services.FilesService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
  @Autowired
  AuthenticationManager authenticationManager;
@Autowired
private BatchRepository batchRepository;
  @Autowired
  UserRepository userRepository;

  @Autowired
  RoleRepository roleRepository;

  @Autowired
  PasswordEncoder encoder;

  @Autowired
  JwtUtils jwtUtils;
  @Autowired
  FilesService filesService;

  @PostMapping("/signin")
  public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

    SecurityContextHolder.getContext().setAuthentication(authentication);
    String jwt = jwtUtils.generateJwtToken(authentication);
    
    UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();    
    List<String> roles = userDetails.getAuthorities().stream()
        .map(item -> item.getAuthority())
        .collect(Collectors.toList());

    return ResponseEntity.ok(new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getUsername(),
                         roles));
  }

  @PostMapping("/signup")
  public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterUserRequest signUpRequest) {
	  
	  /* Adding roles to the database*/
	  
	  Role r1=new Role(ERole.ROLE_STUDENT);
	  Role r2=new Role(ERole.ROLE_ADMIN);
	  Role r3=new Role(ERole.ROLE_CORDINATOR);
	  Role r4=new Role(ERole.ROLE_HOD);
	  Role r5=new Role(ERole.ROLE_FACULTY);
	  
	  roleRepository.save(r1);
	  roleRepository.save(r2);
	  roleRepository.save(r3);
	  roleRepository.save(r4);
	  roleRepository.save(r5);
	  
//	  Batch b1=new Batch("2019-2022");
//	  Batch b2=new Batch("2020-2022");
//	  Batch b3=new Batch("2021-2023");
//	  batchRepository.save(b1);
//	  batchRepository.save(b2);
//	  batchRepository.save(b3);
	    filesService.deleteAll();
	    filesService.init();
	  
    if (userRepository.existsByUsername(signUpRequest.getUsername())) {
      return ResponseEntity
          .badRequest()
          .body(new MessageResponse("Error: Username is already taken!"));
    }

    
    // Create new user's account
    User user = new User(signUpRequest.getUsername(),
               encoder.encode(signUpRequest.getPassword()));

    Set<Role> roles = new HashSet<>();

    roles.add(roleRepository.findByName(ERole.ROLE_ADMIN)
              .orElseThrow(() -> new RuntimeException("Error: Role is not found.")));

    user.setRoles(roles);
    userRepository.save(user);

    return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
  }

}