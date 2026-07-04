import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Gestiona los contactos y los grupos de la agenda.
 *
 * Elección de las colecciones (Java Collections Framework):
 * - Map<String, Contacto> (HashMap): almacena los contactos usando el nombre
 *   como clave, lo que permite buscar, insertar y eliminar en tiempo O(1).
 * - Set<String> (HashSet): guarda los nombres de los grupos garantizando
 *   que sean únicos (no admite duplicados).
 * - List<Contacto> (ArrayList): conserva el orden de inserción de los contactos.
 * - Map<String, List<Contacto>> (HashMap): relaciona cada grupo con la lista
 *   de contactos que pertenecen a él.
 */
public class Agenda {

    private Map<String, Contacto> contactos;              // nombre -> contacto (búsqueda rápida)
    private Set<String> grupos;                           // nombres de grupos (únicos)
    private List<Contacto> listaContactos;                // orden de inserción
    private Map<String, List<Contacto>> contactosPorGrupo; // grupo -> miembros

    /**
     * Constructor: inicializa todas las colecciones vacías.
     */
    public Agenda() {
        contactos = new HashMap<>();
        grupos = new HashSet<>();
        listaContactos = new ArrayList<>();
        contactosPorGrupo = new HashMap<>();
    }

    /**
     * Agrega un contacto a la agenda.
     * Si ya existe un contacto con ese nombre, muestra un mensaje de error.
     */
    public void agregarContacto(Contacto c) {
        if (contactos.containsKey(c.getNombre())) {
            System.out.println("Error: Ya existe un contacto con el nombre '" + c.getNombre() + "'");
            return;
        }
        contactos.put(c.getNombre(), c);
        listaContactos.add(c); // se conserva también el orden de llegada
        System.out.println("Contacto agregado: " + c.getNombre());
    }

    /**
     * Busca y retorna un contacto por su nombre.
     *
     * @return el contacto encontrado, o null si no existe
     */
    public Contacto buscarContacto(String nombre) {
        return contactos.get(nombre); // get() devuelve null si la clave no existe
    }

    /**
     * Elimina un contacto por su nombre.
     * También lo quita de la lista de inserción y de todos los grupos
     * a los que pertenecía, para no dejar referencias "huérfanas".
     *
     * @return true si se eliminó, false si no existía
     */
    public boolean eliminarContacto(String nombre) {
        Contacto eliminado = contactos.remove(nombre);
        if (eliminado == null) {
            return false; // no existía un contacto con ese nombre
        }
        listaContactos.remove(eliminado);
        for (List<Contacto> miembros : contactosPorGrupo.values()) {
            miembros.remove(eliminado);
        }
        return true;
    }

    /**
     * Muestra todos los contactos en orden alfabético por nombre.
     */
    public void listarContactos() {
        if (listaContactos.isEmpty()) {
            System.out.println("No hay contactos en la agenda.");
            return;
        }
        // Se ordena una COPIA para no alterar el orden de inserción original
        List<Contacto> ordenados = new ArrayList<>(listaContactos);
        ordenados.sort(Comparator.comparing(Contacto::getNombre, String.CASE_INSENSITIVE_ORDER));
        for (Contacto c : ordenados) {
            System.out.println(c);
        }
    }

    /**
     * Agrega un nuevo grupo de contactos.
     * El Set garantiza que no haya grupos duplicados.
     */
    public void agregarGrupo(String nombreGrupo) {
        // add() retorna false si el elemento ya existía en el Set
        if (grupos.add(nombreGrupo)) {
            contactosPorGrupo.put(nombreGrupo, new ArrayList<>());
            System.out.println("Grupo '" + nombreGrupo + "' creado");
        } else {
            System.out.println("Error: El grupo '" + nombreGrupo + "' ya existe");
        }
    }

    /**
     * Agrega un contacto existente a un grupo existente.
     * Valida que tanto el contacto como el grupo existan y que el
     * contacto no esté ya dentro del grupo.
     */
    public void agregarContactoAGrupo(String nombreContacto, String nombreGrupo) {
        Contacto c = contactos.get(nombreContacto);
        if (c == null) {
            System.out.println("Error: No existe un contacto con el nombre '" + nombreContacto + "'");
            return;
        }
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: No existe el grupo '" + nombreGrupo + "'");
            return;
        }
        List<Contacto> miembros = contactosPorGrupo.get(nombreGrupo);
        if (miembros.contains(c)) {
            System.out.println("El contacto '" + nombreContacto + "' ya pertenece al grupo '" + nombreGrupo + "'");
            return;
        }
        miembros.add(c);
        System.out.println("Contacto agregado al grupo '" + nombreGrupo + "'");
    }

    /**
     * Muestra todos los grupos disponibles y cuántos miembros tiene cada uno.
     */
    public void listarGrupos() {
        if (grupos.isEmpty()) {
            System.out.println("No hay grupos creados.");
            return;
        }
        System.out.println("Grupos disponibles:");
        for (String grupo : grupos) {
            int cantidad = contactosPorGrupo.get(grupo).size();
            System.out.println("- " + grupo + " (" + cantidad + " contacto(s))");
        }
    }

    /**
     * Muestra todos los contactos de un grupo específico.
     */
    public void mostrarContactosPorGrupo(String nombreGrupo) {
        if (!grupos.contains(nombreGrupo)) {
            System.out.println("Error: No existe el grupo '" + nombreGrupo + "'");
            return;
        }
        List<Contacto> miembros = contactosPorGrupo.get(nombreGrupo);
        if (miembros.isEmpty()) {
            System.out.println("El grupo '" + nombreGrupo + "' no tiene contactos todavía.");
            return;
        }
        System.out.println("Contactos en '" + nombreGrupo + "':");
        for (Contacto c : miembros) {
            System.out.println("- " + c);
        }
    }
}
