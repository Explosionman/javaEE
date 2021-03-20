package ru.rybinskov.dto;

import ru.rybinskov.entities.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class UserDto implements Serializable {

    private Long id;

    private String login;

    private String password;

    private List<RoleDto> roles;

    private RoleDto role;

    public UserDto() {
    }

    public UserDto(User user) {
        this.id = user.getId();
        this.login = user.getLogin();
        this.password = user.getPassword();
        this.roles = new ArrayList<>();
        user.getRoles().forEach(r -> this.roles.add(new RoleDto(r)));
    }

    public UserDto(Long id, String login, String password, List<RoleDto> roles) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.roles = roles;

    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto role) {
        this.role = role;
        roles.add(role);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
