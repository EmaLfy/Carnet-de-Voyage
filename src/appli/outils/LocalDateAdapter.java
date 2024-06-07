package appli.outils;

import com.google.gson.*;
import java.lang.reflect.Type;
import java.time.LocalDate;

/**
 * Classe LocalDateAdapter
 * Classe pour adapter le format de LocalDate pour la sérialisation et la désérialisation
 */
public class LocalDateAdapter implements JsonSerializer<LocalDate>, JsonDeserializer<LocalDate> {
    /**
     * Méthode pour sérialiser une date
     * @param src LocalDate
     * @param typeOfSrc Type
     * @param context Contexte de sérialisation
     * @return un élément JSON
     */
    @Override
    public JsonElement serialize(LocalDate src, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(src.toString());
    }
    /**
     * Méthode pour désérialiser une date
     * @param json Element JSON
     * @param typeOfT Type
     * @param context Contexte de désérialisation
     * @return une date
     */
    @Override
    public LocalDate deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        return LocalDate.parse(json.getAsString());
    }
}
