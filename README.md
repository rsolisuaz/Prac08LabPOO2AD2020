# PRÁCTICA 8. IMPLEMENTACIÓN DE DAO PARA LA TABLA institucion.

## COPIA DEL REPOSITORIO REMOTO EN SU COMPUTADORA LOCAL

Como primer paso, será necesario crear una copia local del repositorio remoto creado en Github al aceptar la tarea. Para ello, es necesario hacer los siguientes pasos:
1. Entrar a la página cuyo URL les fue proporcionado al aceptar la tarea, en tal página dé click en el botón Code y copie el URL que aparece en el cuadro de texto de nombre **Clone with HTTPS** (comienza con *https://*)
2. Abre IntelliJ IDEA e indica que harás un clon local de tu repositorio:
   - Si no tienes ningún repositorio abierto selecciona la opción **Get From Version Control** de la Ventana de Bienvenida, o si tienes un proyecto abierto, puedes entrar al menú **VCS** y seleccionar la opción **Get From Version Control**
   - En el cuadro de diálogo que aparece:
     - Selecciona Git
     - Pega el URL que copiaste en el paso 1  en el cuadro de texto **URL**
     - Selecciona en Directory la carpeta donde lo colocarás, es importante que crees una nueva carpeta o se colocará (da click en el icono de carpeta , navega a donde lo quieres colocar y da click en el icono de New Folder para crear una nueva carpeta)
     - Da click en **Clone**
     - Si te pide usuario y clave de Github proporciona esos datos
     - Después de unos segundos tendrás listo tu un clon de tu repositorio listo para trabajar en Intellij IDEA

## MODIFICACIÓN DEL CÓDIGO PROPORCIONADO
Una vez que tengas el repositorio local, el trabajo consiste en crear las clases de entidad para la base de datos **controlconcursos**, completar la clase de entidad asociada con la tabla **institucion**,  implementar la interface **Dao<T>** para hacer las operaciones CRUD en la tabla **institucion** y los métodos asociados con institución de la interface  **DAOConcursos**, la implementación de la interface **Dao<T>** se hará en la clase **DaoInstitucion** y la implementación de la interface **DaoConcursos** en la clase **DaoConcursosImpl**, ambas a colocarse en el paquete **mx.edu.uaz.ingsoftware.poo2.basedatos** .

En el paquete **mx.edu.uaz.ingsoftware.poo2.main** se te recomienda crear clases para que pruebes que tus clases **DaoInstitucion** y **DaoConcursos** funcionan correctamente.

## CALIFICACIÓN
Cada uno de los métodos que debes implementar de la de la interface **Dao<T>** y **DaoConcursos**, además de la clase de entidad de **institucion** aportan parte de la calificación:

1. La clase de entidad aporta  **5 puntos**
2. Los métodos que tienen que ver con recuperar información aportan **20 puntos**
3. Los métodos que tienen que ver con agregar información aportan **30 puntos**
4. Los métodos que tienen que ver con actualizar información aportan **30 puntos**
5. Los métodos que tienen que ver con eliminar información aportan **15 puntos**

Para ejecutar las pruebas de tu programa selecciona **DBTest** dentro de src/test/java, dale click con el botón derecho y selecciona **Run** (la opción tendrá un triángulo verde ), te mostrará el resultado de las pruebas y la calificación obtenida

**RECUERDA QUE SOLO DEBES HACER CAMBIOS EN LO RELACIONADO A LA TABLA INSTITUCION** 

## NOTAS IMPORTANTES
1. En cada uno de los métodos de la interface DAO<T> que implementarás para la tabla institucion, debes capturar las posibles excepciones que se den al conectarse a MySQL y en su lugar emitirás una excepción de tipo DaoException con los mismos mensajes y causas que la excepción original, esto para que en caso de que quien use la clase tenga la opción de determinar las causas del error.
2. Al agregar o actualizar es necesario asegurarse que los campos tienen un rango valido de valores, en caso contrario truncarlos antes de guardarlos. En el caso de los id de municipio o entidad, verificar que existen en la tabla municipio y entidad respectivamente (en las pruebas del repositorio remoto solo se puden usar las entidades 1 a 5 y la 32 con sus respectivos municipios)
3. Al eliminar una institución es necesario verificar primero que no haya registros asociados con la institución a eliminar, es decir, primero hay que ver que ninguna persona, equipo o sede esten asociados con tal institucion, si ya hay alguna persona, equipo o sede asociados, no se deberá eliminar la institucion y se regresará false.
4. La clase de entidad de institucion deberá comparar objetos de clase Institucion en base solamente al atributo idInstitucion y el toString deberá regresar el nombre de la institucion.
4. Cada vez que completes un método ejecuta las pruebas para verificar que las pruebas del método completado son exitosas
5. Una vez vez que completes un método y verifiques que pasa las pruebas haz un Commit : 
   - Para hacer commit, selecciona primero el proyecto, después entra al menú VCS y selecciona la opción Commit...
   - Te aparecerá una lista de archivos con los cambios detectados, verifica que incluye todos los archivos que deberían estar
   - Teclea un mensaje que describa los cambios realizados de manera clara y concisa (debe ser un mensaje que permita a otras personas darse cuenta de lo realizado)
   - Dé click en el ícono del engrane (Show Commit Options), escriba su nombre en el cuadro Author, deseleccione Perform Code Analysis y Check TODO (Esto es necesario solo en el primer commit que hagan)
   - Dé click en Commit
6. Después de haber hecho todos los commits que completan tu programa, súbelo al repositorio remoto haciendo lo siguiente:
   - Entre al menú VCS y seleccione la opción Git->Push...
   - Dé click en el botón Push en el cuadro de diálogo que aparece
7. Cada vez que subas tu proyecto al repositorio privada con un push, se aplicarán automáticamente las pruebas sobre tu código para verificar si funciona correctamente. Recuerda que en la página de tu repositorio en la sección **Pull Requests**, se encuentra una subsección de nombre **Feedback**, donde podrás encontrar los resultados de las pruebas en la pestaña denominada **Check** (expandiendo la parte que dice **Run education/autograding@v1**), y cualquier comentario general que el profesor tenga sobre tu código en la pestaña **Conversation**. 
