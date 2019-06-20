/**
 * Usuario.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombreAtributo: {
      type:'string'
    },
    nombre: {
      type: 'string',
      required: true,
      minLength: 3,
      maxLength: 60,
    },
    cedula: {
      type: 'string',
      required: true,
      unique: true,
      minLength: 10,
      maxLength: 25,
    },
    username: {
      type: 'string',
      required: true,
      unique: true
    },
    fechaNacimiento: {
      type: 'string'
    },
    sueldo: {
      type: 'number',
      min: 100.00,
      max: 5000,
      defaultsTo: 100.00
    },
    estaCasado: {
      type: 'boolean',
      defaultsTo: false
    },
    latitudCasa: {
      type: 'string'
    },
    longitudCasa: {
      type: 'string'
    },
    tipoUsuario: {
      type: 'string',
      enum: ['normal', 'pendiente', 'premium']
    },
    correo: {
      type: 'string',
      isEmail: true
    }
  },

};

//ESTANDAR RESTFULL

//CREAR
// http://localhost:1337/usuario
//METODO HTTP: POST
// Body Params: usuario

//Actualizar
// http:localhost:1337/usuario/:id
// http:localhost:1337/usuario/2
//METODO HTTP: PUT
// Body params:usuario

//Borrar
// http:localhost:1337/usuario/:id
// http:localhost:1337/usuario/2
// METODO HTTP: DELETE

//Buscar por ID
// http:localhost:1337/usuario/:id
// http:localhost:1337/usuario/2
// METODO HTTP: GET

//OBTENER TODOS ()

//EJEMPLOS
//1) Buscar al usuario con nombre Edgar
// http:localhost:1337/usuario?nombre=Edgar
//
//2) Buscar al usuario con nombre Edgar y cédula 1548645978
// http:localhost:1337/usuario?nombre=Edgar&cedula=1548645978
//
//3) Traer los primeros 5
// http:localhost:1337/usuario/usuario?limit=5
//
//4) Traer los primeros 5 después de los primeros 10
// http:localhost:1337/usuario/usuario?limit=5&skip=10
//
//5) Traer los registros ordenados por nombre
// http:localhost:1337/usuario/usuario?sort=nombre DESC
// http:localhost:1337/usuario/usuario?sort=nombre ASC
//
//6) Traer los registros que contengan en el nombre la letra a
// http:localhost:1337/usuario/usuario?where{"nombre":{"contains":"a"}}
// http:localhost:1337/usuario/usuario?where{"sueldo":{"<=":"1000"}}
// http:localhost:1337/usuario/usuario?where{"fechaNacimiento":{"<=":"2010-05-04"}}
// http:localhost:1337/usuario/usuario?where{"correo":{"endsWith":"@gmail.com"}}