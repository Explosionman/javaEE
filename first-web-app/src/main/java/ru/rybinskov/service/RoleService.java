package ru.rybinskov.service;

import ru.rybinskov.dto.RoleDto;
import ru.rybinskov.dto.UserDto;

import javax.ejb.Local;
import java.util.List;

@Local
public interface RoleService {

    List<RoleDto> findAll();

    RoleDto findById(Long id);

    Long countAll();

    void saveOrUpdate(RoleDto roleDto);
}
