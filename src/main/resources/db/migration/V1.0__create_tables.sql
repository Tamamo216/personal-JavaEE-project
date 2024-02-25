CREATE TABLE employees (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    date_of_birth DATE,
    first_name VARCHAR(20) NOT NULL,
    middle_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    gender VARCHAR(10),
    salary BIGINT CHECK (salary > 0),
    deptid BIGINT
);

CREATE TABLE departments (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    name VARCHAR(200) NOT NULL UNIQUE,
    start_date DATE
);

CREATE TABLE projects (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    area VARCHAR(100),
    name VARCHAR(100) NOT NULL,
    managed_department BIGINT
);

CREATE TABLE assignments (
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    number_of_hour INT,
    employee_id BIGINT,
    project_id BIGINT
);