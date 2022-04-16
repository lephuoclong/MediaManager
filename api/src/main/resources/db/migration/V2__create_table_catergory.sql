CREATE TABLE category (
    id BIGINT not null,
    account_id varchar(255) not null,
    file_id varchar(255) not null,
    categoryName nvarchar(50) not null,
    CONSTRAINT pk_category PRIMARY KEY (id)
);

ALTER TABLE directory MODIFY COLUMN parent_id varchar(255) not null;
ALTER TABLE directory DROP COLUMN flag_parent;