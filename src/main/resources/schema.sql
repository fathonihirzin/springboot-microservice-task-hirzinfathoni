CREATE SCHEMA IF NOT EXISTS book_sc;

CREATE TABLE IF NOT EXISTS book_sc.book (
    id              BIGSERIAL    NOT NULL,
    title           VARCHAR(255) NOT NULL,
    author          VARCHAR(255),
    isbn            VARCHAR(13),
    published_date  DATE,
    CONSTRAINT book_pkey PRIMARY KEY (id)
    );