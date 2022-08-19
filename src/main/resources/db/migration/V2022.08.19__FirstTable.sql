CREATE TABLE transaction (
    id int not null primary key,
    description varchar,
    transaction_date timestamp with time zone not null,
    created_at timestamp with time zone not null,
    updated_at timestamp with time zone not null,
    version integer not null
);