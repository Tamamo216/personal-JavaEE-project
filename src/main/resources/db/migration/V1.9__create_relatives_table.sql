CREATE TABLE IF NOT EXISTS relatives(
    id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    full_name VARCHAR(30) NOT NULL,
    gender VARCHAR(10),
    phone_number VARCHAR(10),
    relationship VARCHAR(30),
    employee_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT relatives_employees_fk FOREIGN KEY(employee_id)
    REFERENCES employees(id)
);