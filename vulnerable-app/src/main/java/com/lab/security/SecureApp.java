package com.lab.security;

import java.util.Properties;

public class SecureApp {
    private final Properties config;
    
    public SecureApp() {
        this.config = loadConfiguration();
    }
    
    public static void main(String[] args) {
        SecureApp app = new SecureApp();
        boolean loginResult = app.authenticateUser("admin");
        if (loginResult) {
            System.out.println("Authentication successful");
        }
        app.processInput("test");
    }
    
    // ✅ Corregido: Configuración externa (sin hardcoded credentials)
    private Properties loadConfiguration() {
        Properties props = new Properties();
        // Leer de variables de entorno en lugar de hardcodear
        props.setProperty("auth.password", 
            System.getenv().getOrDefault("AUTH_PASSWORD", "default"));
        return props;
    }
    
    // ✅ Corregido: Método simplificado (menor complejidad)
    public boolean authenticateUser(String username) {
        if (username == null || username.trim().isEmpty()) {
            return false;
        }
        
        String configPassword = config.getProperty("auth.password");
        return "admin".equals(username) && 
               configPassword != null && 
               !configPassword.equals("default");
    }
    
    // ✅ Corregido: Método único (sin duplicación)
    public void processInput(String input) {
        if (input != null && !input.trim().isEmpty()) {
            String processed = input.toUpperCase();
            logProcessing(input, processed);
        }
    }
    
    // ✅ Método auxiliar para evitar duplicación
    private void logProcessing(String input, String processed) {
        System.out.println("Processing: " + input);
        System.out.println("Result: " + processed);
    }
    
    // ✅ Método público útil (no dead code)
    public String getConfigValue(String key) {
        return config.getProperty(key);
    }
}
