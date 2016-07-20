alter table US_ELECMETER_INFO    add  IS_VALUE            CHAR(1);
alter table US_ZWJ               add  IS_VALUE            CHAR(1);
alter table US_OLD_METER_INFO    add  CHANGE_VALUE_TIME   CHAR(19);
alter table US_OLD_METER_INFO    add  ZWJ_ID			  CHAR(32);
