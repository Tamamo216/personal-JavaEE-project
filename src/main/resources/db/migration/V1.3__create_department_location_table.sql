CREATE TABLE department_locations (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    location VARCHAR(100),
    deptid BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT dept_location_fk FOREIGN KEY (deptid)
    REFERENCES departments(id) ON DELETE CASCADE
);