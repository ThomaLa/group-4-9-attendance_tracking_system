package com.ase.attendancehashchain.service.authentication.login;

/**
 * Created by MarcoSalazar on 28/01/18.
 *
 */

public interface Login {
    void signInWithEmailAndPassword(String email, String password);
}
