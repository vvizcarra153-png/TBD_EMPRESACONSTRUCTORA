APP Constructores - Gestión de Empresa Constructora
Descripción
Aplicación de escritorio moderno desarrollado en Java 11+ con Apache NetBeans 23 para la gestión integral de una empresa constructora. Conectada a SQL Server con interfaz moderna y responsiva.

Módulos Independientes
Módulo 1: Seguridad y Usuarios (Integrante 1)
Autenticación e inicio de sesión avanzado
Gestión de usuarios
Asignación de roles y permisos
Auditoría de operaciones
Dashboard de seguridad
Módulo 2: Recursos Humanos (Integrante 2)
Gestión de empleados
Contratos laborales
Control de asistencia
Nombre y pagos
Reportes de personal
Módulo 3: Clientes y Finanzas (Integrante 3)
Gestión de clientes
Cotizaciones
Presupuestos
Pagos y facturas
Informes financieros
Módulo 4A: Proyectos (Integrante 4)
Creación y gestión de proyectos.
Fases y subfases
Tareas y seguimiento
Avance de proyectos
Flujo de estado
Módulo 4B: Inventario (Integrante 4)
Gestión de materiales
Almacenes
Control de stock
Movimientos de inventario
Alertas de stock mínimo
Módulo 4C: Proveedores (Integrante 4)
Registro de proveedores
Categorías de proveedores
Órdenes de compra
Pagos a proveedores
Histórico de compras
Módulo 4D: Subcontratistas (Integrante 4)
Registro de subcontratistas
Contratos de subcontratación
Pagos a subcontratistas
Seguimiento de trabajos
Evaluación de desempeño
Requisitos del Sistema
Java : JDK 11 o superior
IDE : Apache NetBeans 23 o superior
Base de Datos : SQL Server 2019 o superior
Controlador JDBC : mssql-jdbc-12.6.0.jre11.jar o superior
Configuración Inicial
1. Descargar Driver JDBC
Descargar desde: https://github.com/microsoft/mssql-jdbc/releases

2. Configurar Conexión a BD
Editar src/config/DatabaseConnection.java:

private static final String SERVER = "tu_servidor";
private static final String PORT = "1433";
private static final String DATABASE = "constructors_db";
private static final String USERNAME = "sa";
private static final String PASSWORD = "tu_contraseña";
3. Crear Base de Datos
Ejecutar el script SQL proporcionado para crear todas las tablas.

4. Datos Iniciales
Usuario por defecto:

Nombre de usuario : admin
Contraseña : admin123
Rol : Administrador
Estructura del Proyecto
constructors-app/
├── src/
│   ├── config/
│   │   └── DatabaseConnection.java
│   ├── modulo1_seguridad/
│   │   ├── models/
│   │   │   ├── Usuario.java
│   │   │   ├── Rol.java
│   │   │   └── Auditoria.java
│   │   ├── dao/
│   │   │   ├── UsuarioDAO.java
│   │   │   ├── RolDAO.java
│   │   │   └── AuditoriaDAO.java
│   │   └── ui/
│   │       ├── LoginFrame.java
│   │       ├── UsuarioFrame.java
│   │       └── DashboardFrame.java
│   ├── modulo2_rrhh/
│   │   ├── models/
│   │   │   ├── Empleado.java
│   │   │   ├── Contrato.java
│   │   │   ├── Asistencia.java
│   │   │   └── Nomina.java
│   │   ├── dao/
│   │   │   ├── EmpleadoDAO.java
│   │   │   ├── ContratoDAO.java
│   │   │   ├── AsistenciaDAO.java
│   │   │   └── NominaDAO.java
│   │   └── ui/
│   ├── modulo3_finanzas/
│   │   ├── models/
│   │   │   ├── Cliente.java
│   │   │   ├── Cotizacion.java
│   │   │   ├── Presupuesto.java
│   │   │   └── Pago.java
│   │   ├── dao/
│   │   │   ├── ClienteDAO.java
│   │   │   ├── CotizacionDAO.java
│   │   │   ├── PresupuestoDAO.java
│   │   │   └── PagoDAO.java
│   │   └── ui/
│   ├── modulo4a_proyectos/
│   │   ├── models/
│   │   │   ├── Proyecto.java
│   │   │   ├── Fase.java
│   │   │   ├── Tarea.java
│   │   │   └── Avance.java
│   │   ├── dao/
│   │   └── ui/
│   ├── modulo4b_inventario/
│   │   ├── models/
│   │   │   ├── Material.java
│   │   │   ├── Almacen.java
│   │   │   ├── InventarioMaterial.java
│   │   │   └── MovimientoInventario.java
│   │   ├── dao/
│   │   └── ui/
│   ├── modulo4c_proveedores/
│   │   ├── models/
│   │   │   ├── Proveedor.java
│   │   │   ├── CategoriaProveedor.java
│   │   │   └── PagoProveedor.java
│   │   ├── dao/
│   │   └── ui/
│   └── modulo4d_subcontratistas/
│       ├── models/
│       │   ├── Subcontratista.java
│       │   ├── ContratoSubcontratista.java
│       │   └── PagoSubcontratista.java
│       ├── dao/
│       └── ui/
├── lib/
│   └── mssql-jdbc-12.6.0.jre11.jar
└── nbproject/
Características de la interfaz
✓ Interfaz moderna y profesional
✓ Tema oscuro/claro seleccionable
✓ Navegación intuitiva por módulos
✓ Tablas con búsqueda y filtros avanzados
✓ Formularios validados
✓ Informes exportables a PDF/Excel
✓ Gráficos y estadísticas
✓ Sistema de notificaciones
✓ Control de permisos por módulo
Uso de la Aplicación
Abrir proyecto en Apache NetBeans 23
Configurar la conexión a SQL Server
Compilar el proyecto
Ejecutar Main.java
Usar credenciales por defecto para iniciar sesión
Navegar por los módulos según permisos asignados
Equipo de Desarrollo
Integrante 1 : Módulo Seguridad y Usuarios
Integrante 2 : Módulo Recursos Humanos
Integrante 3 : Módulo Clientes y Finanzas
Integrante 4 : Módulos Proyectos, Inventario, Proveedores y Subcontratistas
Soporte y Mantenimiento
Para problemas de conexión:

Verificar que SQL Server esté ejecutando
Comprobar credenciales en DatabaseConnection.java
Validar que el controlador JDBC está en el classpath
Revisar los registros de la aplicación
Licencia
Proyecto privado para uso interno de la empresa constructora.

Versión
v1.0 - Desarrollo inicial

Acerca de
Gestión integral de empresa constructora - Java con SQL Server

Recursos
 Léame
 Actividad
Estrellas
 0 estrellas
Vigilantes
 0 espectadores
Horquillas
 0 tenedores
Comunicados
No se han publicado comunicados
Crear una nueva versión
Paquetes
No hay paquetes publicados.
Publica tu primer paquete.
Colaboradores
1
@mela04-debug
mela04-debug
Idiomas
Java
100,0%
Flujos de trabajo sugeridos
En función de tu pila tecnológica
Logotipo de Android CI
Android CI
Crea un proyecto de Android con Gradle.
Logotipo de Clojure
Clojure
Crea y prueba un proyecto Clojure con Leiningen.
Logotipo de Java con Gradle
Java con Gradle
Crea y prueba un proyecto Java utilizando un script de envoltura de Gradle.
Más flujos de trabajo
Pie de página
