package model;

public class User {
    private int id;
    private String name;
    private String lastname;
    private String email;
    private String username;
    private String password;

    /**
     * @description Constructor por defecto.
     */
    public User() {}

    /**
     * @param id Identificador del usuario a buscar o a eliminar.
     * @description Constructor para realizar la consulta o la eliminación del usuario en particular.
     */
    public User(int id) {
        this.id = id;
    }

    /**
     *
     * @param username Es el nombre de usuario reconocido por el sistema para el ingreso.
     * @param password Es la contraseña del usuario para iniciar sesión en el sistema.
     * @description Constructor para que el usuario inicie sesión en el sistema.
     */
    public User(String username, String password) {

    }

    /**
     *
     * @param name Es el nombre real de la persona que utilizará el sistema.
     * @param lastname Es el apellido de la persona que utilizará el sistema.
     * @param email Es el correo electrónico de la persona que utilizará el sistema.
     * @param username Es el nombre de usuario reconocido por el sistema para el ingreso.
     * @param password Es la contraseña del usuario para iniciar sesión en el sistema.
     * @description Constructor para crear un usuario en la base de datos.
     */
    public User(String name, String lastname, String email, String username, String password) {
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    /**
     *
     * @param name Es el nombre real de la persona que utilizará el sistema.
     * @param lastname Es el apellido de la persona que utilizará el sistema.
     * @param email Es el correo electrónico de la persona que utilizará el sistema.
     * @param username Es el nombre de usuario reconocido por el sistema para el ingreso.
     * @param password Es la contraseña del usuario para iniciar sesión en el sistema.
     * @description Constructor para listar todos los usuarios existentes de la BDD.
     */
    public User(int id, String name, String lastname, String email, String username, String password) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{\n" +
                "[id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", email='" + email + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + "],\n" +
                '}';
    }
}
