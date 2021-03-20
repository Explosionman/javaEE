package ru.rybinskov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.rybinskov.dto.RoleDto;
import ru.rybinskov.dto.UserDto;
import ru.rybinskov.service.RoleService;
import ru.rybinskov.service.UserService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Named
@SessionScoped
public class UserController implements Serializable {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @EJB
    private UserService userService;

    @EJB
    private RoleService roleService;

    private UserDto user;

    private List<RoleDto> userRoles;

    private List<UserDto> users;

    private List<RoleDto> roles;


    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        users = userService.findAll();
        roles = roleService.findAll();
    }

    public List<RoleDto> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(List<RoleDto> userRoles) {
        this.userRoles = userRoles;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public String createUser() {
        this.user = new UserDto();
        user.setRoles(roles);
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public List<UserDto> getAllUsers() {
        return userService.findAll();
    }

    public String editUser(UserDto user) {
        this.user = user;
        return "/admin/user_form.xhtml?faces-redirect=true";
    }

    public void deleteUser(UserDto user) {
        userService.deleteById(user.getId());
    }

    public String saveUser() {
        logger.info(user.toString());
        userService.saveOrUpdate(user);
        return "/admin/user.xhtml?faces-redirect=true";
    }

    public List<UserDto> getUsers() {
        return users;
    }

    public void setUsers(List<UserDto> users) {
        this.users = users;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
