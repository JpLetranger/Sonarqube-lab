package com.lab.security;

public class VulnerableApp {
    // Problema: Credenciales hardcodeadas
    private static final String PASSWORD = "admin123";
    
    // Problema: Variable no utilizada
    private String unusedVar = "never used";
    
    public static void main(String[] args) {
        VulnerableApp app = new VulnerableApp();
        app.login("admin");
        app.duplicateMethod("test");
    }
    
    // Problema: Método complejo
    public boolean login(String user) {
        if (user == null) {
            return false;
        } else if (user.equals("admin")) {
            if (PASSWORD.equals("admin123")) {
                System.out.println("Login ok");
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
    
    // Problema: Código duplicado
    public void duplicateMethod(String input) {
        if (input != null) {
            System.out.println("Processing: " + input);
        }
    }
    
    // Método idéntico (duplicado)
    public void anotherMethod(String input) {
        if (input != null) {
            System.out.println("Processing: " + input);
        }
    }
}
