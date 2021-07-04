package com.jxt.dataspeed;

import com.jxt.dataspeed.mapper.CarMapper;
import com.jxt.dataspeed.mapper.PetMapper;
import com.jxt.dataspeed.mapper.RecordMapper;
import com.jxt.dataspeed.mapper.TrackMapper;
import com.jxt.dataspeed.pojo.domain.Car;
import com.jxt.dataspeed.pojo.domain.Pet;
import com.jxt.dataspeed.pojo.domain.Track;
import com.jxt.dataspeed.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
class DataspeedApplicationTests {
    @Autowired
    CarMapper carMapper;
    @Autowired
    TrackMapper trackMapper;
    @Autowired
    PetMapper petMapper;
    @Autowired
    RecordMapper recordMapper;

    @Autowired
    CarService carService;
    @Autowired
    TrackService trackService;
    @Autowired
    PetService petService;
    @Autowired
    RecordService recordService;
    @Autowired
    AdminService adminService;

    @Test
    void test() throws ParseException {
        Date parse = new SimpleDateFormat("yyyy-MM-dd").parse("2021-05-03");
        //System.out.println(parse);

        //System.out.println(recordService.getTotalCount("x","withPet","withEcu",""));
        //System.out.println(recordService.listRecentRecordDTOs("x","withPet","withEcu","1","10",""));
    }
/*
    @Autowired
    CarMapper carMapper;
    @Autowired
    TrackMapper trackMapper;
    @Autowired
    PetMapper petMapper;
    @Autowired
    RecordMapper recordMapper;

    @Autowired
    CarService carService;
    @Autowired
    TrackService trackService;
    @Autowired
    PetService petService;
    @Autowired
    RecordService recordService;
    @Autowired
    AdminService adminService;

    @Test
    void test(){
        //System.out.println(carService.listCarsDTOs());
        //System.out.println(trackService.listTrackDTOs());
        System.out.println(carService.listCarWithSkinsDTOsByCarRank("A"));
    }
    @Test
    void listAll(){
        System.out.println("***************cars****************");
        List<Car> cars = carMapper.listCars();
        for (Car car : cars) {
            System.out.println(car);
        }

        System.out.println("***************tracks****************");
        List<Track> tracks = trackMapper.listTracks();
        for (Track track : tracks) {
            System.out.println(track);
        }

        System.out.println("***************tracks****************");
        List<Pet> pets = petMapper.listPets();
        for (Pet pet : pets) {
            System.out.println(pet);
        }


    }
    @Test
    void listTracks(){
        List<Track> tracks = trackMapper.listTracks();
        for (Track track : tracks) {
            System.out.println(track);
        }
    }
*//*


    @Test
    void saveRecord(){
        APetEcuRecord record = new APetEcuRecord();
        Track track = new Track();
        Car car = new Car();
        Pet pet = new Pet();
        Player player = new Player();
        Ecu ecu = new Ecu();
        track.setTrackId(88);
        car.setCarId(6);
        pet.setPetId(7);
        player.setPlayerId(1);
        ecu.setEcuId(3);
        record.setTrack(track);
        record.setPlayer(player);
        record.setPet(pet);
        record.setCar(car);
        record.setEcu(ecu);
        record.setRecordDate(new Date());
        record.setUpdateDatetime(new Date());
        record.setRecordTimeSecond(80);
        record.setRecordTimeMillisecond(60);
        recordMapper.saveAPetEcuRecord(record);
    }*//*

    *//**
     * 读取json文件，返回json串
     * @param fileName
     * @return
     *//*
    public static String readJsonFile(String fileName) {
        String jsonStr = "";
        try {
            File jsonFile = new File(fileName);
            FileReader fileReader = new FileReader(jsonFile);

            Reader reader = new InputStreamReader(new FileInputStream(jsonFile),"utf-8");
            int ch = 0;
            StringBuffer sb = new StringBuffer();
            while ((ch = reader.read()) != -1) {
                sb.append((char) ch);
            }
            fileReader.close();
            reader.close();
            jsonStr = sb.toString();
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    *//**
     * 从json文件添加车
     *//*
    @Test
    void addCars() {
        HashSet<Car> cars = new HashSet<>();
        String path = DataspeedApplicationTests.class.getClassLoader().getResource("list.json").getPath();
        String s = readJsonFile(path);
        JSONArray objects = JSON.parseArray(s);
        for (int i = 0; i < objects.size(); i++) {
            JSONObject key1 = (JSONObject)objects.get(i);
            String name = (String)key1.get("cm_4e");
            String rank = (String)key1.get("jb_43");
            Car car = new Car();
            car.setCarName(name);
            car.setCarRank(rank);
            cars.add(car);
            System.out.println(rank+"---"+name);
        }
    }

    *//**
     * 从json文件添加赛道
     *//*
    *//*@Test
    void addTracks() {
        HashSet<Track> tracks = new HashSet<>();
        String path = DataspeedApplicationTests.class.getClassLoader().getResource("listTracks.json").getPath();
        String s = readJsonFile(path);
        JSONArray objects = JSON.parseArray(s);
        for (int i = 0; i < objects.size(); i++) {
            JSONObject key1 = (JSONObject)objects.get(i);
            String name = (String)key1.get("dtm_88");
            int stars = Integer.parseInt((String)key1.get("jytg_a3"));
            Track track = new Track();
            track.setTrackName(name);
            track.setTrackStars(stars);
            tracks.add(track);
            System.out.println(stars+"---"+name);
        }
        for (Track track : tracks) {
            trackMapper.saveTrack(track);
        }
    }

    *//**//**
     * 从json文件添加宠物
     *//**//*
    @Test
    void addPets() {
        HashSet<Pet> pets = new HashSet<>();
        String path = DataspeedApplicationTests.class.getClassLoader().getResource("listPets.json").getPath();
        String s = readJsonFile(path);
        JSONArray objects = JSON.parseArray(s);
        for (int i = 0; i < objects.size(); i++) {
            JSONObject key1 = (JSONObject)objects.get(i);
            String name = (String)key1.get("mc_77");
            Pet pet = new Pet();
            pet.setPetName(name);
            pets.add(pet);
            System.out.println(name);
        }
        for (Pet pet : pets) {
            petMapper.savePet(pet);
        }
    }*/

}
