/**
 * Empresa.js
 *
 * @description :: A model definition represents a database table/collection.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {
    nombre:{
      type:'string',
      required: true
    },
    //Configuración del papá(Empresa-usuario)
    usuariosDeEmpresa:{     //Nombre del atributo de la relación
      collection:'usuario', //Nombre del modelo a relacionar
      via:'fkEmpresa'       //Nombre del atributo FK del otro modelo
  }

  },

};

