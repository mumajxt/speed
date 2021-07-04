package com.jxt.dataspeed.service;

import com.jxt.dataspeed.mapper.PlayerMapper;
import com.jxt.dataspeed.pojo.domain.Player;
import com.jxt.dataspeed.pojo.domain.Team;
import com.jxt.dataspeed.pojo.domain.Track;
import com.jxt.dataspeed.pojo.dto.PlayerDTO;
import com.jxt.dataspeed.pojo.dto.TeamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerMapper playerMapper;


    /**
     * do转dto
     * @param teamId
     * @return teamDTO
     */

    public TeamDTO getTeamDTO(Integer teamId){
        if (teamId!=null){
            List<Team> teams = playerMapper.getTeamById(teamId);
            if (teams!=null){
                return getTeamDTO(teams.get(0));
            }else {
                return null;
            }
        }else {
            return null;
        }


    }

    public TeamDTO getTeamDTO(Team team){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(team.getId());
        teamDTO.setName(team.getName());
        teamDTO.setIsPro(team.getIsPro());
        teamDTO.setPower(team.getPower());
        return teamDTO;
    }

    public Team getTeam(TeamDTO teamDTO){
        Team team = new Team();
        team.setId(teamDTO.getId());
        team.setName(teamDTO.getName());
        team.setIsPro(teamDTO.getIsPro());
        team.setPower(teamDTO.getPower());
        return team;
    }
    public Player getPlayer(PlayerDTO playerDTO){
        Player player = new Player();
        player.setId(playerDTO.getId());
        player.setName(playerDTO.getName());
        player.setIsPro(playerDTO.getIsPro());
        player.setTeamId(playerDTO.getTeam().getId());
        player.setPower(playerDTO.getPower());
        return player;
    }
    public Player getPlayer(double id){
        return playerMapper.getPlayerById(id);
    }

    /**
     * do转dto
     * @param player
     * @return playerDTO
     */

    public PlayerDTO getPlayerDTO(Player player){
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setId(player.getId());
        playerDTO.setName(player.getName());
        playerDTO.setTeam(getTeamDTO(player.getTeamId()));
        playerDTO.setIsPro(player.getIsPro());
        playerDTO.setPower(player.getPower());
        return playerDTO;
    }

    public PlayerDTO getPlayerDTO(Double id){
        Player player = getPlayer(id);
        return getPlayerDTO(player);
    }

    /**
     * 获取所有玩家
     * @return
     */
    public List<PlayerDTO> listPlayerDTOs(){
        List<PlayerDTO> playerDTOs = new LinkedList<>();
        List<Player> players = playerMapper.listPlayers();
        for (Player player : players) {
            playerDTOs.add(getPlayerDTO(player));
        }
        return playerDTOs;
    }

    public List<TeamDTO> listTeamDTOs() {
        List<TeamDTO> TeamDTOs = new LinkedList<>();
        List<Team> teams = playerMapper.listTeams();
        for (Team team : teams) {
            TeamDTOs.add(getTeamDTO(team));
        }
        return TeamDTOs;
    }

    public Integer saveTeamDTO(TeamDTO teamDTO) {
        Team team = getTeam(teamDTO);
        int i = playerMapper.saveTeam(team);
        if (i==1){
            return team.getId();
        }else {
            return null;
        }
    }

    public boolean updateTeamDTO(TeamDTO teamDTO) {
        if (teamDTO!=null){
            int count = playerMapper.updateTeam(getTeam(teamDTO));
            return count>0;
        }else {
            return false;
        }

    }

    public boolean deleteTeamDTO(int teamId) {
        return playerMapper.deleteTeam(teamId)>0;
    }

    public Double savePlayerDTO(PlayerDTO playerDTO) {
        Player player = getPlayer(playerDTO);
        return savePlayer(player);
    }

    public Double savePlayer(Player player) {
        double i = playerMapper.savePlayer(player);
        if (i==1){
            return player.getId();
        }else {
            return null;
        }
    }

    public boolean deletePlayer(double id) {
        return playerMapper.deletePlayer(id)>0;
    }

    public boolean updatePlayer(Player player) {
        return playerMapper.updatePlayer(player)>0;
    }

    public List<PlayerDTO> listPlayerDTOsByParams(String type, String pet, String ecu) {
        List<PlayerDTO> playerDTOs = new LinkedList<>();
        List<Player> players = playerMapper.listPlayersByParams(type,pet,ecu);
        for (Player player : players) {
            playerDTOs.add(getPlayerDTO(player));
        }
        return playerDTOs;
    }
}
