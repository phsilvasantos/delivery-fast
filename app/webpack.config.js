const webpack = require('webpack');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const CopyWebpackPlugin = require('copy-webpack-plugin');
const CleanObsoleteChunks = require('webpack-clean-obsolete-chunks');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const InterpolateHtmlPlugin = require('interpolate-html-plugin');

var path = require('path');
const ENV = process.env.NODE_ENV || 'development';
const isProd = ENV != 'development';
const VERSION = JSON.stringify(require('./package.json').version).replace(/["']/g, '');

const plugins = [
  new CleanObsoleteChunks(),
  new webpack.HotModuleReplacementPlugin(),

  new HtmlWebpackPlugin({
    template: path.join(__dirname, 'src/index.html'),
    filename: 'index.html',
    inject: 'body',
  }),
  new InterpolateHtmlPlugin({
    PUBLIC_URL: './public'
  }),
  new CopyWebpackPlugin([
    // Copy directory contents to {output}/to/directory/
    { from: path.join(__dirname, 'src/assets') },
    { from: path.join(__dirname, 'src/login.html') },
  ]),
  new MiniCssExtractPlugin({
    filename: '[name].css',
    chunkFilename: '[id].css',
  }),
];

module.exports = env => {
  console.log(
    `|******************* NODE_ENV: ${ENV.toUpperCase()} #### VERSION: ${VERSION} ***********************************|`
  );

  return {
    mode: isProd ? 'production' : 'development',
    entry: {
      application: path.join(__dirname, 'src/Boot.js'),
      vendor: ['react', 'react-dom', 'react-router', 'react-router-dom'],
    },
    output: {
      filename: '[name].[hash].js',
      path: path.join(__dirname, 'build/'),
      publicPath: '/reinf',
    },
    devtool: env.prod ? false : 'inline-source-map',
    resolve: {
      alias: {
        envs: path.join(__dirname, 'src/envs', ENV),
        react: path.resolve('./node_modules/react'),
      },
    },
    bail: env.prod,
    module: {
      rules: [
        {
          test: /\.js$|\.jsx$/,
          exclude: /node_modules/,
          use: {
            loader: 'babel-loader',
            options: {
              presets: ['@babel/preset-env', '@babel/preset-react'],
            },
          },
        },
        { test: /\.css$/, use: [MiniCssExtractPlugin.loader, 'css-loader'] },
      ],
    },
    plugins: plugins,
    optimization: {
      minimize: isProd,
      runtimeChunk: true,
      splitChunks: {
        cacheGroups: {
          commons: {
            test: /[\\/]node_modules[\\/]/,
            name: 'vendor',
            chunks: 'all',
          },
        },
      },
    },
  };
};
