// see http://vuejs-templates.github.io/webpack for documentation.
var path = require('path')

module.exports = {
  build: {
    env: require('./prod.env'),
    index: path.resolve(__dirname, '../../index.html'),
    assetsRoot: path.resolve(__dirname, '../../'),
    assetsSubDirectory: 'static',
    assetsPublicPath: '/',
    productionSourceMap: true
  },
  dev: {
    env: require('./dev.env'),
    port: 8080,
    proxyTable: {
      "/api/*": {
        target: "http://127.0.0.1:9000",
        toProxy: true,
        prependPath: true,
        changeOrigin: true
      },
    }
  }
}
