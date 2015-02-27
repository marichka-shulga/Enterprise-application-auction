DROP TABLE public.bid;
DROP TABLE public.lot;
DROP TABLE public.users;
DROP SEQUENCE user_id_seq;
DROP SEQUENCE lot_id_seq;
DROP SEQUENCE bid_id_seq;

CREATE SEQUENCE user_id_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE lot_id_seq START WITH 100 INCREMENT BY 1;
CREATE SEQUENCE bid_id_seq START WITH 100 INCREMENT BY 1;

CREATE TABLE public.users (
       id_user INTEGER NOT NULL DEFAULT NEXTVAL('user_id_seq')
     , first_name VARCHAR(20) NOT NULL
     , last_name VARCHAR(20) NOT NULL
     , login CHAR(10) NOT NULL
     , password CHAR(32) NOT NULL
     , PRIMARY KEY (id_user)
);

CREATE TABLE public.lot (
       id_lot INTEGER NOT NULL DEFAULT NEXTVAL('lot_id_seq')
     , code INTEGER NOT NULL
     , name VARCHAR(30) NOT NULL
     , finash_date TIMESTAMP NOT NULL
     , start_price DECIMAL(10,2) NOT NULL
     , descriptions TEXT
     , state VARCHAR(10) NOT NULL
     , id_user INTEGER NOT NULL
     , PRIMARY KEY (id_lot)
     , CONSTRAINT FK_lot FOREIGN KEY (id_user)
                  REFERENCES public.users (id_user) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE public.bid (
       id_bid INTEGER NOT NULL DEFAULT NEXTVAL('bid_id_seq')
     , rate DECIMAL(10,2) NOT NULL
     , is_winning_bid boolean NOT NULL       
     , id_lot INTEGER NOT NULL
     , id_user INTEGER NOT NULL
     , PRIMARY KEY (id_bid)
     , CONSTRAINT FK_bid_lot FOREIGN KEY (id_lot)
                  REFERENCES public.lot (id_lot)
     , CONSTRAINT FK_bid_user FOREIGN KEY (id_user)
                  REFERENCES public.users (id_user) ON DELETE CASCADE ON UPDATE CASCADE
);

