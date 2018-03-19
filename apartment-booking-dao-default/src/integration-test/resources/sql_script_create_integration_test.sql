
DROP TABLE APARTMENTSTEST.T_AMENITIES CASCADE CONSTRAINTS ;/

DROP TABLE APARTMENTSTEST.T_APARTMENT CASCADE CONSTRAINTS ;/

DROP TABLE APARTMENTSTEST.T_AVAILABILITY CASCADE CONSTRAINTS ;/

DROP TABLE APARTMENTSTEST.T_BOOKING CASCADE CONSTRAINTS ;/

DROP TABLE APARTMENTSTEST.T_OWNER CASCADE CONSTRAINTS ;/

DROP TABLE APARTMENTSTEST.T_PRICES CASCADE CONSTRAINTS ;/

DROP TABLE APARTMENTSTEST.T_SPACE CASCADE CONSTRAINTS ;/

DROP TABLE APARTMENTSTEST.T_USER CASCADE CONSTRAINTS ;/

CREATE TABLE APARTMENTSTEST.T_AMENITIES
  (
    AM_WIRELESS       CHAR (1) ,
    AM_FREE_PARKING   CHAR (1) ,
    T_APARTMENT_AP_ID NUMBER NOT NULL
  ) ;/
CREATE UNIQUE INDEX APARTMENTSTEST.T_AMENITIES__IDX ON APARTMENTSTEST.T_AMENITIES
  (
    T_APARTMENT_AP_ID ASC
  )
  ;/


CREATE TABLE APARTMENTSTEST.T_APARTMENT
  (
    AP_ID         NUMBER NOT NULL ,
    T_OWNER_OW_ID NUMBER NOT NULL
  ) ;/
ALTER TABLE APARTMENTSTEST.T_APARTMENT ADD CONSTRAINT T_APARTMENT_PK PRIMARY KEY ( AP_ID ) ;/


CREATE TABLE APARTMENTSTEST.T_AVAILABILITY
  (
    AV_MIN_NIGHT      INTEGER ,
    T_APARTMENT_AP_ID NUMBER NOT NULL
  ) ;/
CREATE UNIQUE INDEX APARTMENTSTEST.T_AVAILABILITY__IDX ON APARTMENTSTEST.T_AVAILABILITY
  (
    T_APARTMENT_AP_ID ASC
  )
  ;/


CREATE TABLE APARTMENTSTEST.T_BOOKING
  (
    BO_ID             INTEGER NOT NULL ,
    BO_CHECK_IN       TIMESTAMP ,
    BO_CHECK_OUT      TIMESTAMP ,
    BO_TOTAL_SUM      NUMBER (12,4) ,
    BO_DATE_BOOKED    TIMESTAMP ,
    T_USER_US_ID      INTEGER NOT NULL ,
    T_APARTMENT_AP_ID NUMBER NOT NULL
  ) ;/
CREATE UNIQUE INDEX APARTMENTSTEST.T_BOOKING__IDX ON APARTMENTSTEST.T_BOOKING
  (
    T_APARTMENT_AP_ID ASC
  )
  ;/
CREATE UNIQUE INDEX APARTMENTSTEST.T_BOOKING__IDXv1 ON APARTMENTSTEST.T_BOOKING
  (
    T_USER_US_ID ASC
  )
  ;/
ALTER TABLE APARTMENTSTEST.T_BOOKING ADD CONSTRAINT T_BOOKING_PK PRIMARY KEY ( BO_ID ) ;/


CREATE TABLE APARTMENTSTEST.T_OWNER
  (
    OW_ID      NUMBER NOT NULL ,
    OW_TITLE   VARCHAR2 (255) ,
    OW_ADDRESS VARCHAR2 (255)
  ) ;/
ALTER TABLE APARTMENTSTEST.T_OWNER ADD CONSTRAINT T_OWNER_PK PRIMARY KEY ( OW_ID ) ;/


CREATE TABLE APARTMENTSTEST.T_PRICES
  (
    PR_DAILY            NUMBER (8,4) ,
    PR_WEEKLY_DISCOUNT  NUMBER ,
    PR_MONTHLY_DISCOUNT NUMBER ,
    T_APARTMENT_AP_ID   NUMBER NOT NULL
  ) ;/
CREATE UNIQUE INDEX APARTMENTSTEST.T_PRICES__IDX ON APARTMENTSTEST.T_PRICES
  (
    T_APARTMENT_AP_ID ASC
  )
  ;/


CREATE TABLE APARTMENTSTEST.T_SPACE
  (
    SP_ACCOMMODATES    INTEGER ,
    SP_BEDS           INTEGER ,
    SP_ROOM_TYPE      VARCHAR2 (45) ,
    T_APARTMENT_AP_ID NUMBER NOT NULL
  ) ;/
CREATE UNIQUE INDEX APARTMENTSTEST.T_SPACE__IDX ON APARTMENTSTEST.T_SPACE
  (
    T_APARTMENT_AP_ID ASC
  )
  ;/


CREATE TABLE APARTMENTSTEST.T_USER
  (
    US_ID       INTEGER NOT NULL ,
    US_NAME     VARCHAR2 (255 BYTE) ,
    US_EMAIL    VARCHAR2 (255) ,
    US_PASSWORD VARCHAR2 (255)
  ) ;/
ALTER TABLE APARTMENTSTEST.T_USER ADD CONSTRAINT T_USER_PK PRIMARY KEY ( US_ID ) ;/


ALTER TABLE APARTMENTSTEST.T_AMENITIES ADD CONSTRAINT T_AMENITIES_T_APARTMENT_FK FOREIGN KEY ( T_APARTMENT_AP_ID ) REFERENCES APARTMENTSTEST.T_APARTMENT ( AP_ID ) ;/

ALTER TABLE APARTMENTSTEST.T_APARTMENT ADD CONSTRAINT T_APARTMENT_T_OWNER_FK FOREIGN KEY ( T_OWNER_OW_ID ) REFERENCES APARTMENTSTEST.T_OWNER ( OW_ID ) ;/

ALTER TABLE APARTMENTSTEST.T_AVAILABILITY ADD CONSTRAINT T_AVAILABILITY_T_APARTMENT_FK FOREIGN KEY ( T_APARTMENT_AP_ID ) REFERENCES APARTMENTSTEST.T_APARTMENT ( AP_ID ) ;/

ALTER TABLE APARTMENTSTEST.T_BOOKING ADD CONSTRAINT T_BOOKING_T_APARTMENT_FK FOREIGN KEY ( T_APARTMENT_AP_ID ) REFERENCES APARTMENTSTEST.T_APARTMENT ( AP_ID ) ;/

ALTER TABLE APARTMENTSTEST.T_BOOKING ADD CONSTRAINT T_BOOKING_T_USER_FK FOREIGN KEY ( T_USER_US_ID ) REFERENCES APARTMENTSTEST.T_USER ( US_ID ) ;/

ALTER TABLE APARTMENTSTEST.T_PRICES ADD CONSTRAINT T_PRICES_T_APARTMENT_FK FOREIGN KEY ( T_APARTMENT_AP_ID ) REFERENCES APARTMENTSTEST.T_APARTMENT ( AP_ID ) ;/

ALTER TABLE APARTMENTSTEST.T_SPACE ADD CONSTRAINT T_SPACE_T_APARTMENT_FK FOREIGN KEY ( T_APARTMENT_AP_ID ) REFERENCES APARTMENTSTEST.T_APARTMENT ( AP_ID ) ;/

CREATE OR REPLACE TRIGGER APARTMENTSTEST.T_APARTMENT_AP_ID_TRG BEFORE
  INSERT ON APARTMENTSTEST.T_APARTMENT FOR EACH ROW WHEN (NEW.AP_ID IS NULL) BEGIN :NEW.AP_ID := APARTMENTSTEST.T_APARTMENT_AP_ID_SEQ.NEXTVAL;
END;
;/

CREATE OR REPLACE TRIGGER APARTMENTSTEST.T_BOOKING_BO_ID_TRG BEFORE
  INSERT ON APARTMENTSTEST.T_BOOKING FOR EACH ROW WHEN (NEW.BO_ID IS NULL) BEGIN :NEW.BO_ID := APARTMENTSTEST.T_BOOKING_BO_ID_SEQ.NEXTVAL;
END;
;/


CREATE OR REPLACE TRIGGER APARTMENTSTEST.T_OWNER_OW_ID_TRG BEFORE
  INSERT ON APARTMENTSTEST.T_OWNER FOR EACH ROW WHEN (NEW.OW_ID IS NULL) BEGIN :NEW.OW_ID := APARTMENTSTEST.T_OWNER_OW_ID_SEQ.NEXTVAL;
END;
;/


CREATE OR REPLACE TRIGGER APARTMENTSTEST.T_USER_US_ID_TRG BEFORE
  INSERT ON APARTMENTSTEST.T_USER FOR EACH ROW WHEN (NEW.US_ID IS NULL) BEGIN :NEW.US_ID := APARTMENTSTEST.T_USER_US_ID_SEQ.NEXTVAL;
END;
;/