package com.jxt.dataspeed.mapper;

import com.jxt.dataspeed.pojo.domain.Player;
import com.jxt.dataspeed.pojo.domain.Team;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PlayerMapper {

    int savePlayer(Player player);
    int deletePlayer(double playerId);
    int updatePlayer(Player player);
    List<Player> listPlayers();
    List<Player> listPlayersByTeamId(int teamId);
    List<Player> listPlayersByParams(@Param("type") String type,
                                     @Param("pet") String pet,
                                     @Param("ecu") String ecu);
    Player getPlayerById(double playerId);
    Player getPlayerByName(String playerName);
    int increasePlayerPower(double playerId);

    int saveTeam(Team team);
    int deleteTeam(int teamId);
    int updateTeam(Team team);
    List<Team> listTeams();
    Team getTeamByPlayerId(double playerId);
    List<Team> getTeamById(int teamId);
    Team getTeamByName(String teamName);
    int increaseTeamPower(int teamId);
}
