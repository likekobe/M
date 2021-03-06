drop index UIDX_BS_INC_ELEC_MONITOR ;
create index UIDX_BS_INC_ELEC_MONITOR on BS_INCREMENT_DATA (ELEC_METER_ID, DATA_TIME, DATA_TYPE)
  tablespace ECMS_INDEX;

drop index UIDX_BS_INC_WATER_MONITOR ;
create index UIDX_BS_INC_WATER_MONITOR on BS_INCREMENT_DATA (WATER_METER_ID, DATA_TIME, DATA_TYPE)
  tablespace ECMS_INDEX;

drop index UIDX_BS_TRE_E_DATE_VALUE_TY;
create index UIDX_BS_TRE_E_DATE_VALUE_TY on BS_TREATEDDATA (ELEC_METER_ID, TREATE_DATE, TOTAL_USED, ERROR_TYPE)
  tablespace ECMS_INDEX;

drop index UIDX_BS_TRE_W_DATE_VALUE_TY;
create index UIDX_BS_TRE_W_DATE_VALUE_TY on BS_TREATEDDATA (WATER_METER_ID, TREATE_DATE, TOTAL_USED, ERROR_TYPE)
  tablespace ECMS_INDEX;

drop index UIDX_BS_TRE_WATER_DATE_VALUE;
create index UIDX_BS_TRE_WATER_DATE_VALUE on BS_TREATEDDATA (WATER_METER_ID, TREATE_DATE, TOTAL_USED)
  tablespace ECMS_INDEX;

drop index UIDX_BS_TRE_ELEC_DATE_VALUE;
create index UIDX_BS_TRE_ELEC_DATE_VALUE on BS_TREATEDDATA (ELEC_METER_ID, TREATE_DATE, TOTAL_USED)
  tablespace ECMS_INDEX;