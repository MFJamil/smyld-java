module.exports = {
  publicPath: '/RatingAnzeigeV1',
  devServer: {
    proxy: {
      '^/Authenticate': {
        target: 'http://localhost:9080/Recherche/',
        ws: true,
        changeOrigin: true
        },
      '^/KfwRest': {
          target: 'http://localhost:9080/Recherche/',
          ws: true,
          changeOrigin: true
          },
      '^/RatingAnzeigeRest': {
        target: 'http://localhost:9080/Recherche/',
        ws: true,
        changeOrigin: true
        },
  
      '^/rech': {
        target: 'http://localhost:9080/Recherche/KfwFormularServer?APPLICATIONOBJEKT=Recherche&CLIENT_ACTION=InitRecherche&SC_RECHERCHE_TYPE=HauptGeschaeftspartner&SC_HGPNAME_VALUE=Test&SC_RETURN_MODE=ReturnFromModalDialog&SC_MISC',
        ws: true,
        changeOrigin: true
        }
  
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