package com.ase.attendancehashchain.service.authentication;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by MarcoSalazar on 21/01/18.
 *
 */

public class FirebaseAuthentication {
   public FirebaseAuth getFirebaseAuthInstance() {
        return FirebaseAuth.getInstance();
   }
}
