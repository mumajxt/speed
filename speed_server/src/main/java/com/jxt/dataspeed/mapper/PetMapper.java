package com.jxt.dataspeed.mapper;

import com.jxt.dataspeed.pojo.domain.Pet;
import com.jxt.dataspeed.pojo.domain.PetSkin;
import com.jxt.dataspeed.pojo.domain.Pet;
import com.jxt.dataspeed.pojo.domain.PetSkin;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface PetMapper {
    /*
    宠物
     */
    int savePet(Pet pet);
    int deletePet(int petId);
    int updatePet(Pet pet);
    List<Pet> listPets();
    Pet getPetById(int petId);
    int increasePetPower(int petId);


    /*
    宠物皮肤
     */
    int savePetSkin(PetSkin petSkin);
    int deletePetSkin(int petSkinId);
    int updatePetSkin(PetSkin petSkin);
    List<PetSkin> listPetSkinsByPetId(int petId);
    List<PetSkin> listPetSkins();
    PetSkin getPetSkinById(int petSkinId);
    int increasePetSkinPower(int petSkinId);
}
