package myproject.department.service;

import myproject.base.exception.ConflictException;
import myproject.base.exception.NotFoundException;
import myproject.department.dao.DepartmentDAO;
import myproject.department.dto.DepartmentRequestDTO;
import myproject.department.dto.DepartmentResponseDTO;
import myproject.department.entity.Department;
import myproject.department.mapper.DepartmentMapper;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Objects;

@Stateless
public class DepartmentService {
    @Inject
    DepartmentDAO departmentDAO;
    @Inject
    DepartmentMapper departmentMapper;
    public List<DepartmentResponseDTO> getDepartments(boolean isDesc) {
        List<Department> departments = departmentDAO.getSortedDepartments(isDesc);
        return departments.stream().map(departmentMapper::toDepartmentDTO).toList();
    }

    public DepartmentResponseDTO getDepartmentById(Long departmentId) throws NotFoundException {
        Department department = departmentDAO.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department id doesn't exist"));
        return departmentMapper.toDepartmentDTO(department);
    }

    public DepartmentResponseDTO createDepartment(DepartmentRequestDTO department) throws ConflictException {
        Department newDepartment = departmentMapper.toDepartment(department);
        checkUniqueDepartmentNameBeforeInsert(newDepartment.getName());
        return departmentMapper.toDepartmentDTO(departmentDAO.add(newDepartment));
    }

    public DepartmentResponseDTO updateDepartment(DepartmentRequestDTO departmentDTO, Long departmentId) throws NotFoundException, ConflictException {
        Department departmentToUpdate = departmentDAO.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department id doesn't exist"));
        checkUniqueDepartmentNameBeforeUpdate(departmentDTO.getName(), departmentToUpdate.getId());
        departmentMapper.updateDepartment(departmentToUpdate, departmentDTO);
        return departmentMapper.toDepartmentDTO(departmentToUpdate);
    }

    public DepartmentResponseDTO removeDepartment(Long departmentId) throws NotFoundException {
        Department departmentToRemove = departmentDAO.findById(departmentId)
                .orElseThrow(() -> new NotFoundException("Department id doesn't exist"));
        departmentDAO.remove(departmentToRemove.getId());
        return departmentMapper.toDepartmentDTO(departmentToRemove);
    }

    private void checkUniqueDepartmentNameBeforeInsert(String name) throws ConflictException {
        if (!departmentDAO.getDepartmentByName(name).isEmpty())
            throw new ConflictException("Department name is already existed");
    }

    private void checkUniqueDepartmentNameBeforeUpdate(String name, Long id) throws ConflictException {
        List<Department> departments = departmentDAO.getDepartmentByName(name);
        if (!departments.isEmpty() && !Objects.equals(departments.get(0).getId(), id))
            throw new ConflictException("Department name is already existed");
    }
}
