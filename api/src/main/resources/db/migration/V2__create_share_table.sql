create table share (
    id bigint NOT NULL AUTO_INCREMENT,
    account_id varchar(255),
    share_name varchar(50),
    file_id varchar(255),
    CONSTRAINT pk_share PRIMARY KEY (id)
)