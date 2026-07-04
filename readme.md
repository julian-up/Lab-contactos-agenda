# 📒 Agenda de Contactos — Java Collections

Laboratorio **TIFC2DEV-JAVA-MJ10 – Address-Book (Collections)**

Aplicación de consola en Java para gestionar contactos y grupos, aplicando el **Framework de Colecciones de Java**: `List`, `Set`, `Map`, genéricos y la elección de la implementación adecuada según el caso de uso.

## 📁 Estructura del proyecto

| Archivo | Descripción |
|---|---|
| `Contacto.java` | Clase modelo con atributos privados (nombre, teléfono, email), constructor, getters, setters y `toString()`. |
| `Agenda.java` | Lógica de negocio: agregar, buscar, eliminar y listar contactos; crear grupos y gestionar sus miembros. |
| `MenuPrincipal.java` | Menú interactivo por consola (`while` + `switch` + `Scanner`) con manejo de opciones inválidas. |

## 🧠 Colecciones utilizadas y justificación

| Colección | Implementación | ¿Para qué se usa? |
|---|---|---|
| `Map<String, Contacto>` | `HashMap` | Almacena los contactos usando el **nombre como clave**, lo que permite buscar, insertar y eliminar en tiempo O(1). |
| `Set<String>` | `HashSet` | Guarda los nombres de los grupos garantizando que sean **únicos** (no admite duplicados). |
| `List<Contacto>` | `ArrayList` | Conserva el **orden de inserción** de los contactos. |
| `Map<String, List<Contacto>>` | `HashMap` | Relaciona cada grupo con la **lista de contactos** que pertenecen a él. |

Para el listado alfabético se ordena una copia de la lista con `Comparator.comparing()`, sin alterar el orden de inserción original.

## ▶️ Compilación y ejecución

```bash
javac *.java
java MenuPrincipal
```

> Si los acentos dan problemas al compilar en Windows, usa: `javac -encoding UTF-8 *.java`

## ✅ Funcionalidades

1. Agregar contacto (rechaza nombres duplicados)
2. Buscar contacto por nombre
3. Eliminar contacto (también lo quita de sus grupos)
4. Listar todos los contactos en orden alfabético
5. Agregar grupo (rechaza grupos duplicados)
6. Agregar contacto a un grupo existente
7. Listar grupos con su cantidad de miembros
8. Mostrar los contactos de un grupo específico
9. Salir

## 🧪 Casos límite manejados

- Agregar un contacto con nombre duplicado
- Buscar o eliminar un contacto que no existe
- Crear un grupo que ya existe
- Agregar a un grupo un contacto inexistente, o agregar a un grupo inexistente
- Agregar el mismo contacto dos veces al mismo grupo
- Nombre de contacto o de grupo vacío
- Opción de menú inválida (letras o números fuera de rango)
