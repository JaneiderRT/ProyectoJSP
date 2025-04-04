package util;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;

public class PasswordExclusionStrategy implements ExclusionStrategy {

    /**
     * Excluye el atributo <code>password</code> de la clase <code>User</code>
     * @param fieldAttributes entidad a la cual se le excluira el atributo especificado.
     * @return <code>boolean</code> <ul>
     *      <li><code>true</code> si contiene el atributo password</li>
     *      <li><code>false</code> si no contiene el atributo password</li>
     * </ul>
     */
    @Override
    public boolean shouldSkipField(FieldAttributes fieldAttributes) {
        return fieldAttributes.getName().equals("password");
    }

    /**
     * Método para excluir clases - En este caso no se excluira ninguna clase.
     * @param aClass Clase especificada.
     * @return <code>false</code> No excluirá ninguna clase.
     */
    @Override
    public boolean shouldSkipClass(Class<?> aClass) {
        return false;
    }
}
