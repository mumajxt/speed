package com.jxt.dataspeed.service;

import com.jxt.dataspeed.mapper.TrackMapper;
import com.jxt.dataspeed.pojo.domain.Track;
import com.jxt.dataspeed.pojo.dto.TrackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TrackService {

    @Autowired
    private TrackMapper trackMapper;


    /**
     * 增
     */
    public Integer saveTrack(Track track) {
        int i = trackMapper.saveTrack(track);
        if (i == 1) {
            return track.getId();
        } else {
            return null;
        }
    }

    public Integer saveTrackDTO(TrackDTO trackDTO) {
        Track track = getTrack(trackDTO);
        if (track == null) return null;
        int i = trackMapper.saveTrack(track);
        if (i == 1) {
            return track.getId();
        } else {
            return null;
        }
    }

    /**
     * 删
     */
    public boolean deleteTrack(int id) {
        return trackMapper.deleteTrack(id) > 0;
    }

    /**
     * 改
     */

    public boolean updateTrackDTO(TrackDTO trackDTO) {
        Track track = getTrack(trackDTO);
        if (track == null) return false;
        return trackMapper.updateTrack(track) > 0;
    }

    /**
     * 查
     */
    public List<TrackDTO> listTrackDTOs() {
        List<TrackDTO> trackDTOs = new LinkedList<>();
        List<Track> tracks = trackMapper.listTracks();
        for (Track track : tracks) {
            trackDTOs.add(getTrackDTO(track));
        }
        return trackDTOs;
    }

    public List<TrackDTO> listTrackDTOsByParams(String type, String pet, String ecu) {
        List<TrackDTO> trackDTOs = new LinkedList<>();
        List<Track> tracks = trackMapper.listTracksByParams(type, pet, ecu);
        for (Track track : tracks) {
            trackDTOs.add(getTrackDTO(track));
        }
        return trackDTOs;
    }


    /**
     * do转dto
     */
    public TrackDTO getTrackDTO(int trackId) {
        Track track = trackMapper.getTrackById(trackId);
        return getTrackDTO(track);
    }

    public TrackDTO getTrackDTO(Track track) {
        if (track == null) return null;
        TrackDTO trackDTO = new TrackDTO();
        trackDTO.setId(track.getId());
        trackDTO.setName(track.getName());
        trackDTO.setStars(track.getStars());
        trackDTO.setIsLeague(track.getIsLeague());
        trackDTO.setIsNew(track.getIsNew());
        trackDTO.setIsOld(track.getIsOld());
        trackDTO.setIsSea(track.getIsSea());
        trackDTO.setDateLong(track.getDate().getTime());
        trackDTO.setPower(track.getPower());
        Date date = track.getDate();
        if (date != null) {
            trackDTO.setDate(new SimpleDateFormat("yyyy-MM-dd").format(date));
        }
        boolean trackOld = track.getIsOld();
        boolean trackSea = track.getIsSea();
        boolean trackNew = track.getIsNew();
        if (trackNew) {
            trackDTO.setLabel("新图");
            trackDTO.setKey("news");
        } else if (trackOld) {
            trackDTO.setLabel("怀旧图");
            trackDTO.setKey("olds");
        } else if (trackSea) {
            trackDTO.setLabel("航海图");
            trackDTO.setKey("seas");
        } else {
            int stars = track.getStars();
            if (stars == 99) {
                trackDTO.setLabel("拉力图");
                trackDTO.setKey("s99s");
            } else {
                trackDTO.setLabel(stars + "星图");
                trackDTO.setKey("s" + stars + "s");
            }

        }
        return trackDTO;
    }

    /**
     * dto转do
     */
    public Track getTrack(TrackDTO trackDTO) {

        Track track = new Track();
        Integer id = trackDTO.getId();
        String name = trackDTO.getName();
        Integer stars = trackDTO.getStars();
        Boolean isLeague = trackDTO.getIsLeague();
        Boolean isNew = trackDTO.getIsNew();
        Boolean isOld = trackDTO.getIsOld();
        Boolean isSea = trackDTO.getIsSea();
        String dateStr = trackDTO.getDate();

        track.setId(id);
        track.setName(name);
        track.setStars(stars);
        track.setIsLeague(isLeague);
        track.setIsNew(isNew);
        track.setIsOld(isOld);
        track.setIsSea(isSea);
        track.setPower(trackDTO.getPower());

        try {
            track.setDate(new SimpleDateFormat("yyyy-MM-dd").parse(dateStr));
        } catch (ParseException e) {
            //System.out.println("解析日期出错");
            return null;
        }

        return track;
    }

    /**
     * 获取标签权重
     *
     * @param label
     * @return
     */
    public int getPower(String label) {
        switch (label) {
            case "新图":
                return 1;
            case "7星图":
                return 2;
            case "6星图":
                return 3;
            case "5星图":
                return 4;
            case "4星图":
                return 5;
            case "3星图":
                return 6;
            case "2星图":
                return 7;
            case "1星图":
                return 8;
            case "拉力图":
                return 9;
            case "航海图":
                return 10;
            case "怀旧图":
                return 11;
            default:
                return 100;
        }
    }
}

