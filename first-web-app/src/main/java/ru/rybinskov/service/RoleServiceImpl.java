package ru.rybinskov.service;

import ru.rybinskov.dto.RoleDto;
import ru.rybinskov.entities.Role;
import ru.rybinskov.repository.RoleRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoleServiceImpl implements RoleService {

    @EJB
    private RoleRepository roleRepository;

    @Override
    public List<RoleDto> findAll() {
        return roleRepository.findAll().stream().map(RoleDto::new).collect(Collectors.toList());
    }

    @Override
    public RoleDto findById(Long id) {
        Role role = roleRepository.findById(id);
        if (role == null) return null;
        return new RoleDto(role);
    }

    @Override
    public Long countAll() {
        return roleRepository.countAll();
    }


    @TransactionAttribute
    @Override
    public void saveOrUpdate(RoleDto roleDto) {
        roleRepository.merge(new Role(roleDto));
    }

}
