package com.jxt.dataspeed.mapper;

import com.jxt.dataspeed.pojo.domain.Track;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TrackMapper {
    int saveTrack(Track track);
    int deleteTrack(int trackId);
    int updateTrack(Track track);
    List<Track> listTracks();
    List<Track> listTracksByParams(@Param("type") String type,
                                   @Param("pet") String pet,
                                   @Param("ecu")String ecu);
    Track getTrackById(int trackId);
    Track getTrackByName(String trackName);
    int increaseTrackPower(int trackId);


}
