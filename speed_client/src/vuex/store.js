import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    admin: null,
    tracks: [],
    players: [],
    teams: [],
    cars: [],
    carSkins: [],
    pets: [],
    petSkins: [],
    ecus: [],
    records: [],
    recordsWithParams: [],
    recentRecordsWithParams: [],
  },
  mutations: {
    setAdmin(state, admin) {
      state.admin = admin;
    },
    setTracks(state, tracks) {
      state.tracks = tracks;
    },
    setPlayers(state, players) {
      state.players = players;
    },
    setTeams(state, teams) {
      state.teams = teams;
    },
    setCars(state, cars) {
      state.cars = cars;
    },
    setCarSkins(state, carSkins) {
      state.carSkins = carSkins;
    },
    setPets(state, pets) {
      state.pets = pets;
    },
    setPetSkins(state, petSkins) {
      state.petSkins = petSkins;
    },
    setEcus(state, ecus) {
      state.ecus = ecus;
    },
    setRecords(state, records) {
      state.records = records;
    },
    pushRecordsWithParams(state, recordsWithParam) {
      state.recordsWithParams.push(recordsWithParam);
    },
    pushRecentRecordsWithParams(state, recentRecordsWithParam) {
      state.recentRecordsWithParams.push(recentRecordsWithParam);
    },
  },
  getters: {},
  actions: {
    listTracks(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/tracks",
          withCredentials: true,
        })
          .then((response) => {
            console.log("返回地图数据↓");
            console.log(response.data);
            let info = response.data;
            if (info.flag) {
              context.commit("setTracks", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            console.log("请求赛车出错了↓");
            console.log(error);
            reject(error);
          });
      });
    },
    listPlayers(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/players",
        })
          .then((response) => {
            console.log("返回车手数据↓");

            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("setPlayers", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    listTeams(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/teams",
        })
          .then((response) => {
            let info = response.data;
            console.log("返回车队数据↓");
            console.log(info);
            if (info.flag) {
              context.commit("setTeams", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    listCars(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/cars",
        })
          .then((response) => {
            console.log("返回赛车数据↓");
            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("setCars", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    listCarSkins(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/carSkins",
        })
          .then((response) => {
            console.log("返回赛车皮肤数据↓");
            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("setCarSkins", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    listPets(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/pets",
        })
          .then((response) => {
            console.log("返回宠物数据↓");
            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("setPets", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    listPetSkins(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/petSkins",
        })
          .then((response) => {
            console.log("返回宠物皮肤数据↓");
            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("setPetSkins", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    listEcus(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/ecus",
        })
          .then((response) => {
            console.log("返回ECU数据↓");
            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("setEcus", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    listRecords(context) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/records",
        })
          .then((response) => {
            console.log("返回所有记录数据↓");
            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("setRecords", info.data);
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },

    listRecordsWithParams(context, param) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/records",
          params: param,
        })
          .then((response) => {
            console.log("返回榜单数据↓");
            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("pushRecordsWithParams", {
                param: param,
                records: info.data,
              });
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    listRecentRecordsWithParams(context, param) {
      return new Promise((resolve, reject) => {
        axios({
          method: "get",
          url: "/api/recentRecords",
          params: param,
        })
          .then((response) => {
            console.log("返回最新历史榜单数据↓");
            let info = response.data;
            console.log(info);
            if (info.flag) {
              context.commit("pushRecentRecordsWithParams", {
                param: param,
                records: info.data,
              });
              resolve(response);
            } else {
              console.log(info.errorMsg);
              reject(info.errorMsg);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
  },
});
