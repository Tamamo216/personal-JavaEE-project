ALTER TABLE employees
ADD CONSTRAINT emp_dept_fk FOREIGN KEY(deptid)
REFERENCES departments(id) ON DELETE SET NULL;

ALTER TABLE projects
ADD CONSTRAINT proj_dept_fk FOREIGN KEY(managed_department)
REFERENCES departments(id) ON DELETE SET NULL;

ALTER TABLE assignments
ADD CONSTRAINT assign_emp_fk FOREIGN KEY(employee_id)
REFERENCES employees(id) ON DELETE CASCADE;

ALTER TABLE assignments
ADD CONSTRAINT assign_proj_fk FOREIGN KEY(project_id)
REFERENCES projects(id) ON DELETE CASCADE;