'use strict'

require('source-map-support').install()

exports.handler = (event, context, callback) => {
  let id = (event.pathParameters || {}).id || false
  switch (event.httpMethod) {
    case 'GET':
      if (id) {
        console.log('get product id=' + id)
        callback(null, {
          statusCode: 200,
          body: 'get product id=' + id
        })
      } else {
        console.log('list all products')
        callback(null, {
          statusCode: 200,
          body: 'list all products'
        })
      }
      break
    case 'POST':
      console.log('create product')
      callback(null, {
        statusCode: 201,
        body: 'created product id=NEW'
      })
      break
    default:
      console.log('ERROR unsupported operation. Method=' + event.httpMethod)
      callback(null, {
        statusCode: 501
      })
      break
  }
}
