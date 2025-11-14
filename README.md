# LEER IMPORTANTE

PARA LOS POST, COLOCAR OBJETOS SOLO CON LA ID DENTRO DEL PARAMETRO

ejemplo:

```json
// http:/<url>/api/usuarios - POST

{
        "idUsuario": 1,
        "nombreUsuario": "xde",
        "emailUsuario": "12",
        "idRol": {
            "idRol": 1,
            "nombreRol": null // se puede omitir
        }
}
```