package myproject.project.service;

import myproject.project.dao.ProjectDAO;
import myproject.project.dto.ProjectResponseDTO;
import myproject.project.entity.Project;
import myproject.project.mapper.ProjectMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class ProjectService {

    @Inject
    ProjectDAO projectDAO;
    @Inject
    ProjectMapper projectMapper;

    public List<ProjectResponseDTO> getProjects(String orderBy) {
        List<Project> projects = projectDAO.getProjects(orderBy);
        return projectMapper.toProjectDTOs(projects);
    }

    public List<ProjectResponseDTO> getProjectsByDepartment(Long departmentId, String orderBy) {
        List<Project> projects = projectDAO.getProjectsByDepartment(departmentId, orderBy);
        return projectMapper.toProjectDTOs(projects);
    }
}
