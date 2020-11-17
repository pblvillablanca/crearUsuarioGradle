Crear BD en Postgresql

	create user usuarios encrypted password 'usua_vil874';
	create database usuarios owner usuarios;
	grant ALL on DATABASE usuarios to usuarios;

URL de prueba para creación de usuarios
	
	http://localhost:8080/usuarios/crear

Json de prueba para creación de usuarios
	
	{
	   "name":"Juan Rodriguez",
	   "email":"juan@rodriguez.org",
	   "password":"Abzxc34",
	   "phones":[
	      {
		 "number":"1234567",
		 "citycode":"1",
		 "contrycode":"57"
	      }
	   ]
	}
