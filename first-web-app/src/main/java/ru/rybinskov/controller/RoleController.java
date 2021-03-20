package ru.rybinskov.controller;

import ru.rybinskov.dto.RoleDto;
import ru.rybinskov.service.RoleService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ComponentSystemEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class RoleController implements Serializable {

    @EJB
    private RoleService roleService;

    private RoleDto role;

    private List<RoleDto> roles;

    public void preloadData(ComponentSystemEvent componentSystemEvent) {
        roles = roleService.findAll();
    }

    public RoleDto getRole() {
        return role;
    }

    public void setRole(RoleDto user) {
        this.role = role;
    }

    public String createRole() {
        this.role = new RoleDto();
        return "/admin/role_form.xhtml?faces-redirect=true";
    }

    public List<RoleDto> getAllRoles() {
        return roleService.findAll();
    }

    public String editRole(RoleDto role) {
        this.role = role;
        return "/admin/role_form.xhtml?faces-redirect=true";
    }

    public String saveRole() {
        roleService.saveOrUpdate(role);
        return "/admin/role.xhtml?faces-redirect=true";
    }

    public List<RoleDto> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDto> roles) {
        this.roles = roles;
    }
}
