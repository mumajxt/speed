package com.jxt.dataspeed.service;

import com.jxt.dataspeed.mapper.PetMapper;
import com.jxt.dataspeed.pojo.domain.*;
import com.jxt.dataspeed.pojo.domain.Pet;
import com.jxt.dataspeed.pojo.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetMapper petMapper;

    /**
     增
     */
    public Integer savePet(Pet pet) {
        int i = petMapper.savePet(pet);
        if (i==1){
            return pet.getId();
        }else {
            return null;
        }
    }
    public Integer savePetSkin(PetSkin petSkin){
        int i = petMapper.savePetSkin(petSkin);
        if (i==1){
            return petSkin.getId();
        }else {
            return null;
        }
    }


    /**
     * 删
     */
    public boolean deletePet(int id) {
        return petMapper.deletePet(id)>0;
    }

    public boolean deletePetSkin(int id) {
        return petMapper.deletePetSkin(id)>0;
    }


    /**
     * 改
     */
    public boolean updatePet(Pet pet) {
        return petMapper.updatePet(pet)>0;
    }
    public boolean updatePetSkin(PetSkin petSkin) {
        return petMapper.updatePetSkin(petSkin)>0;
    }

    /**
     * 查
     */
    public List<PetDTO> listPetDTOs() {
        List<PetDTO> petDTOs = new LinkedList<>();
        List<Pet> pets = petMapper.listPets();
        for (Pet pet : pets) {
            petDTOs.add(getPetDTO(pet));
        }
        return petDTOs;
    }
    public List<PetSkinDTO> listPetSkinDTOs(){
        List<PetSkinDTO> petSkinDTOs = new LinkedList<>();
        List<PetSkin> petSkins = petMapper.listPetSkins();
        for (PetSkin petSkin : petSkins) {
            petSkinDTOs.add(getPetSkinDTO(petSkin));
        }
        return petSkinDTOs;
    }
    public Pet getPet(Integer id){
        if (id!=null){
            return petMapper.getPetById(id);
        }
        return null;
    }
    public PetSkin getPetSkin(PetSkinDTO dto){
        PetSkin petSkin = new PetSkin();
        petSkin.setPetId(dto.getPet().getId());
        petSkin.setId(dto.getId());
        petSkin.setName(dto.getName());
        petSkin.setPower(dto.getPower());
        return petSkin;
    }
    public PetSkinDTO getPetSkinDTO(PetSkin petSkin){
        if (petSkin==null) return null;
        PetSkinDTO dto = new PetSkinDTO();
        dto.setId(petSkin.getId());
        dto.setName(petSkin.getName());
        dto.setPet(getPet(petSkin.getPetId()));
        dto.setPower(petSkin.getPower());
        return dto;
    }
    public PetSkinDTO getPetSkinDTO(Integer petSkinId){
        if (petSkinId==null) return null;
        return getPetSkinDTO(petMapper.getPetSkinById(petSkinId));
    }

    /**
     * 其他
     */
    public PetDTO getPetDTO(Integer petId){
        if (petId==null) return null;
        Pet pet = petMapper.getPetById(petId);
        return getPetDTO(pet);
    }

    /**
     * pet转petDTO
     * @param pet
     * @return
     */
    public PetDTO getPetDTO(Pet pet){
        if (pet==null) return null;
        PetDTO petDTO = new PetDTO();
        petDTO.setId(pet.getId());
        petDTO.setName(pet.getName());
        petDTO.setPower(pet.getPower());
        return petDTO;
    }
}
