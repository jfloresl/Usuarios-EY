# Usuarios-EY
Springboot API CRUD Usuarios
## Api

### Crear usuarios
POST http://localhost:8080/api/users
#### Request
```json
{ 
  "name": "Juan Rodriguez",
  "email": "juan@dominio.cl",
  "password": "hunterA2",
  "phones": [
    {
      "number": "1234567",
      "citycode": "1",
      "contrycode": "57"
    }
  ]
}
```
#### Response
```json
{
    "id": "240246ab-1cfa-4f95-a024-97bb98bc8b7a",
    "name": "Juan Rodriguez",
    "email": "juan@dominio.cl",
    "password": "hunterA2",
    "created": "2023-06-15",
    "modified": "2023-06-15",
    "last_login": "2023-06-15",
    "token": "e0657695-7292-4aee-a7bd-7725a18492f0",
    "isactive": "1",
    "phones": [
        {
            "id": 1,
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```
### Ver todos los usuarios
GET http://localhost:8080/api/users/all
#### Response
```json
[
    {
        "id": "2b109181-de42-4b1b-91fa-2fdaf4bdaf90",
        "name": "Juan Rodriguez",
        "email": "juan@dominio.cl",
        "password": "*********",
        "created": "2023-06-16",
        "modified": "2023-06-16",
        "last_login": "2023-06-16",
        "token": "8d6f047d-fa74-46f0-9aee-48e7fd6070e3",
        "isactive": "1",
        "phones": [
            {
                "id": 1,
                "number": "1234567",
                "citycode": "1",
                "contrycode": "57"
            }
        ]
    }
]
```
### Ver un usuario
GET http://localhost:8080/api/users?id={id}&token={token}

#### RESPONSE
```json
{
    "id": "2b109181-de42-4b1b-91fa-2fdaf4bdaf90",
    "name": "Juan Rodriguez",
    "email": "juan@dominio.cl",
    "password": "*********",
    "created": "2023-06-16",
    "modified": "2023-06-16",
    "last_login": "2023-06-16",
    "token": "8d6f047d-fa74-46f0-9aee-48e7fd6070e3",
    "isactive": "1",
    "phones": [
        {
            "id": 1,
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
```


### Modificar Usurios
PUT http://localhost:8080/api/users/
#### Request
```json

{ "id":"240246ab-1cfa-4f95-a024-97bb98bc8b7a",
  "name": "Juan Rsodriguezsss",
  "email": "jueeeean@dominio.cl",
  "token": "e0657695-7292-4aee-a7bd-7725a18492f0"
}
```


#### Response
```json
{
   {
    "id": "240246ab-1cfa-4f95-a024-97bb98bc8b7a",
    "name": "Juan Rsodriguezsss",
    "email": "jueeeean@dominio.cl",
    "password": "*********",
    "created": "2023-06-15",
    "modified": "2023-06-15",
    "last_login": "2023-06-15",
    "token": "e0657695-7292-4aee-a7bd-7725a18492f0",
    "isactive": "1",
    "phones": [
        {
            "id": 1,
            "number": "1234567",
            "citycode": "1",
            "contrycode": "57"
        }
    ]
}
}
```


### Eliminar usuarios
DELETE http://localhost:8080/api/users/delete?id={id}&token={token}
#### Response
```json
{
    "message": "Usuario eliminado",
    "status": "202"
}
```


## Diagrama
![diagrama](Diagrama.png)
