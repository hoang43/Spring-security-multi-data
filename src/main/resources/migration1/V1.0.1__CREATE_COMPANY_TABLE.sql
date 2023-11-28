CREATE TABLE IF NOT EXISTS COMPANY (
    ID              SERIAL          NOT NULL    PRIMARY KEY,
    NAME            VARCHAR(250)    NOT NULL,
    CODE            VARCHAR(100)    NOT NULL,
    TAX_CODE        VARCHAR(100)    NOT NULL,
    ADDRESS         VARCHAR(255)    NOT NULL,
    PARENT_ID       BIGINT          NULL,
    task_id         BIGINT          NULL,
    is_deleted      boolean         NOT NULL,
    EMAIL           VARCHAR(100)    NOT NULL,
    PHONE           VARCHAR(15)     NOT NULL,
    STATUS          VARCHAR(50)     NOT NULL,
    CREATED_AT      TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT      TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
