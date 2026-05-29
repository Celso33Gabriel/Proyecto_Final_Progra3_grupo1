CREATE TABLE IF NOT EXISTS nodes (
                                     id        VARCHAR(50) PRIMARY KEY,
    value     VARCHAR(255) NOT NULL,
    parent_id VARCHAR(50) REFERENCES nodes(id) ON DELETE CASCADE
    );