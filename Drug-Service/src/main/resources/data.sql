--Details of Medicines
-- ******************
--  Medicine : id,name,unit,cost
--   Manufacture: name,location
--   Date: manufactured_date,expire_date

INSERT INTO Drug_Details(id,name,units,cost,location,manufacturer,manufactured_Date,expiry_Date,medical_Composition) VALUES
(1,'Neurotin',1,300,'Mumbai','Dangot','2021-01-02','2022-01-01','gabapentin'),
(2,'Covaxin',1,400,'Goa','Dan','2021-01-02','2022-01-01','hydrocortisone'),
(3,'Acetamino',1,100,'Bangalore','SixerTablet','2021-01-02','2022-01-01',' A-Hydrocort');
INSERT INTO Drug_Details(id,name,units,cost,location,manufacturer,manufactured_Date,expiry_Date,medical_Composition) VALUES
(4,'Azithrom',1,100,'Bangalore','Power','2021-01-02','2022-01-01','warfarin'),
(5,'Citalopram',1,150,'Bihar','MIMS','2021-01-02','2022-01-01','hydrocortisone'),
(6,'Meloxicam',1,100,'Goa','Dan','2021-01-02','2022-01-01','gabapentin'),
(7,'Omeprazole',1,100,'Delhi','Dangot','2021-01-02','2022-01-01',' A-Hydrocort');
INSERT INTO Drug_Details(id,name,units,cost,location,manufacturer,manufactured_Date,expiry_Date,medical_Composition) VALUES
(8,'Fentanyl',1,350,'Bangalore','Power','2021-01-02','2022-01-01',' A-Hydrocort'),
(9,'Naloxone',1,300,'Goa','Dangot','2021-01-02','2022-01-01','gabapentin'),
(10,'Trazodone',1,50,'Bihar','Dan','2021-01-02','2022-01-01','licorice'),
(11,'Entyvio',1,125,'Delhi','MIMS','2021-01-02','2022-01-01','warfarin');
INSERT INTO Drug_Details(id,name,units,cost,location,manufacturer,manufactured_Date,expiry_Date,medical_Composition) VALUES
(12,'Xenical',1,1100,'Bihar','Dan','2021-01-02','2022-01-01','gabapentin'),
(13,'Xtampza ER',1,100,'Mumbai','Power','2021-01-02','2022-01-01','warfarin'),
(14,'Melatonin',1,300,'Bangalore','SixerTablet','2021-01-02','2022-01-01',' A-Hydrocort'),
(15,'Paracetamol',1,450,'Goa','MIMS','2021-01-02','2022-01-01','hydrocortisone'),
(16,'Prolia',1,500,'Delhi','Dan','2021-01-02','2022-01-01','licorice');
INSERT INTO Drug_Details(id,name,units,cost,location,manufacturer,manufactured_Date,expiry_Date,medical_Composition) VALUES
(17,'Protonix',1,200,'Goa','MIMS','2021-01-02','2022-01-01',' A-Hydrocort'),
(18,'Quetiapine',1,120,'Mumbai','Power','2021-01-02','2022-01-01','warfarin');
INSERT INTO Drug_Details(id,name,units,cost,location,manufacturer,manufactured_Date,expiry_Date,medical_Composition) VALUES
(19,'Quazzia',1,350,'Bihar','Dangot','2021-01-02','2022-01-01',' A-licorice'),
(20,'Penciline',1,350,'Mumbai','SixerTablet','2021-01-02','2022-01-01','hydrocortisone');

--Details of drug stock Available
-- ******************
--  Stock : id
-- Drug: Drug id
--   Available: location, quantity

INSERT INTO Drug_Stock(id,drugid,location,quantity) VALUES
(1,1,'Coimbatore',20000),
(2,1,'Chennai',200000),
(3,2,'Coimbatore',10000);
INSERT INTO Drug_Stock(id,drugid,location,quantity) VALUES
(4,2,'Chennai',25000),
(5,3,'Chennai',22000),
(6,4,'Coimbatore',11200),
(7,4,'Chennai',20200),
(8,5,'Chennai',20020),
(9,6,'Chennai',20060),
(10,6,'Coimbatore',27000),
(11,7,'Coimbatore',20800),
(12,8,'Chennai',10000);
INSERT INTO Drug_Stock(id,drugid,location,quantity) VALUES
(13,8,'Coimbatore',20000),
(14,9,'Chennai',12000),
(15,10,'Coimbatore',22200),
(16,10,'Chennai',200040),
(17,11,'Chennai',23000),
(18,12,'Coimbatore',24700),
(19,13,'Chennai',20000);
INSERT INTO Drug_Stock(id,drugid,location,quantity) VALUES
(20,13,'Coimbatore',122000),
(21,14,'Coimbatore',12000),
(22,14,'Chennai',20000),
(23,15,'Coimbatore',20200),
(24,16,'Chennai',20001),
(25,17,'Coimbatore',20120),
(26,18,'Coimbatore',20000),
(27,18,'Chennai',200560);
INSERT INTO Drug_Stock(id,drugid,location,quantity) VALUES
(28,19,'Goa',20670),
(29,20,'Mumbai',20000),
(30,20,'Bangalore',20880);
