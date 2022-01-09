create table cards
(
    id         UUID PRIMARY KEY,
    cardnumber TEXT    NOT NULL,
    balance    NUMERIC NOT NULL
)