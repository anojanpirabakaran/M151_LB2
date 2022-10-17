package com.example.webshop_be.domain.authority;

import com.example.webshop_be.domain.authority.mapper.AuthorityMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/authorities")
public class AuthorityController {

    private final AuthorityService authorityService;
    private final AuthorityMapper authorityMapper;

    @Autowired
    public AuthorityController(AuthorityService authorityService, AuthorityMapper authorityMapper) {
        this.authorityService = authorityService;
        this.authorityMapper = authorityMapper;
    }

    @GetMapping({"/{id}", "/{id}/"})
    public @ResponseBody ResponseEntity<AuthorityDTO> getById(@PathVariable String id) {
        Authority authority = authorityService.findById(id);
        return new ResponseEntity<>(authorityMapper.toDTO(authority), HttpStatus.OK);
    }

    @GetMapping({"", "/"})
    public @ResponseBody ResponseEntity<List<AuthorityDTO>> getAll() {
        List<Authority> authorities = authorityService.getAllAuthorities();
        return new ResponseEntity<>(authorityMapper.toDTOs(authorities), HttpStatus.OK);
    }

    @PostMapping({"/", ""})
    public ResponseEntity<AuthorityDTO> create(@RequestBody AuthorityDTO authorityDTO)
            throws Exception {
        Authority authority =
                authorityService.createAuthority(authorityMapper.fromDTO(authorityDTO));
        return new ResponseEntity<>(authorityMapper.toDTO(authority), HttpStatus.CREATED);
    }

    @PutMapping({"/{id}", "/{id}/"})
    public ResponseEntity<AuthorityDTO> updateById(@PathVariable String id,
                                                   @RequestBody AuthorityDTO authorityDTO)
            throws Exception {
        Authority authority = authorityMapper.fromDTO(authorityDTO);
        authorityService.updateAuthority(id, authority);
        return new ResponseEntity<>(authorityMapper.toDTO(authority), HttpStatus.OK);
    }

    @DeleteMapping({"/{id}/", "/{id}"})
    public ResponseEntity<Void> delete(@PathVariable String id) throws Exception {
        authorityService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
