package com.example.intento300

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.*
import android.util.Log

class MyReadingDatabaseRealTime {

    // Referencia a la base de datos
    private var database: DatabaseReference = FirebaseDatabase.getInstance().getReference("messages")

    fun addMessage() {
        val messageId = database.push().key // Genera un nuevo ID único para cada mensaje
        if (messageId != null) {
            database.child(messageId).setValue("Hola")
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d("FirebaseMessages", "Message added successfully.")
                    } else {
                        Log.e("FirebaseMessages", "Failed to add message.")
                    }
                }
        }
    }

    fun readAllMessages() {
        database.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                var index = 1
                for (data in snapshot.children) {
                    index++
                    val message = data.getValue(String::class.java)
                    Log.d("FirebaseMessages", "Mensaje $index: $message")
                    //println("Message: $message")
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.e("FirebaseMessages", "Failed to read messages: ${error.message}")
            }
        })
    }




}