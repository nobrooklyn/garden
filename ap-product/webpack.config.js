const Webpack = require('webpack');

module.exports = {
  entry: `./src/index.js`,
  target: 'node',
  mode: 'production',
      
  output: {
    path: `${__dirname}/dist`,
    filename: 'main.js',
    libraryTarget: 'commonjs'
  },
  externals: ['aws-sdk'],
  module: {
    rules: [
      {
        test: /\.js$/,
        enforce: 'pre',
        loader: 'eslint-loader',
        options: {
          fix: true,
          failOnError: true,
          failOnWarning: true,
          emitError: true
        },
        exclude: [/node_modules/]
      }
    ]
  },
  plugins: [
    new Webpack.NoEmitOnErrorsPlugin(),
    new Webpack.DefinePlugin({
      'process.env': {
        NODE_ENV: JSON.stringify('production')
      }
    }),
    new Webpack.LoaderOptionsPlugin({
      minimize: true,
      debug: false
    })
  ],
  devtool: 'inline-source-map',
  bail: true
};
  
