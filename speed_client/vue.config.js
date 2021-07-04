const path = require("path");
const webpack = require("webpack");
module.exports = {
  pluginOptions: {
    "style-resources-loader": {
      preProcessor: "less",
      patterns: [path.resolve(__dirname, "./src/assets/less/common.less")],
    },
  },
  publicPath: "./",
  devServer: {
    host: "0.0.0.0",
    hot: true,
    proxy: {
      "/api": {
        target: "http://localhost:8088", //后端接口地址
        changeOrigin: true, //是否允许跨越
      },
    },
  },
};
