module.exports = {
  publicPath: '/${app_name}/',
  
  devServer: {
    proxy: {
${proxy_settings}
    }},
    configureWebpack: {
      module: {
        rules: [
          {
            test: /.html$/,
            loader: "vue-template-loader",
            exclude: /index.html/
          }
        ]
      }
    },
  "transpileDependencies": [
    "vuetify"
  ]
}