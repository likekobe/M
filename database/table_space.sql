spool tablespace.log
prompt
prompt Creating temporary tablespace ecms_temporary
prompt =============================
prompt


CREATE  TEMPORARY TABLESPACE ECMS_TEMP TEMPFILE 
'/home/oracle/app/oracle/oradata/ecms/ECMS_TEMP.dbf' 
SIZE 1024M AUTOEXTEND ON NEXT 1024M MAXSIZE UNLIMITED EXTENT MANAGEMENT LOCAL UNIFORM SIZE 1M;




prompt
prompt Creating tablespace ecms_data1
prompt =============================
prompt



CREATE SMALLFILE TABLESPACE ECMS_DATA1 DATAFILE '/home/oracle/app/oracle/oradata/ecms/ECMS_DATA1.dbf' 
SIZE 20480M AUTOEXTEND ON NEXT 1024M MAXSIZE UNLIMITED LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO ;

prompt
prompt Creating tablespace ecms_index 
prompt =============================
prompt



CREATE  TABLESPACE ECMS_INDEX
DATAFILE '/home/oracle/app/oracle/oradata/ecms/ECMS_INDEX.dbf' 
SIZE 2048M AUTOEXTEND ON NEXT 1024M MAXSIZE UNLIMITED LOGGING EXTENT MANAGEMENT LOCAL SEGMENT SPACE MANAGEMENT AUTO ;






spool off

