const path = require('path')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const webpackCommon = require('./webpack.common')

const {merge} = require('webpack-merge')

const OUTPUT_PATH = path.resolve('..', 'demo', "src/main/resources/static/")
const OUTPUT_BUNDLE_PATH = 'bundle.js'
const OUTPUT_HTML_PATH = 'index.html'
const TEMPLATE = './public/index.html'

module.exports = merge(webpackCommon, {
  mode: 'production',
  devtool: 'eval-cheap-module-source-map',
  output: {
    path: OUTPUT_PATH,
    filename: OUTPUT_BUNDLE_PATH,
  },
  plugins: [
    new HtmlWebpackPlugin({
      template: TEMPLATE,
      filename: OUTPUT_HTML_PATH,
      publicPath: '/',
    }),
  ],
  optimization: {
    minimize: true,
  },
  performance: {
    hints: false,
    maxEntrypointSize: 512000,
    maxAssetSize: 512000,
  },
})
