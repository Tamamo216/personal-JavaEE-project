package myproject.project.service;

import myproject.base.exception.NotFoundException;
import myproject.project.dao.ProjectDAO;
import myproject.project.dto.ProjectRequestDTO;
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

    public List<ProjectResponseDTO> getProjects(Integer limit, String orderBy) {
        if (limit == null) limit = -1;
        List<Project> projects = projectDAO.getProjects(limit, orderBy);
        return projectMapper.toProjectDTOs(projects);
    }

    public List<ProjectResponseDTO> getProjectsByDepartment(Long departmentId, String orderBy) {
        List<Project> projects = projectDAO.getProjectsByDepartment(departmentId, orderBy);
        return projectMapper.toProjectDTOs(projects);
    }

    public ProjectResponseDTO getProjectById(Long projectId) throws NotFoundException {
        Project project = projectDAO.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project id doesn't exist")
        );
        return projectMapper.toProjectDTO(project);
    }

    public ProjectResponseDTO addProject(ProjectRequestDTO request) {
        Project newProject = projectMapper.toProject(request);
        return projectMapper.toProjectDTO(projectDAO.add(newProject));
    }

    public ProjectResponseDTO updateProject(ProjectRequestDTO request, Long projectId) throws NotFoundException {
        Project projectToUpdate = projectDAO.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project id doesn't exist"));
        projectMapper.updateProject(projectToUpdate, request);
        return projectMapper.toProjectDTO(projectToUpdate);
    }

    public ProjectResponseDTO removeProject(Long projectId) throws NotFoundException {
        Project projectToRemove = projectDAO.findById(projectId).orElseThrow(
                () -> new NotFoundException("Project id doesn't exist")
        );
        projectDAO.remove(projectId);
        return projectMapper.toProjectDTO(projectToRemove);
    }
}
