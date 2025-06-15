# Manual Técnico - Sistema de Gestión de Vehículos

## Introducción
Este manual describe la arquitectura, diseño y funcionamiento interno del sistema de gestión de vehículos, multas y traspasos implementado en Java. El sistema utiliza estructuras de datos avanzadas para optimizar operaciones de búsqueda, inserción y recorrido, además de ofrecer interfaces gráficas para la interacción del usuario.

---

## 1. Arquitectura General

- **Estructuras de Datos Principales**:  
  - Árbol AVL (`ArbolAVL`) para almacenamiento balanceado de vehículos.
  - Árbol ABB (`ArbolABB`) para almacenamiento no balanceado (referencial/comparativo).
  - Listas doblemente enlazadas para multas (`ListaMultasDoble`).
  - Listas circulares para traspasos (`ListaTraspasosCircular`).
- **Persistencia**:  
  - Archivos de texto organizados por departamento, para vehículos, multas y traspasos.
- **Interfaz de Usuario**:  
  - Aplicación Swing con formularios para gestión, estadísticas, historiales y operaciones CRUD.

---

## 2. Descripción de Clases Clave

### 2.1 Vehiculo
- Modelo de dominio para vehículos con atributos: placa, DPI, nombre, marca, modelo, año, multas, traspasos.
- Contiene referencias a listas de multas y traspasos asociadas.
- Métodos getters/setters estándares, más métodos auxiliares específicos (e.g., restarMulta).

### 2.2 ArbolAVL
- Implementación del árbol balanceado AVL:
  - Campos: NodoAVL raíz.
  - Inserción con balanceo automático mediante rotaciones (derecha, izquierda, derecha-izquierda, izquierda-derecha).
  - Métodos de búsqueda binaria por placa.
  - Funciones para recorrido en orden, preorden y postorden, retornando `DefaultTableModel`.
  - Carga y guardado recursivo desde/ a archivos departamento.
  - Manejo seguro al escribir archivos (creación de directorios, streams con try-with-resources).
  - Medición de tiempo de búsqueda con actualización visual.

- **NodoAVL**:
  - Nodo base con altura para balanceo.
  - Métodos para actualizar altura y obtener factor de balance.

### 2.3 ArbolABB
- Árbol binario normal sin balanceo:
  - Funcionalidades similares a `ArbolAVL` pero sin rotaciones ni balanceo.
  - Usado para comparar rendimiento y comportamiento.

- **NodoABB**:
  - Nodo básico con referencias a hijos izquierdo y derecho.

### 2.4 ListaMultasDoble
- Lista doblemente enlazada para almacenar las multas de los vehículos.
- Implementa métodos para agregar, eliminar y persistir multas en archivos.
- Uso de `HashSet` para evitar duplicados al guardar.
- Métodos robustos para lectura y escritura línea a línea con manejo de excepciones.

### 2.5 ListaTraspasosCircular
- Lista circular para registrar los traspasos de vehículos.
- Métodos para agregar elementos y persistir por departamento.
- Carga recursiva por carpetas y archivos.
- Control eficiente de inicio y recorrido circular.

### 2.6 ControladorSistema
- Clase que mantiene instancias estáticas globales para:
  - Árbol AVL (`arbolVehiculos`)
  - Árbol ABB (`arbolesbb`)
  - Lista de multas (`multas`)
  - Lista de traspasos (`traspasos`)
- Facilita acceso centralizado a datos desde interfaces.

### 2.7 Interfaces Gráficas
- **VentanaPrincipal**:
 
  - Carga y despliegue inicial de datos avl y abb.
  - Botones para CRUD, estadísticas y operación en ambas estructuras.
  - Gestión sincronizada de tablas y cifrado/descifrado.
- **Pantallaagregarvehiculos**:
  - Captura datos para nuevo vehículo.
  - Validaciones de campos obligatorios y unicidad de placa.
  - Inserta en ambos árboles con actualización y persistencia.
- **VentanaMultas**:
  - Añade multas a vehículos existentes.
  - Sincroniza listas y actualiza contadores.
- **VentanaTraspaso**:
  - Maneja cambios de propietario (nombre y DPI).
  - Registra histórico en lista circular.
  - Actualiza datos y persistencia.
- **VentanaHistorialVehiculo**:
  - Consulta y despliega historial de multas y traspasos.
  - Permite pagar multas y actualizar listas y archivos.
- **Estadistica**:
  - Muestra totales y máximos de multas y traspasos usando recorrido AVL.

---

## 3. Flujo de Datos y Operaciones Principales

- **Carga inicial**:  
  - Desde la carpeta base, se leen carpetas por departamento.
  - Se cargan archivos de vehículos, multas y traspasos.
  - Se insertan en estructuras de datos correspondientes.

- **Inserción de Vehículo**:  
  - Validación de datos (campos no vacíos, placa única).
  - Inserción en árboles AVL y ABB.
  - Persistencia actualizada por departamento.

- **Gestión de Multas**:  
  - Adición en lista doble.
  - Incremento de contador en vehículo.
  - Guardado en archivo correspondiente.
  - Visualización sincronizada.

- **Gestión de Traspasos**:  
  - Registro en lista circular.
  - Actualización del propietario en vehículo.
  - Incremento contador y persistencia.

- **Cifrado/Descifrado**:  
  - Aplicación de cifrado César sobre campos sensibles.
  - Acceso a clave desde config centralizada.
  - Métodos recursivos sobre árboles.

- **Búsquedas**:  
  - Árboles realizan búsqueda binaria eficiente.
  - Medición de tiempo para comparación.
  - Resultados informados en UI.

---

## 4. Detalles Técnicos

- **Persistencia**:
  - Archivos planos CSV con delimitadores ','.
  - Directorios dinámicos por departamento.
  - Escrituras con BufferedWriter en modo append o sobrescritura.
- **Excepciones**:
  - Manejo básico de excepciones I/O y formateo.
  - Impresión de stack trace para debug.
- **UI**:
  - Uso de Swing con diseño absoluto (AbsoluteLayout).
  - Tablas con DefaultTableModel para datos dinámicos.
- **Optimización**:
  - Árbol AVL balanceado para búsquedas rápidas.
  - Listas especializadas para datos asociados.
  - Control central de estado con ControladorSistema.

---

## 5. Dependencias y Configuración

- Uso exclusivo de librerías estándar de Java SE (java.io, javax.swing).
- Configuración de clave de cifrado en clase `Config`.
- Uso de rutas absolutas configuradas en el código para datos.

---

## 6. Consideraciones para Mantenimiento

- Separar rutas y configuración de entorno para mejorar portabilidad.
- Reforzar manejo de errores y validaciones.
- Modularizar código para mejorar legibilidad y pruebas unitarias.
- Implementar interfaz para acceso a datos para desacoplar lógica de persistencia.
- Incorporar logging adecuado en lugar de printStackTrace.

---

## Apéndices

### A. Ejemplo de línea en archivo vehículo CSV  
`PXZ123,1234567890123,Juan Pérez,Toyota,Corolla,2015,2,1`

### B. Funciones de rotación AVL  
- Rotación simple derecha  
- Rotación simple izquierda  
- Rotación doble izquierda-derecha  
- Rotación doble derecha-izquierda

### C. Cifrado César  
- Alfabeto de 26 letras, desplazamiento configurable.

---

Fin del Manual Técnico.
