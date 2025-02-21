package co.edu.unipiloto.mymessenger;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MessageManager {

    private static final String PREFS_NAME = "ChatPrefs";
    private static final String KEY_MESSAGES = "messages";

    private SharedPreferences sharedPreferences;

    public MessageManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    // Guardar un mensaje en el historial
    public void saveMessage(String message) {
        List<String> messages = getMessages();
        messages.add(message);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_MESSAGES, String.join(",", messages));
        editor.apply();
    }

    // Obtener todos los mensajes del historial
    public List<String> getMessages() {
        String messagesString = sharedPreferences.getString(KEY_MESSAGES, "");
        if (messagesString.isEmpty()) {
            return new ArrayList<>();
        }
        return new ArrayList<>(Arrays.asList(messagesString.split(",")));
    }

    // Limpiar el historial de mensajes
    public void clearMessages() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.remove(KEY_MESSAGES);
        editor.apply();
    }
}