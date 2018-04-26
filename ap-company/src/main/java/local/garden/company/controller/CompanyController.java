package local.garden.company.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.garden.company.domain.model.Company;
import local.garden.company.infrastructure.CompanyRepository;

@RestController
@RequestMapping("/company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    @GetMapping
    public Iterable<Company> all() {
        return repository.findAll();
    }

    @PostMapping
    public void add() {
        Company c = new Company();
        c.setShortName("ABC");
        c.setFullName("ABC Company");
        c.setCode("9999");

        repository.save(c);
    }
}