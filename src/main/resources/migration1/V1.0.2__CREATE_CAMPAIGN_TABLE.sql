CREATE TABLE IF NOT EXISTS CAMPAIGN (
    ID                  SERIAL          NOT NULL    PRIMARY KEY,
    NAME                TEXT            NOT NULL,
    USER_ID             BIGINT          NOT NULL,
    company_id          BIGINT          NOT NULL,
    BRAND_NAME_ID       BIGINT          NOT NULL,
    TOTAL_PHONE         INT             NULL,
    TOTAL_MESSAGES      INT             NULL,
    total_error_message INT             NULL,
    step                INT             NULL,
    task_id             BIGINT          NULL,
    message_content     text            NULL,
    is_deleted          boolean         NOT NULL,
    is_scan             boolean         NOT NULL,
    STATUS              VARCHAR(20)     NOT NULL,
    SEND_DATE           TIMESTAMP       NOT NULL,
    CREATED_AT          TIMESTAMP       DEFAULT CURRENT_TIMESTAMP,
    UPDATED_AT          TIMESTAMP       DEFAULT CURRENT_TIMESTAMP
);

