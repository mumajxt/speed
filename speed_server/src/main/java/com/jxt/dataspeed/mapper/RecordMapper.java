package com.jxt.dataspeed.mapper;

import com.jxt.dataspeed.pojo.domain.Record;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface RecordMapper {
    
    int saveRecord(Record record);
    int deleteRecord(double recordId);
    int updateRecord(Record record);
    List<Record> listRecords();
    Record getRecordById(double recordId);
    List<Record> listRecordsByParams(Map params);
    List<Record> listRecentRecordsByParams(@Param("type") String type,
                                           @Param("pet") String pet,
                                           @Param("ecu") String ecu,
                                           @Param("start") int start,
                                           @Param("size") int size,
                                           @Param("searchKey") String searchKey,
                                           @Param("trackId") Integer trackId,
                                           @Param("playerId") Integer playerId,
                                           @Param("carId") Integer carId,
                                           @Param("orderBy") String orderBy);
    int getTotalCount(@Param("type") String type,
                         @Param("pet") String pet,
                         @Param("ecu") String ecu,
                         @Param("searchKey") String searchKey,

                          @Param("trackId") Integer trackId,
                          @Param("playerId") Integer playerId,
                          @Param("carId") Integer carId);


}
