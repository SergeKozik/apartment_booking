Insert into T_USER (US_NAME,US_ID,US_EMAIL,US_PASSWORD) values ('Angela',3,'angela@angela.com','Angela1');/
Insert into T_USER (US_NAME,US_ID,US_EMAIL,US_PASSWORD) values ('Bob',1,'bob@bob.com','Bob1');/
Insert into T_USER (US_NAME,US_ID,US_EMAIL,US_PASSWORD) values ('Givi',2,'givi@givi.com','Givi1');/

Insert into T_OWNER (OW_ID,OW_TITLE,OW_ADDRESS) values (1,'Leccio Apartment - Cimbolello','Citta della Pieve, Umbria, Italy');/
Insert into T_OWNER (OW_ID,OW_TITLE,OW_ADDRESS) values (2,'CENTRAL Comfy Artist''s Home','London, United Kingdom');/
Insert into T_OWNER (OW_ID,OW_TITLE,OW_ADDRESS) values (3,'Loft in historic Cadiz','Cadiz, Andalusia, Spain');/
Insert into T_OWNER (OW_ID,OW_TITLE,OW_ADDRESS) values (4,'Fantastic treehut between old oaks','Huetor - Santillan, Andalusia, Spain');/
Insert into T_OWNER (OW_ID,OW_TITLE,OW_ADDRESS) values (5,'City Center 8 Beds Dorm','Warsaw, Mazowieckie, Poland');/
Insert into T_OWNER (OW_ID,OW_TITLE,OW_ADDRESS) values (6,'Waterfront Flat with Balcony stunning location','Scarborough, United Kingdom');/

Insert into T_APARTMENT (AP_ID,T_OWNER_OW_ID) values (3,3);/
Insert into T_APARTMENT (AP_ID,T_OWNER_OW_ID) values (4,4);/
Insert into T_APARTMENT (AP_ID,T_OWNER_OW_ID) values (5,5);/
Insert into T_APARTMENT (AP_ID,T_OWNER_OW_ID) values (6,6);/
Insert into T_APARTMENT (AP_ID,T_OWNER_OW_ID) values (1,1);/
Insert into T_APARTMENT (AP_ID,T_OWNER_OW_ID) values (2,2);/

Insert into T_SPACE (SP_ACCOMMODATES,SP_BEDS,SP_ROOM_TYPE,T_APARTMENT_AP_ID) values (4,2,'Entire home',3);/
Insert into T_SPACE (SP_ACCOMMODATES,SP_BEDS,SP_ROOM_TYPE,T_APARTMENT_AP_ID) values (4,2,'Private room',4);/
Insert into T_SPACE (SP_ACCOMMODATES,SP_BEDS,SP_ROOM_TYPE,T_APARTMENT_AP_ID) values (16,8,'Shared room',5);/
Insert into T_SPACE (SP_ACCOMMODATES,SP_BEDS,SP_ROOM_TYPE,T_APARTMENT_AP_ID) values (2,1,'Shared room',6);/
Insert into T_SPACE (SP_ACCOMMODATES,SP_BEDS,SP_ROOM_TYPE,T_APARTMENT_AP_ID) values (3,2,'Entire home',1);/
Insert into T_SPACE (SP_ACCOMMODATES,SP_BEDS,SP_ROOM_TYPE,T_APARTMENT_AP_ID) values (3,1,'Private room',2);/

Insert into T_PRICES (PR_DAILY,PR_WEEKLY_DISCOUNT,PR_MONTHLY_DISCOUNT,T_APARTMENT_AP_ID) values (98,0.05,0.15,3);/
Insert into T_PRICES (PR_DAILY,PR_WEEKLY_DISCOUNT,PR_MONTHLY_DISCOUNT,T_APARTMENT_AP_ID) values (76,0.15,0,4);/
Insert into T_PRICES (PR_DAILY,PR_WEEKLY_DISCOUNT,PR_MONTHLY_DISCOUNT,T_APARTMENT_AP_ID) values (10,0.1,0.15,5);/
Insert into T_PRICES (PR_DAILY,PR_WEEKLY_DISCOUNT,PR_MONTHLY_DISCOUNT,T_APARTMENT_AP_ID) values (77,0.15,0.15,6);/
Insert into T_PRICES (PR_DAILY,PR_WEEKLY_DISCOUNT,PR_MONTHLY_DISCOUNT,T_APARTMENT_AP_ID) values (65,0.1,0.21,1);/
Insert into T_PRICES (PR_DAILY,PR_WEEKLY_DISCOUNT,PR_MONTHLY_DISCOUNT,T_APARTMENT_AP_ID) values (66,0,0,2);/

Insert into T_AVAILABILITY (AV_MIN_NIGHT,T_APARTMENT_AP_ID) values (1,3);/
Insert into T_AVAILABILITY (AV_MIN_NIGHT,T_APARTMENT_AP_ID) values (2,4);/
Insert into T_AVAILABILITY (AV_MIN_NIGHT,T_APARTMENT_AP_ID) values (1,5);/
Insert into T_AVAILABILITY (AV_MIN_NIGHT,T_APARTMENT_AP_ID) values (1,6);/
Insert into T_AVAILABILITY (AV_MIN_NIGHT,T_APARTMENT_AP_ID) values (2,1);/
Insert into T_AVAILABILITY (AV_MIN_NIGHT,T_APARTMENT_AP_ID) values (3,2);/

Insert into T_AMENITIES (AM_WIRELESS,AM_FREE_PARKING,T_APARTMENT_AP_ID) values ('1','0',3);/
Insert into T_AMENITIES (AM_WIRELESS,AM_FREE_PARKING,T_APARTMENT_AP_ID) values ('0','1',4);/
Insert into T_AMENITIES (AM_WIRELESS,AM_FREE_PARKING,T_APARTMENT_AP_ID) values ('1','0',5);/
Insert into T_AMENITIES (AM_WIRELESS,AM_FREE_PARKING,T_APARTMENT_AP_ID) values ('1','0',6);/
Insert into T_AMENITIES (AM_WIRELESS,AM_FREE_PARKING,T_APARTMENT_AP_ID) values ('1','1',1);/
Insert into T_AMENITIES (AM_WIRELESS,AM_FREE_PARKING,T_APARTMENT_AP_ID) values ('1','0',2);/

Insert into T_BOOKING (BO_ID,BO_CHECK_IN,BO_CHECK_OUT,BO_TOTAL_SUM,BO_DATE_BOOKED,T_USER_US_ID,T_APARTMENT_AP_ID) values (1,to_timestamp('03-AUG-17 12.00.00.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('13-AUG-17 12.00.00.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),100.25,to_timestamp('20-FEB-17 07.05.00.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),1,1);/
Insert into T_BOOKING (BO_ID,BO_CHECK_IN,BO_CHECK_OUT,BO_TOTAL_SUM,BO_DATE_BOOKED,T_USER_US_ID,T_APARTMENT_AP_ID) values (2,to_timestamp('29-AUG-17 12.00.00.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),to_timestamp('30-SEP-17 12.00.00.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),200.35,to_timestamp('20-MAR-17 07.05.00.000000000 PM','DD-MON-RR HH.MI.SSXFF AM'),2,3);/

