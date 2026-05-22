# 🎨 Gestión de Artistas — JavaFX + Oracle JDBC

Aplicación de escritorio desarrollada en Java con JavaFX y conexión a Oracle Database para gestionar artistas mediante operaciones CRUD completas, con interfaz gráfica estilizada, fuente personalizada y CSS propio.

## 📋 Descripción

Aplicación de ventanas múltiples que permite administrar una tabla de artistas almacenada en Oracle. Incluye formularios de inserción, actualización y eliminación, una tabla interactiva con filtrado por género musical en tiempo real, y un sistema de estilos CSS aplicado globalmente a toda la interfaz.

## 🎯 Funcionalidades Principales

### Ventana Principal 🏠
- ✅ Menú de navegación con 4 botones de acceso a cada operación CRUD
- ✅ Fuente personalizada `Macondo` cargada desde recursos internos
- ✅ Estilos CSS aplicados globalmente a la escena principal

### Insertar Artista ➕
- ✅ Formulario con campo de texto para el nombre del artista
- ✅ Desplegable de géneros musicales (Pop, Rock, Hip-Hop, Rap, R&B, Dance, Reggaeton, Country, Jazz, Metal)
- ✅ Desplegable de países de origen (11 opciones)
- ✅ Validación: alerta de error si el nombre está vacío
- ✅ Diálogo de confirmación antes de insertar en la base de datos
- ✅ ID calculado automáticamente como `MAX(ID_A) + 1`

### Mostrar Artistas 📋
- ✅ `TableView` con columnas Nombre y Género Musical
- ✅ Filtrado en tiempo real por género mediante `FilteredList` + `ComboBox`
- ✅ Opción "Todos" para restaurar la vista completa
- ✅ Géneros del `ComboBox` cargados dinámicamente desde los datos (sin repetición)
- ✅ Botón "Eliminar" para borrar el artista seleccionado directamente desde la tabla
- ✅ Columnas ajustadas automáticamente al ancho disponible

### Actualizar Artista ✏️
- ✅ `ComboBox` cargado con los nombres de artistas desde la base de datos
- ✅ Selección de nuevo género musical y nuevo país de origen
- ✅ Validación: alerta de error si no se seleccionan todos los campos
- ✅ Diálogo de confirmación antes de ejecutar el `UPDATE`

### Eliminar Artista 🗑️
- ✅ `ComboBox` cargado con los nombres de artistas desde la base de datos
- ✅ Validación: alerta de error si no se selecciona ningún artista
- ✅ Diálogo de confirmación antes de ejecutar el `DELETE`
- ✅ Eliminación también disponible directamente desde la `TableView`

## 🏗️ Arquitectura del Proyecto

```
org.example [JavaFX CRUD Artistas]/
│
├── 📁 .mvn/                                    # Wrapper de Maven
│
├── 📁 src/
│   └── 📁 main/
│       ├── 📁 java/
│       │   └── 📁 org/example/
│       │       │
│       │       ├── 🖥️  Vistas (JavaFX Stages)
│       │       ├── 🎬 Main.java                # Punto de entrada, ventana principal
│       │       ├── 🪟 VentanaInsertar.java     # Formulario de inserción
│       │       ├── 🪟 VentanaTabla.java        # Tabla con filtrado y eliminación
│       │       ├── 🪟 VentanaActualizar.java   # Formulario de actualización
│       │       ├── 🪟 VentanaEliminar.java     # Formulario de eliminación
│       │       │
│       │       ├── 🗃️  Modelo
│       │       ├── 📦 Artista.java             # POJO: nombre y género
│       │       │
│       │       ├── 🔌 Acceso a Datos
│       │       ├── 🔗 ConexionBBDD.java        # Gestión de conexión JDBC
│       │       ├── 📥 CargarDatosBBDD.java     # SELECT: carga datos en tabla/set
│       │       ├── ➕ InsertarArtista.java     # INSERT con ID auto-calculado
│       │       ├── ✏️  ActualizarArtista.java  # UPDATE por nombre de artista
│       │       └── 🗑️  EliminarArtista.java   # DELETE por nombre de artista
│       │
│       └── 📁 resources/
│           ├── 📁 css/
│           │   └── 🎨 estilos.css              # Estilos globales de la aplicación
│           └── 📁 fonts/
│               └── 🔤 Macondo-Regular.ttf      # Fuente personalizada
│
├── 📁 test/                                    # Tests (vacío por ahora)
├── 📁 target/                                  # Clases compiladas (generado por Maven)
├── 🚫 .gitignore
├── 📄 pom.xml                                  # Dependencias y configuración Maven
└── 📄 README.md
```

## 🗄️ Modelo de Base de Datos

### Diagrama de la Tabla

```
┌──────────────────────────┐
│         ARTISTA          │
├──────────────────────────┤
│ ID_A        NUMBER (PK)  │
│ NOMBRE_A    VARCHAR2(100)│
│ GENERO_MUSICAL VARCHAR2  │
│ PAIS_ORIGEN VARCHAR2     │
└──────────────────────────┘
```

### Script de Creación de Tabla

```sql
CREATE TABLE ARTISTA (
    ID_A            NUMBER          PRIMARY KEY,
    NOMBRE_A        VARCHAR2(100)   NOT NULL,
    GENERO_MUSICAL  VARCHAR2(50),
    PAIS_ORIGEN     VARCHAR2(50)
);
```

| Campo | Tipo Oracle | Restricción | Descripción |
|-------|-------------|-------------|-------------|
| `ID_A` | `NUMBER` | `PRIMARY KEY` | Identificador único del artista |
| `NOMBRE_A` | `VARCHAR2(100)` | `NOT NULL` | Nombre artístico o real |
| `GENERO_MUSICAL` | `VARCHAR2(50)` | — | Estilo musical principal |
| `PAIS_ORIGEN` | `VARCHAR2(50)` | — | País de procedencia |

## 🚀 Configuración y Ejecución

### Requisitos Previos

- ☕ **Java JDK 17 o superior** (compatible con JavaFX 17+)
- 🗄️ **Oracle Database XE** (11g o superior) activo y accesible
- 📦 **Maven 3.8+**
- 🎨 **JavaFX SDK** (incluido como dependencia Maven)
- 🔌 **Oracle JDBC Driver** (`ojdbc11`)

### Configuración de la Conexión

#### Editar `ConexionBBDD.java`

Ajusta la URL según tu entorno:

```java
// INSTITUTO
private static final String url = "jdbc:oracle:thin:@localhost:1521:xe";

// CASA (IP de red)
private static final String url = "jdbc:oracle:thin:@192.168.56.1:1521:xe";

private static final String user = "TU_USUARIO";
private static final String password = "TU_CONTRASEÑA";
```

**⚠️ IMPORTANTE:** Ajusta los valores según tu configuración:
- `localhost` / IP → Dirección del servidor Oracle
- `1521` → Puerto de Oracle (por defecto)
- `xe` → SID de tu base de datos
- `user` y `password` → Credenciales de tu esquema

#### Añadir Dependencias (`pom.xml`)

```xml
<dependencies>
    <!-- Oracle JDBC -->
    <dependency>
        <groupId>com.oracle.database.jdbc</groupId>
        <artifactId>ojdbc11</artifactId>
        <version>23.3.0.23.09</version>
    </dependency>

    <!-- JavaFX -->
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-controls</artifactId>
        <version>21</version>
    </dependency>
    <dependency>
        <groupId>org.openjfx</groupId>
        <artifactId>javafx-fxml</artifactId>
        <version>21</version>
    </dependency>
</dependencies>
```

### Compilación y Ejecución

#### Usando Maven:
```bash
# Compilar el proyecto
mvn clean compile

# Ejecutar la aplicación
mvn javafx:run
```

#### Usando IntelliJ IDEA:
1. Importar como proyecto Maven
2. Configurar el JDK (17+)
3. Verificar la URL de conexión en `ConexionBBDD.java`
4. Ejecutar `Main.java` con el botón ▶️

## 🎮 Uso del Sistema

### Ventana Principal

```
┌─────────────────────────────┐
│       Ejercicio 24          │
├─────────────────────────────┤
│   [ Insertar nuevo artista ]│
│   [ Mostrar todos artistas ]│
│   [  Actualizar un artista ]│
│   [   Eliminar un artista  ]│
└─────────────────────────────┘
```

### Flujo de Trabajo Típico

#### 1️⃣ Insertar un Artista

```
Clic en "Insertar nuevo artista"
  → Se abre VentanaInsertar
  → Escribir nombre: "Dua Lipa"
  → Seleccionar género: "Pop"
  → Seleccionar país: "Reino Unido"
  → Clic en "Insertar"
    → Diálogo: "¿Desea añadir el artista a la Base de Datos?"
      → [OK] → Artista insertado con éxito ✓
      → [Cancelar] → No se realiza ninguna acción
```

#### 2️⃣ Ver y Filtrar Artistas

```
Clic en "Mostrar todos los artistas"
  → Se abre VentanaTabla con TableView

  NOMBRE          GÉNERO MUSICAL
  ────────────────────────────────
  Dua Lipa        Pop
  Metallica        Metal
  Bad Bunny        Reggaeton
  Coldplay         Pop Rock

  → ComboBox arriba a la derecha: seleccionar "Pop"
    → La tabla se filtra automáticamente en tiempo real
  → Seleccionar fila + clic "Eliminar"
    → Diálogo de confirmación → [OK] → Eliminado de BD y tabla ✓
```

#### 3️⃣ Actualizar un Artista

```
Clic en "Actualizar un artista"
  → Se abre VentanaActualizar
  → ComboBox artista: seleccionar "Dua Lipa"
  → ComboBox género: seleccionar "Dance"
  → ComboBox país: seleccionar "Reino Unido"
  → Clic en "Actualizar"
    → Diálogo de confirmación → [OK] → Artista actualizado ✓
```

#### 4️⃣ Eliminar un Artista

```
Clic en "Eliminar un artista"
  → Se abre VentanaEliminar
  → ComboBox: seleccionar "Metallica"
  → Clic en "Eliminar"
    → Diálogo de confirmación → [OK] → Artista eliminado ✓
```

## 📊 Validaciones Implementadas

### Insertar Artista
- ❌ Nombre vacío → `Alert ERROR`: "Ingrese todos los campos para guardar"
- ✅ Todos los campos informados → Diálogo de confirmación antes de insertar

### Actualizar Artista
- ❌ Algún `ComboBox` sin selección → `Alert ERROR`: "Seleccione todos los campos para actualizar"
- ✅ Todos los campos seleccionados → Diálogo de confirmación antes de actualizar

### Eliminar Artista (formulario)
- ❌ Ningún artista seleccionado → `Alert ERROR`: "Seleccione un artista a eliminar"
- ✅ Artista seleccionado → Diálogo de confirmación antes de eliminar

### Eliminar desde TableView
- ❌ Ninguna fila seleccionada → `Alert INFORMATION`: "No ha seleccionado ningún artista"
- ✅ Fila seleccionada → Confirmación → Borrado en BD y eliminado de `ObservableList`

## 🎨 Sistema de Estilos (CSS)

El archivo `estilos.css` aplica un tema visual coherente en verde-azulado oscuro a todos los componentes:

| Componente | Estilo aplicado |
|------------|-----------------|
| `.root` | Fondo `#459199` en toda la ventana |
| `*` | Fuente `Macondo` global |
| `.button` | Degradado `#1a7a6e → #125259`, bordes redondeados, sombra |
| `.button:hover` | Degradado más claro, cursor `hand`, sombra intensa |
| `.button:pressed` | Degradado invertido, sombra reducida |
| `.combo-box` | Mismo degradado que botones, texto blanco |
| `.text-field` | Degradado verde, prompt text semitransparente |
| `.label` | Texto blanco, tamaño `30px`, negrita |
| `.table-view` | Fondo `#125259`, sin bordes visibles |
| `.table-row-cell:odd` | Color alterno `#0f3d35` |
| `.table-row-cell:hover` | Verde `#1a7a6e` |
| `.table-row-cell:selected` | Azul cyan `#2BC7D9` |

## 🔧 Características Técnicas

### Patrones de Diseño Aplicados

- **MVC simplificado**: Vistas (`Ventana*.java`) separadas del modelo (`Artista.java`) y del acceso a datos (`*Artista.java`, `CargarDatosBBDD.java`)
- **DAO (Data Access Object)**: Cada operación CRUD en su propia clase (`InsertarArtista`, `EliminarArtista`, `ActualizarArtista`)
- **Single Responsibility**: Cada clase gestiona exactamente una responsabilidad

### Buenas Prácticas

- ✅ **Try-with-resources** para cierre automático de `Connection`, `Statement` y `PreparedStatement`
- ✅ **PreparedStatement** en todas las operaciones DML para prevenir SQL Injection
- ✅ **ObservableList + FilteredList** para filtrado reactivo sin re-consultar la base de datos
- ✅ **PropertyValueFactory** para enlace automático modelo ↔ columnas de `TableView`
- ✅ **Diálogos de confirmación** en todas las operaciones destructivas
- ✅ **CSS externalizado** para separar estilos del código Java

### Seguridad

- 🔒 **SQL Injection**: Uso exclusivo de `PreparedStatement` en INSERT, UPDATE y DELETE
- 🔒 **Validación de entrada**: Verificación antes de ejecutar cualquier operación en BD
- 🔒 **Confirmación obligatoria**: Ninguna operación de escritura se ejecuta sin confirmación del usuario
- 🔒 **Integridad de tabla**: La `ObservableList` se actualiza en memoria al eliminar para mantener consistencia visual

## ⚠️ Manejo de Errores

### Errores Comunes y Soluciones

#### 1. Error de Conexión
```
RuntimeException: Error al conectar con la BD
```
**Solución:**
- Verificar que Oracle Database esté activo
- Comprobar URL, usuario y contraseña en `ConexionBBDD.java`
- Verificar que el puerto `1521` esté escuchando

#### 2. CSS o fuente no encontrados
```
NullPointerException en getResource("/css/estilos.css")
```
**Solución:**
- Verificar que `estilos.css` esté en `src/main/resources/css/`
- Verificar que `Macondo-Regular.ttf` esté en `src/main/resources/fonts/`
- Ejecutar `mvn clean compile` para sincronizar recursos

#### 3. Tabla vacía al abrir VentanaTabla
```
TableView muestra 0 filas
```
**Solución:**
- Verificar que existan datos en la tabla `ARTISTA` de Oracle
- Comprobar la consulta SQL en `CargarDatosBBDD.cargarArtistas()`
- Revisar permisos del usuario sobre la tabla

#### 4. ComboBox de artistas vacío en Actualizar/Eliminar
```
ComboBox no muestra ningún artista
```
**Solución:**
- Verificar conexión activa al abrir la ventana
- Comprobar que `CargarDatosBBDD.cargarNombresArtistas()` devuelve datos
- Revisar logs de consola para errores SQL silenciosos

## 🎓 Conceptos de Programación Aplicados

### JavaFX
- **Stage / Scene**: Gestión de ventanas múltiples independientes
- **VBox / BorderPane / GridPane**: Layouts para organización de componentes
- **TableView + PropertyValueFactory**: Tabla enlazada al modelo `Artista`
- **ObservableList + FilteredList**: Lista reactiva con filtrado dinámico
- **ComboBox.valueProperty().addListener()**: Escuchador de cambios en desplegable
- **Alert**: Diálogos de información, error y confirmación con `Optional<ButtonType>`
- **Font.loadFont()**: Carga de fuente personalizada desde recursos
- **scene.getStylesheets()**: Aplicación de CSS externo a la escena

### JDBC
- **PreparedStatement**: Consultas parametrizadas seguras contra SQL Injection
- **ResultSet**: Navegación por resultados de SELECT
- **Try-with-resources**: Cierre automático de `Connection`, `Statement` y `ResultSet`
- **MAX(ID) + 1**: Generación manual de IDs sin SEQUENCE

### Programación Orientada a Objetos
- **POJO (Artista)**: Clase modelo con encapsulación mediante `getters`
- **Métodos estáticos utilitarios**: Patrón de clase de utilidad en clases DAO
- **Separación de responsabilidades**: Vista, modelo y acceso a datos desacoplados

## 📈 Mejoras Futuras

- [ ] Usar `db.properties` para externalizar credenciales de conexión
- [ ] Añadir columna `PAIS_ORIGEN` en la `TableView`
- [ ] Implementar búsqueda por nombre con `TextField` + `FilteredList`
- [ ] Usar Oracle `SEQUENCE` en lugar de `MAX(ID) + 1` (thread-safe)
- [ ] Añadir validación de duplicados antes de insertar
- [ ] Implementar pool de conexiones con **HikariCP**
- [ ] Exportar la tabla a CSV desde la `TableView`
- [ ] Añadir paginación para grandes volúmenes de datos
- [ ] Tests unitarios con **JUnit 5** para las clases DAO

## 🐛 Troubleshooting

### La aplicación no arranca
1. Verificar JDK instalado: `java -version` (requiere 17+)
2. Comprobar módulos JavaFX en `pom.xml`
3. Limpiar y recompilar: `mvn clean install`

### Los botones no tienen estilo
1. Verificar que `estilos.css` esté en `src/main/resources/css/`
2. Comprobar la ruta en `scene.getStylesheets().add(...)`
3. Ejecutar `mvn clean compile` y reiniciar

### El filtro del ComboBox no funciona
1. Verificar que `tableView.setItems(listaFiltrada)` esté llamado (no `listaOriginal`)
2. Comprobar que el `listener` esté registrado sobre `comboBox.valueProperty()`

### Error al insertar artista duplicado
1. Verificar si existe restricción `UNIQUE` sobre `NOMBRE_A` en la tabla
2. Añadir validación previa con `SELECT COUNT(*) FROM ARTISTA WHERE NOMBRE_A = ?`

## 📚 Recursos Adicionales

- [JavaFX Documentation](https://openjfx.io/javadoc/21/)
- [Oracle JDBC Documentation](https://docs.oracle.com/en/database/oracle/oracle-database/23/jjdbc/)
- [JavaFX CSS Reference](https://openjfx.io/javadoc/21/javafx.graphics/javafx/scene/doc-files/cssref.html)
- [FilteredList JavaFX](https://docs.oracle.com/javase/8/javafx/api/javafx/collections/transformation/FilteredList.html)
- [Maven JavaFX Plugin](https://openjfx.io/openjfx-docs/#maven)

## 🎯 Ejercicios Propuestos

1. **Básico**: Añadir la columna `PAIS_ORIGEN` a la `TableView` de artistas
2. **Intermedio**: Añadir un `TextField` de búsqueda por nombre con `FilteredList`
3. **Avanzado**: Combinar filtro por género y búsqueda por nombre simultáneamente
4. **Experto**: Refactorizar la conexión usando `db.properties` y un pool con HikariCP

## 💡 Notas del Desarrollador

### Decisiones de Diseño

1. **`MAX(ID_A) + 1` vs SEQUENCE de Oracle**
   - Ventaja: Simple, no requiere crear objetos adicionales en la BD
   - Desventaja: No es seguro en concurrencia (dos inserciones simultáneas podrían generar el mismo ID)

2. **`ObservableList` + `FilteredList` vs re-consulta a BD al filtrar**
   - Ventaja: Filtrado instantáneo sin latencia de red ni carga en Oracle
   - Desventaja: Si los datos cambian externamente, la lista no se actualiza sola

3. **CSS global vs estilos inline en Java**
   - Ventaja: Separación total de presentación y lógica; fácil de modificar el tema
   - Desventaja: Requiere que el archivo esté correctamente en el classpath

4. **Métodos estáticos en clases DAO**
   - Ventaja: Uso sencillo sin instanciar objetos (`InsertarArtista.nuevoArtista(...)`)
   - Desventaja: Dificulta los tests unitarios y la inyección de dependencias

## 📄 Licencia

Proyecto educativo para aprendizaje de JavaFX, JDBC, Oracle Database y diseño de interfaces gráficas en Java.

## 👤 Autor

Aplicación desarrollada como ejercicio de aprendizaje de interfaces gráficas con JavaFX, acceso a datos con JDBC Oracle y diseño visual con CSS en aplicaciones de escritorio Java.

---

## 🎨 ¡Gestiona tus Artistas con Estilo!

Una aplicación CRUD completa con interfaz gráfica moderna, tema visual personalizado y conexión directa a Oracle. Lista para crecer hacia un sistema completo de gestión musical. ☕🎵🗄️
