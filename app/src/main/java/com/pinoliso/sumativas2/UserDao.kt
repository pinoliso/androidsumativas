package com.pinoliso.sumativas2

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UserDao {

    private val db = Firebase.firestore
    private val usersCollection = db.collection("users")

    fun validateLogin(email: String, password: String, onResult: (Boolean, User?, String?) -> Unit) {
        usersCollection
            .whereEqualTo("email", email)
            .whereEqualTo("password", password)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val document = result.documents.first()
                    val user = User(name = document.get("name")!!.toString(), document.get("email")!!.toString(), password = document.get("password")!!.toString())
                    onResult(true, user, "Inicio de sesión exitoso")
                } else {
                    onResult(false, null, "Email o contraseña incorrectos")
                }
            }
            .addOnFailureListener { exception ->
                onResult(false, null, "Error al validar el usuario: ${exception.message}")
            }
    }

    fun register(email: String, password: String, name: String, onResult: (Boolean, String?) -> Unit) {
        usersCollection
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { result ->
                if (result.isEmpty) {
                    val user = hashMapOf(
                        "name" to name,
                        "email" to email,
                        "password" to password
                    )

                    usersCollection
                        .add(user)
                        .addOnSuccessListener { documentReference ->
                            println("Documento agregado con ID: ${documentReference.id}")
                            onResult(true, "Registro exitoso")
                        }
                        .addOnFailureListener { e ->
                            println("Error al agregar documento: $e")
                            onResult(true, "Error interno")
                        }
                } else {
                    onResult(false, "Email ya existe")
                }
            }
            .addOnFailureListener { exception ->
                onResult(false, "Error al validar el usuario: ${exception.message}")
            }
    }

    fun validateRecovery(email: String, onResult: (Boolean, String?) -> Unit) {
        usersCollection
            .whereEqualTo("email", email)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    onResult(true, "Se ha enviado un correo.")
                } else {
                    onResult(false, "No existe el correo.")
                }
            }
            .addOnFailureListener { exception ->
                onResult(false, "Error al validar el usuario: ${exception.message}")
            }
    }
}