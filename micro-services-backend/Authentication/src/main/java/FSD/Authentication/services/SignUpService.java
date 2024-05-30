package FSD.Authentication.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import FSD.Authentication.DAO.User;
import FSD.Authentication.repo.UserRepo;

@Service
public class SignUpService {

    @Autowired
    private UserRepo userRepo;

    public ResponseEntity<?> signUp(User user) {

        String email = user.getEmail();
        String password = user.getPassword();

        if (email == null || email.isEmpty()) {
            return ResponseEntity.badRequest().body("Email cannot be empty!");
        }

        // Check if password is empty or null
        if (password == null || password.isEmpty()) {
            return ResponseEntity.badRequest().body("Password cannot be empty!");
        }

        // Check if email is valid
        if (!isValidEmail(email)) {
            return ResponseEntity.badRequest().body("Invalid email format!");
        }

        if (userRepo.existsByEmail(user.getEmail())) {
            return ResponseEntity.badRequest()
                    .body("User with the same email already exists. Please try other emails.");
        }

        userRepo.saveAndFlush(user);

        return new ResponseEntity<>("Sign-Up Successful!", HttpStatus.OK);

        // return ResponseEntity.ok().body("Sign-Up Successfull!");

    }

    private boolean isValidEmail(String email) {
        // This is a simple check, you can use a more robust email validation method if
        // needed
        return email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}");
    }

}
