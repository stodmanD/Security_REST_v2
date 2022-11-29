package ru.kata.spring.boot_security.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.repository.RoleRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class RoleServieceImpl implements RoleService{
    private final RoleRepository roleRepository;

    @Autowired
    public RoleServieceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public List<Role> getAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public void saveRole (Role role) {
        roleRepository.save(role);
    }



    @Override
    public Role getRole(int id) {
        Role role = null;
        Optional<Role> opr = roleRepository.findById(id);
        if (opr.isPresent()) {
            role = opr.get();
        }
        return role;
    }

    @Override
    public void deleteRoleById(int id) {
        roleRepository.deleteById(id);
    }

    @Override
    public Role getByName(String roleName) throws Exception {
        Role role = roleRepository.findByRole(roleName);
        if (role == null){throw new Exception (roleName);
        }
        return role;
    }

    @Override
    public Set<Role> getRoleSet(String[] role) throws Exception {
        Set <Role> rolSet = new HashSet<>();
            for (String rols : role) {
                rolSet.add(getByName(rols));
            }
        return rolSet;
    }
}
