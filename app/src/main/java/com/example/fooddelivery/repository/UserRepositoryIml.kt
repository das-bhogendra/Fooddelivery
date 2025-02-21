package com.example.fooddelivery.repository



import com.example.fooddelivery.model.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase

class UserRepositoryImpl : UserRepository {

    var auth: FirebaseAuth = FirebaseAuth.getInstance()

    var database: FirebaseDatabase = FirebaseDatabase.getInstance()
    var ref= database.reference.child("users")

    override fun login(email: String, password: String, callback: (Boolean, String) -> Unit) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Login success")
            } else {
                callback(false, it.exception?.message.orEmpty())
            }
        }
    }

    override fun signUp(
        email: String, password: String,
        callback: (Boolean, String, Any?) -> Unit
    ) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                val userId = auth.currentUser?.uid.orEmpty()
                callback(true, "Registration success",
                    auth.currentUser?.uid.toString())
            } else {
                callback(false, it.exception?.message.orEmpty(),"")
            }
        }
    }

    override fun addUserToDatabase(
        userID: String,
        userModel: UserModel,
        callback: (Boolean, String) -> Unit
    ) {
        ref.child(userID).setValue(userModel).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "User added to database successfully")
            } else {
                callback(false, it.exception?.message.orEmpty())
            }
        }
    }

    override fun forgetPassword(email: String, callback: (Boolean, String) -> Unit) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener {
            if (it.isSuccessful) {
                callback(true, "Password reset link sent to $email")
            } else {
                callback(false, it.exception?.message.orEmpty())
            }
        }
    }

    override fun getCurrentUser(): FirebaseUser? {
        return auth.currentUser
    }
}
