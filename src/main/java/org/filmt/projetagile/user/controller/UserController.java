package org.filmt.projetagile.user.controller;

import lombok.AllArgsConstructor;
import org.filmt.projetagile.replies.model.Reply;
import org.filmt.projetagile.user.model.UserModel;
import org.filmt.projetagile.user.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@AllArgsConstructor
@RequestMapping("api/users")
public class UserController {

    UserService userService ;

    @ResponseStatus(HttpStatus.OK)
    @PutMapping
    public ResponseEntity<UserModel> update(@RequestBody UserModel user) {
        return ResponseEntity.ok(userService.update(user));
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{username}")
    public ResponseEntity<Void> delete(@PathVariable String username) {
        userService.delete(username);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/{username}")
    public ResponseEntity<Optional<UserModel>> getUserByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @GetMapping("")
    public ResponseEntity<List<UserModel>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
