(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-b3f9644e"],{"45fc":function(t,s,a){"use strict";var i=a("23e7"),e=a("b727").some,c=a("a640"),n=a("ae40"),o=c("some"),r=n("some");i({target:"Array",proto:!0,forced:!o||!r},{some:function(t){return e(this,t,arguments.length>1?arguments[1]:void 0)}})},"63aa":function(t,s,a){},a362:function(t,s,a){"use strict";a.r(s);var i=function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"records-container",attrs:{id:"view_statistics"}},[a("div",{staticClass:"short_info"},[t.loading?a("p",[a("Loading",[t._v("加载中...")])],1):a("p",[t.recordsOut.length>0?a("span",[t.searchKey?a("span",[t._v("搜索到")]):a("span",[t._v("在榜")]),t.playerStatistics.length>0?a("span",[a("span",{staticClass:"yellow",domProps:{textContent:t._s(t.playerStatistics.length)}}),t._v("位选手")]):t._e(),t.carStatistics.length>0?a("span",[t._v("，"),a("span",{staticClass:"yellow",domProps:{textContent:t._s(t.carStatistics.length)}}),t._v("辆赛车")]):t._e(),t.petStatistics.length>0?a("span",[t._v("，"),a("span",{staticClass:"yellow",domProps:{textContent:t._s(t.petStatistics.length)}}),t._v("只宠物")]):t._e(),t.ecuStatistics.length>0?a("span",[t._v("，"),a("span",{staticClass:"yellow",domProps:{textContent:t._s(t.ecuStatistics.length)}}),t._v("个ECU")]):t._e()]):a("span",[t._v("暂无记录~")])])]),t.playerStatistics.length>0?a("div",{staticClass:"statistic card"},[t._m(0),a("div",{staticClass:"statistic-list"},t._l(t.playerStatistics,(function(s,i){return a("div",{key:s.player.id,staticClass:"statistic-list-item",class:t.playerStatistics[i].isShow?"show":""},[a("div",{staticClass:"statistic-list-item-info",on:{click:function(s){t.playerStatistics[i].isShow=!t.playerStatistics[i].isShow}}},[a("span",{staticClass:"player",domProps:{textContent:t._s(s.player.name)}}),a("span",{staticClass:"persent",domProps:{textContent:t._s(t.$formatToPercent(s.records.length/t.recordsOut.length))}}),a("div",{staticClass:"blank"},[a("div",{staticClass:"bar",style:{flex:s.records.length}}),a("div",{staticClass:"bar_blank",style:{flex:t.playerStatistics[0].records.length-s.records.length}})]),a("span",{staticClass:"count",domProps:{textContent:t._s(s.records.length)}}),a("span",{staticClass:"icon iconfont iconxiala",class:t.playerStatistics[i].isShow?"":"rotate-90"})]),t.playerStatistics[i].isShow?a("div",{staticClass:"statistic-list-item-detail"},[a("div",{staticClass:"table"},t._l(s.records,(function(s){return a("div",{key:s.id,staticClass:"tr",on:{click:function(a){return t.$emit("showDetail",s)}}},[a("div",{staticClass:"track_name",domProps:{textContent:t._s(s.track.name)}},[t._v("地图")]),a("div",{staticClass:"time yellow",class:{url:s.videoUrl},domProps:{textContent:t._s(s.time)}},[t._v("记录")]),a("div",{staticClass:"car_name",domProps:{textContent:t._s(s.car.name)}},[t._v("赛车")]),a("div",{staticClass:"pet_name"},[s.pet?a("span",{domProps:{textContent:t._s(s.pet.name)}}):a("span",{staticClass:"grey2"},[t._v("无宠")])]),a("div",{staticClass:"ecu_name"},[s.ecu?a("span",{domProps:{textContent:t._s(s.ecu.name)}}):a("span",{staticClass:"grey2"},[t._v("无E")])]),a("div",{staticClass:"date",domProps:{textContent:t._s(s.date)}},[t._v("日期")])])})),0)]):t._e()])})),0)]):t._e(),t.carStatistics.length>0?a("div",{staticClass:"statistic card"},[t._m(1),a("div",{staticClass:"statistic-list"},t._l(t.carStatistics,(function(s,i){return a("div",{key:s.car.id,staticClass:"statistic-list-item",class:t.carStatistics[i].isShow?"show":""},[a("div",{staticClass:"statistic-list-item-info",on:{click:function(s){t.carStatistics[i].isShow=!t.carStatistics[i].isShow}}},[a("span",{staticClass:"car",domProps:{textContent:t._s(s.car.name)}}),a("span",{staticClass:"persent",domProps:{textContent:t._s(t.$formatToPercent(s.records.length/t.recordsOut.length))}}),a("div",{staticClass:"blank"},[a("div",{staticClass:"bar",style:{flex:s.records.length}}),a("div",{staticClass:"bar_blank",style:{flex:t.carStatistics[0].records.length-s.records.length}})]),a("span",{staticClass:"count",domProps:{textContent:t._s(s.records.length)}}),a("span",{staticClass:"icon iconfont iconxiala",class:t.carStatistics[i].isShow?"":"rotate-90"})]),t.carStatistics[i].isShow?a("div",{staticClass:"statistic-list-item-detail"},[a("div",{staticClass:"table"},t._l(s.records,(function(s){return a("div",{key:s.id,staticClass:"tr",on:{click:function(a){return t.$emit("showDetail",s)}}},[a("div",{staticClass:"track_name",domProps:{textContent:t._s(s.track.name)}},[t._v("地图")]),a("div",{staticClass:"time yellow",class:{url:s.videoUrl},domProps:{textContent:t._s(s.time)}},[t._v("记录")]),a("div",{staticClass:"player_name",domProps:{textContent:t._s(s.player.name)}},[t._v("车手")]),a("div",{staticClass:"pet_name"},[s.pet?a("span",{domProps:{textContent:t._s(s.pet.name)}}):a("span",{staticClass:"grey2"},[t._v("无宠")])]),a("div",{staticClass:"ecu_name"},[s.ecu?a("span",{domProps:{textContent:t._s(s.ecu.name)}}):a("span",{staticClass:"grey2"},[t._v("无E")])]),a("div",{staticClass:"date",domProps:{textContent:t._s(s.date)}},[t._v("日期")])])})),0)]):t._e()])})),0)]):t._e(),t.petStatistics.length>0?a("div",{staticClass:"statistic card"},[t._m(2),a("div",{staticClass:"statistic-list"},t._l(t.petStatistics,(function(s,i){return a("div",{key:s.pet.id,staticClass:"statistic-list-item",class:t.petStatistics[i].isShow?"show":""},[a("div",{staticClass:"statistic-list-item-info",on:{click:function(s){t.petStatistics[i].isShow=!t.petStatistics[i].isShow}}},[a("span",{staticClass:"pet",domProps:{textContent:t._s(s.pet.name)}}),a("span",{staticClass:"persent",domProps:{textContent:t._s(t.$formatToPercent(s.records.length/t.recordsOut.length))}}),a("div",{staticClass:"blank"},[a("div",{staticClass:"bar",style:{flex:s.records.length}}),a("div",{staticClass:"bar_blank",style:{flex:t.petStatistics[0].records.length-s.records.length}})]),a("span",{staticClass:"count",domProps:{textContent:t._s(s.records.length)}}),a("span",{staticClass:"icon iconfont iconxiala",class:t.petStatistics[i].isShow?"":"rotate-90"})]),t.petStatistics[i].isShow?a("div",{staticClass:"statistic-list-item-detail"},[a("div",{staticClass:"table"},t._l(s.records,(function(s){return a("div",{key:s.id,staticClass:"tr",on:{click:function(a){return t.$emit("showDetail",s)}}},[a("div",{staticClass:"track_name",domProps:{textContent:t._s(s.track.name)}},[t._v("地图")]),a("div",{staticClass:"time yellow",class:{url:s.videoUrl},domProps:{textContent:t._s(s.time)}},[t._v("记录")]),a("div",{staticClass:"player_name",domProps:{textContent:t._s(s.player.name)}},[t._v("车手")]),a("div",{staticClass:"car_name",domProps:{textContent:t._s(s.car.name)}},[t._v("赛车")]),a("div",{staticClass:"ecu_name"},[s.ecu?a("span",{domProps:{textContent:t._s(s.ecu.name)}}):a("span",{staticClass:"grey2"},[t._v("无E")])]),a("div",{staticClass:"date",domProps:{textContent:t._s(s.date)}},[t._v("日期")])])})),0)]):t._e()])})),0)]):t._e(),t.ecuStatistics.length>0?a("div",{staticClass:"statistic card"},[t._m(3),a("div",{staticClass:"statistic-list"},t._l(t.ecuStatistics,(function(s,i){return a("div",{key:s.ecu.id,staticClass:"statistic-list-item",class:t.ecuStatistics[i].isShow?"show":""},[a("div",{staticClass:"statistic-list-item-info",on:{click:function(s){t.ecuStatistics[i].isShow=!t.ecuStatistics[i].isShow}}},[a("span",{staticClass:"ecu",domProps:{textContent:t._s(s.ecu.name)}}),a("span",{staticClass:"persent",domProps:{textContent:t._s(t.$formatToPercent(s.records.length/t.recordsOut.length))}}),a("div",{staticClass:"blank"},[a("div",{staticClass:"bar",style:{flex:s.records.length}}),a("div",{staticClass:"bar_blank",style:{flex:t.ecuStatistics[0].records.length-s.records.length}})]),a("span",{staticClass:"count",domProps:{textContent:t._s(s.records.length)}}),a("span",{staticClass:"icon iconfont iconxiala",class:t.ecuStatistics[i].isShow?"":"rotate-90"})]),t.ecuStatistics[i].isShow?a("div",{staticClass:"statistic-list-item-detail"},[a("div",{staticClass:"table"},t._l(s.records,(function(s){return a("div",{key:s.id,staticClass:"tr",on:{click:function(a){return t.$emit("showDetail",s)}}},[a("div",{staticClass:"track_name",domProps:{textContent:t._s(s.track.name)}},[t._v("地图")]),a("div",{staticClass:"time yellow",class:{url:s.videoUrl},domProps:{textContent:t._s(s.time)}},[t._v("记录")]),a("div",{staticClass:"player_name",domProps:{textContent:t._s(s.player.name)}},[t._v("车手")]),a("div",{staticClass:"car_name",domProps:{textContent:t._s(s.car.name)}},[t._v("赛车")]),a("div",{staticClass:"pet_name"},[s.pet?a("span",{domProps:{textContent:t._s(s.pet.name)}}):a("span",{staticClass:"grey2"},[t._v("无宠")])]),a("div",{staticClass:"date",domProps:{textContent:t._s(s.date)}},[t._v("日期")])])})),0)]):t._e()])})),0)]):t._e()])},e=[function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"statistic-header"},[a("h3",[a("span",{staticClass:"icon iconfont iconleft yellow"}),a("span",{staticClass:"header_name"},[t._v("车手"),a("span",{staticClass:"yellow"},[t._v("TOP")]),t._v("榜")]),a("span",{staticClass:"icon iconfont iconright yellow"})])])},function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"statistic-header"},[a("h3",[a("span",{staticClass:"icon iconfont iconleft yellow"}),a("span",{staticClass:"header_name"},[t._v("赛车"),a("span",{staticClass:"yellow"},[t._v("TOP")]),t._v("榜")]),a("span",{staticClass:"icon iconfont iconright yellow"})])])},function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"statistic-header"},[a("h3",[a("span",{staticClass:"icon iconfont iconleft yellow"}),a("span",{staticClass:"header_name"},[t._v("宠物"),a("span",{staticClass:"yellow"},[t._v("TOP")]),t._v("榜")]),a("span",{staticClass:"icon iconfont iconright yellow"})])])},function(){var t=this,s=t.$createElement,a=t._self._c||s;return a("div",{staticClass:"statistic-header"},[a("h3",[a("span",{staticClass:"icon iconfont iconleft yellow"}),a("span",{staticClass:"header_name"},[t._v("ECU"),a("span",{staticClass:"yellow"},[t._v("TOP")]),t._v("榜")]),a("span",{staticClass:"icon iconfont iconright yellow"})])])}],c=(a("4de4"),a("4160"),a("45fc"),a("b0c0"),a("4d63"),a("ac1f"),a("25f0"),a("159b"),a("3a5e")),n={name:"ViewStatistics",components:{Loading:c["default"]},props:{recordsOut:Array,searchKey:String,type:String,pet:String,ecu:String,loading:Boolean},created:function(){},mounted:function(){},watch:{filteredRecords:{handler:function(t){var s=this;this.playerStatistics=[],this.carStatistics=[],this.petStatistics=[],this.ecuStatistics=[],t.forEach((function(t){var a=-1,i=s.playerStatistics.some((function(s,i){if(t.player.id==s.player.id)return a=i,!0}));i?s.playerStatistics[a].records.push(t):s.playerStatistics.push({player:t.player,records:[t],isShow:!1});var e=-1,c=s.carStatistics.some((function(s,a){if(t.car.id==s.car.id)return e=a,!0}));if(c?s.carStatistics[e].records.push(t):s.carStatistics.push({car:t.car,records:[t],isShow:!1}),t.pet){var n=-1,o=s.petStatistics.some((function(s,a){if(t.pet.id==s.pet.id)return n=a,!0}));o?s.petStatistics[n].records.push(t):s.petStatistics.push({pet:t.pet,records:[t],isShow:!1})}if(t.ecu){var r=-1,l=s.ecuStatistics.some((function(s,a){if(t.ecu.id==s.ecu.id)return r=a,!0}));l?s.ecuStatistics[r].records.push(t):s.ecuStatistics.push({ecu:t.ecu,records:[t],isShow:!1})}})),this.playerStatistics.sort((function(t,s){return s.records.length-t.records.length})),this.playerStatistics.forEach((function(t){t.records.sort((function(t,s){return s.date>t.date}))})),this.carStatistics.sort((function(t,s){return s.records.length-t.records.length})),this.carStatistics.forEach((function(t){t.records.sort((function(t,s){return s.date>t.date}))})),this.petStatistics.sort((function(t,s){return s.records.length-t.records.length})),this.petStatistics.forEach((function(t){t.records.sort((function(t,s){return s.date>t.date}))})),this.ecuStatistics.sort((function(t,s){return s.records.length-t.records.length})),this.ecuStatistics.forEach((function(t){t.records.sort((function(t,s){return s.date>t.date}))}))},immediate:!0}},computed:{filteredRecords:function(){var t=new RegExp(this.searchKey,"i");return this.recordsOut.filter((function(s){return t.test(s.track.name)||t.test(s.player.name)||t.test(s.car.name)||!!s.pet&&t.test(s.pet.name)||!!s.ecu&&t.test(s.ecu.name)}))}},data:function(){return{playerStatistics:[],carStatistics:[],petStatistics:[],ecuStatistics:[]}},methods:{}},o=n,r=(a("dbe1"),a("2877")),l=Object(r["a"])(o,i,e,!1,null,"73d21a0c",null);s["default"]=l.exports},dbe1:function(t,s,a){"use strict";a("63aa")}}]);
//# sourceMappingURL=chunk-b3f9644e.e60955c0.js.map