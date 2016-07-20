
create table T_BD_BuildBaseInfo
(
  F_BuildID          	CHAR(10) not null,
  F_DataCenterID  	CHAR(6) not null,
  F_BuildName 		VARCHAR2(48) not null,
  F_AliasName        	VARCHAR2(16),
  F_BuildOwner       	VARCHAR2(80),
  F_State		NUMBER(1),
  F_DistrictCode	CHAR(6),
  F_BuildAddr		VARCHAR2(80),
  F_BuildLong           NUMBER(18,4),
  F_BuildLat		NUMBER(18,4),
  F_BuildYear		NUMBER(4),
  F_UpFloor		NUMBER(3),
  F_DownFloor		NUMBER(3),	
  F_BuildFunc		CHAR(1),
  F_TotalArea		NUMBER(18,4),
  F_AirArea		NUMBER(18,4),
  F_HeatArea		NUMBER(18,4),
  F_AirType		CHAR(1),
  F_HeatType		CHAR(1),
  F_BodyCoef		NUMBER(18,4),
  F_StruType		CHAR(1),
  F_WallMatType		CHAR(1),
  F_WallWarmType	CHAR(1),
  F_WallWinType	        CHAR(1),
  F_GlassType		CHAR(1),
  F_WinFrameType 	CHAR(1),		
  F_IsStandard		VARCHAR2(5),
  F_DesignDept		VARCHAR2(64),
  F_WorkDept		VARCHAR2(64),
  F_CreateTime		VARCHAR2(19),
  F_CreateUser		VARCHAR2(32),
  F_MonitorDate		VARCHAR2(19),
  F_AcceptDate	        VARCHAR2(19)
)
;
alter table T_BD_BuildBaseInfo
  add constraint PK_F_BuildID primary key (F_BuildID);

create table T_AD_Machine
(
  F_MachineID	CHAR(32),
  F_BuildID	CHAR(10),
  F_lay		VARCHAR2(20),
  F_num		VARCHAR2(32),
  F_xh	        Char(32),
  F_mark	VARCHAR2(20),
  F_EnergyItemCode   Char(5),
  F_buildnum	VARCHAR2(20),
  F_type	VARCHAR2(20)
);

alter table T_AD_Machine
  add constraint PK_F_MachineID primary key (F_MachineID);


ALTER TABLE T_AD_Machine ADD CONSTRAINT FK_F_BuildID
    FOREIGN KEY (F_BuildID) REFERENCES T_BD_BuildBaseInfo (F_BuildID);