package com.ase.attendancehashchain.util.validator;

/**
 * Created by MarcoSalazar on 28/01/18.
 */

public interface InputValidator {
    boolean validateInputEmailAndPassword(String email, String password);
}