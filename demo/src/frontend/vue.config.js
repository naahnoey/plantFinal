const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  publicPath: '',
  outputDir: '../main/resources/static', //빌드 경로
  chainWebpack(config) { // 빌드 시 빌드되어 나오는 js파일들 js폴더 아래로 묶어 빌드한다.
    config.output.filename("js/[name].js");
  },
  transpileDependencies: true,
  devServer: {
    port: 8081
  }
})
