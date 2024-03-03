CREATE TABLE department_locations (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    location VARCHAR(100),
    deptid BIGINT,
    CONSTRAINT dept_location_fk FOREIGN KEY (deptid)
    REFERENCES departments(id) ON DELETE CASCADE
);