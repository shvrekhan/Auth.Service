package com.AuthService.Controller;

import com.AuthService.DTO.LoginRequest;
import com.AuthService.Repository.UsersRepo;
import com.AuthService.Util.CreateCookie;
import com.AuthService.Util.GenerateJwtToken;
import com.AuthService.model.UserDetail;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@RestController
@RequestMapping("auth")
public class LoginController {

    @Autowired
    private UsersRepo userRepo;
    @Autowired
    private GenerateJwtToken generateJwtToken;
    @Autowired
    private CreateCookie createCookie;

    @PostMapping("login")
    public ResponseEntity<String> performLogin(@NotNull @RequestBody LoginRequest loginRequest) {

        String email = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        UserDetail user = userRepo.findByEmail(email);

        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        } else {
            String jwt = generateJwtToken.getToken(user.getFirstName(), user.getEmail());
            String[] jwtArray = jwt.split("\\.");

            Cookie jwtHeader = createCookie.createCookie("19_AUTH_111", jwtArray[0], 3600, true, "/");
            Cookie jwtPayload = createCookie.createCookie("19_AUTH_222", jwtArray[1], 3600, true, "/");
            Cookie jwtSignature = createCookie.createCookie("19_AUTH_333", jwtArray[2], 3600, true, "/");

            HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
            response.addCookie(jwtHeader);
            response.addCookie(jwtPayload);
            response.addCookie(jwtSignature);
        }

        System.out.println("fcvsdvsdvververnnneyuuh");
        String temp = "test";
        return ResponseEntity.ok("ok");
    }

}
