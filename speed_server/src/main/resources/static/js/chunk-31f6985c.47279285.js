(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-31f6985c"],{"5f7a":function(e,t,a){"use strict";a.r(t);var s=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"mask-wrapper animate__animated",class:e.visible?"animate__fadeIn":"animate__fadeOut",on:{click:[function(t){return e.close()},function(e){e.stopPropagation()}]}},[a("div",{staticClass:"drawer animate__animated",class:e.visible?"animate__fadeInUp":"animate__fadeOutDown",on:{click:function(e){e.stopPropagation()}}},[e._m(0),a("div",{staticClass:"drawer-body"},[a("div",{staticClass:"input-wrapper"},[e._m(1),a("input",{directives:[{name:"model",rawName:"v-model",value:e.searchKey,expression:"searchKey"}],attrs:{type:"text",placeholder:"搜索"},domProps:{value:e.searchKey},on:{input:function(t){t.target.composing||(e.searchKey=t.target.value)}}}),a("span",{staticClass:"input-icon"},[e.searchKey?a("i",{staticClass:"iconfont iconclose",on:{click:function(t){e.searchKey=""}}}):e._e()])]),a("div",{staticClass:"container"},[a("div",{staticClass:"left nav"},[a("div",{staticClass:"nav-item",class:"all"==e.navSelectedItem?"nav-item-selected":"",on:{click:function(t){e.navSelectedItem="all"}}},[e._v(" 全部"),a("span",{staticClass:"count",domProps:{textContent:e._s(e.playersAll.length)}})]),a("div",{staticClass:"nav-item",class:"player"==e.navSelectedItem?"nav-item-selected":"",on:{click:function(t){e.navSelectedItem="player"}}},[e._v(" 普通车手"),a("span",{staticClass:"count",domProps:{textContent:e._s(e.players.length)}})]),a("div",{staticClass:"nav-item",class:"proPlayer"==e.navSelectedItem?"nav-item-selected":"",on:{click:function(t){e.navSelectedItem="proPlayer"}}},[e._v(" 职业车手"),a("span",{staticClass:"count",domProps:{textContent:e._s(e.proPlayers.length)}})])]),a("div",{staticClass:"right players"},e._l(e.tempPlayers,(function(t){return a("span",{key:t.id,staticClass:"label btn btn-label",class:e.playerOut&&e.playerOut.id==t.id?"btn-label-yellow":"",domProps:{textContent:e._s(t.name)},on:{click:function(a){return e.chosePlayer(t)}}})})),0)])]),a("div",{staticClass:"drawer-footer"},[a("div",{staticClass:"drawer-footer-btn drawer-footer-btn-grey",on:{click:function(t){return e.close()}}},[e._v("取消")]),a("span",{staticClass:"divider"}),a("div",{staticClass:"drawer-footer-btn drawer-footer-btn-red",on:{click:function(t){return e.chosePlayer(null)}}},[e._v("清除筛选")])])])])},n=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"drawer-header"},[a("h2",[e._v("筛选车手")])])},function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"input-icon"},[a("i",{staticClass:"iconfont iconsousuo"})])}],i=(a("4de4"),a("4160"),a("b0c0"),a("4d63"),a("ac1f"),a("25f0"),a("159b"),{name:"UserDrawerChosePlayer",props:{playerOut:Object,type:String,pet:String,ecu:String},created:function(){this.initPlayers()},watch:{type:function(){this.initPlayers()},pet:function(){this.initPlayers()},ecu:function(){this.initPlayers()},playersAll:{handler:function(e){var t=this;this.players=[],this.proPlayers=[],e.forEach((function(e){switch(e.isPro){case!1:t.players.push(e);break;case!0:t.proPlayers.push(e);break;default:break}}))},deep:!0}},computed:{playersAll:function(){var e=new RegExp(this.searchKey);return this.ps.filter((function(t){return e.test(t.name)})).sort((function(e,t){return t.power-e.power}))},tempPlayers:function(){switch(this.navSelectedItem){case"all":return this.playersAll;case"player":return this.players;case"proPlayer":return this.proPlayers;default:return[]}}},data:function(){return{visible:!0,searchKey:"",navSelectedItem:"all",loading:!1,ps:[],players:[],proPlayers:[]}},methods:{close:function(){var e=this;this.visible=!1,setTimeout((function(){e.$emit("close")}),300)},chosePlayer:function(e){this.$emit("chosePlayer",e),this.close()},initPlayers:function(){var e=this;this.loading=!0;var t={type:this.type,pet:this.pet,ecu:this.ecu};this.$axios({method:"get",url:"/api/players",params:t}).then((function(t){var a=t.data;console.log("--------------------------"),a.flag?(console.log("返回筛选车手数据↓"),console.log(a.data),e.ps=a.data):console.error(a.errorMsg),console.log("--------------------------"),e.loading=!1})).catch((function(t){console.error(t),e.loading=!1}))}}}),r=i,l=(a("6973"),a("2877")),c=Object(l["a"])(r,s,n,!1,null,"07dd9bf3",null);t["default"]=c.exports},6973:function(e,t,a){"use strict";a("ede3")},ede3:function(e,t,a){}}]);
//# sourceMappingURL=chunk-31f6985c.47279285.js.map