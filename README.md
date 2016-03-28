# Paddle
Spring. Paddle

##ECP1. Persistencia (30%)
  *A (+2) Modificar la capa de persistencia para que los tokens caduquen en 1 hora. Se deberá ofrecer una funcionalidad de eliminación de tokens caducados:
    *Se agrega nuevo parametro en la entidad Token de fecha de expiración
    *Se ha creado el método para eliminar token caducado en TokenDao
    *En el servicio de usuario se reeemplaza el método de busqueda de token por uno no expirado
  *B (+5) Ampliar la capa de persistencia para poder ofrecer un servicio de clases de padel. El diseño es abierto
    *Se ha creado una entidad para los entrenamientos (id, trainer, court, players, startDate, endDate) con las siguientes relaciones:
      *Un entrenamiento pertenece a un entrenador, pero un entrenador tiene muchos entreamientos
      *Un entrenamiento tiene muchos jugadores y un jugador puede apuntarse a varios entrenamientos
    *Tambien se ha creado un TrainingDao con metodos de busqueda por id, startDate, court
    *En el poblador se ha creado un entrenador por defecto
  *C (+3) Modificar o realizar los test de las mejorar anteriores
      *Nuevos test de entidad y dao
        
##ECP1. Api (50%)
  *A (+2) Modificar la capa de negocio para que la validación de tokens incluya la mejora de caducidad
    *En el controlador del token se ha establecido la fecha de expiracion en una hora
  *B (+5) Ampliar la capa de negocio para poder ofrecer un servicio de clases de padel, incluyendo la seguridad. El diseño es abierto
    *Se ha creado el controlador de entrenamiento y se verifica que no se registren mas de 4 jugadores
    *Se ha creado los envolventes CreateTraining y TrainingWrapper
    *Se ha creado el recuroso TrainingResource y sus excepciones, para crear un entrenamiento se solicita la fecha de inicio y las semanas que dura dicho entrenamiento
  *C (+3) Modificar o realizar los test de las mejorar anteriores
    *Se ha probado la funcionalidad del TrainingController
    *También se probó la funcionalidad del TrainingResouser y las excepciones
    
##ECP1. Web (20%)
  *A (+10) Realizar la capa Web de algunas de las operaciones de la aplicación de Paddle, sin seguridad
    *Se realizó toda la funcionalidad de pista con thymeleaf
    
      
      
