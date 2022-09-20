const path = require('path')
const webpackCommon = require('./webpack.common')
const {merge} = require('webpack-merge')
const HtmlWebpackPlugin = require('html-webpack-plugin')
const {CleanWebpackPlugin} = require('clean-webpack-plugin')
const CopyPlugin = require('copy-webpack-plugin')

const OUTPUT_PATH = path.resolve(__dirname, 'dist')
const OUTPUT_BUNDLE_PATH = 'bundle.js'
const TEMPLATES = './public/index.html'

module.exports = merge(webpackCommon, {
  mode: 'development',
  devtool: 'source-map',
  output: {
    path: OUTPUT_PATH,
    filename: OUTPUT_BUNDLE_PATH,
  },
  plugins: [
    new CleanWebpackPlugin(),
    new HtmlWebpackPlugin({
      template: TEMPLATES,
      publicPath: '/',
      inject: 'body',
    }),

    new CopyPlugin({patterns: [{from: 'src', to: 'src'}]}),
  ],
  devServer: {
    // contentBase: OUTPUT_PATH,
    port: 5000,
    proxy: {
      '/api': {
        target: 'http://localhost:8080',
      },
    },
    historyApiFallback: true,
  }
})
