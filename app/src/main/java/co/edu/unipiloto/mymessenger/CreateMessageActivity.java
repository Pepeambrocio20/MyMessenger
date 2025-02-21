package co.edu.unipiloto.mymessenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class CreateMessageActivity extends Activity {

    private MessageManager messageManager;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> messageHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_message);

        // Inicializar MessageManager
        messageManager = new MessageManager(this);

        // Configurar el ListView y el adaptador
        ListView listView = findViewById(R.id.listView);
        messageHistory = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, messageHistory);
        listView.setAdapter(adapter);

        // Cargar el historial de mensajes
        loadMessages();
    }

    public void onSendMessage(View view) {
        EditText messageView = findViewById(R.id.message);
        String messageText = messageView.getText().toString();

        // Guardar mensaje en el historial
        messageManager.saveMessage("Propietario: " + messageText);

        // Enviar mensaje al cuidador
        Intent intent = new Intent(this, ReceiveMessageActivity.class);
        intent.putExtra("message", "Propietario: " + messageText);
        startActivity(intent);

        // Limpiar el campo de texto
        messageView.setText("");

        // Actualizar el historial de mensajes
        loadMessages();
    }

    // Cargar el historial de mensajes
    private void loadMessages() {
        messageHistory.clear();
        List<String> messages = messageManager.getMessages();
        messageHistory.addAll(messages);
        adapter.notifyDataSetChanged();
    }
}