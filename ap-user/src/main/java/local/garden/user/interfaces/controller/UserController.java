package local.garden.user.interfaces.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.garden.user.domain.model.User;
import local.garden.user.usecase.UserInteractor;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserInteractor interactor;

    @GetMapping
    public String hello() {
        return "hello";
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") String id) {
        return interactor.findById(id);
    }
}