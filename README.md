##Aplicacion simple con JSP, Servlets y ORM
#Grupo 1
#Flujo general de la aplicación:
-  El usuario interactúa con la página JSP a través de su navegador.
-  La página JSP muestra un formulario para añadir usuarios y un botón para listar los usuarios existentes.
-  Al hacer clic en el botón para listar usuarios o enviar el formulario para agregar un nuevo usuario, el Servlet procesa la solicitud y realiza las operaciones necesarias.
-  El Servlet interactúa con el modelo de datos a través del ORM (Hibernate) para gestionar los datos en la base de datos.
-  El Servlet luego redirige nuevamente a la página JSP, donde se muestran los resultados, como la lista de usuarios o una confirmación de la operación.
