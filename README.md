# SonarQube Security Lab 🔒
> Proyecto de Análisis Estático de Software - Demo de Detección y Corrección de Vulnerabilidades

[![Java](https://img.shields.io/badge/Java-11+-red.svg)](https://openjdk.java.net/)
[![Maven](https://img.shields.io/badge/Maven-3.8+-blue.svg)](https://maven.apache.org/)
[![SonarQube](https://img.shields.io/badge/SonarQube-LTS-green.svg)](https://www.sonarqube.org/)
[![Docker](https://img.shields.io/badge/Docker-Latest-blue.svg)](https://www.docker.com/)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)

Una aplicación Java diseñada para demostrar la implementación de análisis estático de software usando SonarQube, desde la detección de vulnerabilidades críticas hasta su corrección completa.

## 📋 Características

### 🔍 Análisis Estático Completo
- **Detección de vulnerabilidades** de seguridad
- **Identificación de code smells** y malas prácticas
- **Análisis de complejidad** ciclomática
- **Detección de código duplicado** y dead code
- **Métricas de cobertura** de pruebas

### 🛠️ Ciclo de Mejora Demostrado
- **Código vulnerable inicial** con 5+ problemas críticos
- **Quality Gate fallido** → **Quality Gate aprobado**
- **Refactorización guiada** por métricas de SonarQube
- **Comparación antes/después** con métricas cuantificables
- **Best practices** de desarrollo seguro

## 🏗️ Arquitectura del Proyecto

```
┌─────────────────┐    ┌──────────────────┐    ┌─────────────────┐    ┌──────────────┐
│   Código Java   │───▶│  Maven Build     │───▶│   SonarQube     │───▶│   Análisis   │
│  (Vulnerable)   │    │  + SonarScanner  │    │   (Docker)      │    │  de Calidad  │
└─────────────────┘    └──────────────────┘    └─────────────────┘    └──────────────┘
         │                       │                        │                    │
    Source Code            Build & Test              Static Analysis      Quality Reports
```

## 🛠️ Stack Tecnológico

### Análisis y Build
- **Java 11+** - Lenguaje principal
- **Maven 3.8+** - Gestión de dependencias y build
- **JUnit 4.13** - Framework de testing
- **JaCoCo** - Cobertura de código

### Herramientas de Calidad
- **SonarQube LTS** - Plataforma de análisis estático
- **SonarScanner Maven Plugin** - Integración con Maven
- **OWASP Dependency Check** - Análisis de dependencias
- **SpotBugs** - Detección de bugs adicional

### Infraestructura
- **Docker** - Containerización de SonarQube
- **WSL2** - Entorno de desarrollo Linux en Windows
- **Git** - Control de versiones

### IDE y Herramientas
- **VSCode/IntelliJ** - IDE principal
- **SonarLint** - Análisis en tiempo real
- **Maven CLI** - Build automation

## 📦 Instalación y Configuración

### Prerrequisitos
- **Java 11+** instalado
- **Maven 3.8+** configurado
- **Docker** corriendo en WSL2/Linux
- **Git** para control de versiones

### Setup del Entorno

```bash
# 1. Clonar el repositorio
git clone https://github.com/tu-usuario/sonarqube-security-lab.git
cd sonarqube-security-lab

# 2. Iniciar SonarQube con Docker
docker run -d --name sonarqube \
    -p 9000:9000 \
    -v sonarqube_data:/opt/sonarqube/data \
    -v sonarqube_extensions:/opt/sonarqube/extensions \
    -v sonarqube_logs:/opt/sonarqube/logs \
    sonarqube:lts-community

# 3. Esperar inicio completo (2-3 minutos)
docker logs -f sonarqube

# 4. Configurar SonarQube
# - Ir a http://localhost:9000
# - Login: admin/admin
# - Generar token de análisis
```

### Estructura del Proyecto
```
sonarqube-security-lab/
├── README.md                 # Este archivo
├── pom.xml                  # Configuración Maven + SonarQube
├── .gitignore              # Archivos ignorados
├── analisis_final.md       # Documentación de resultados
└── src/
    ├── main/java/com/lab/security/
    │   ├── VulnerableApp.java    # Versión con vulnerabilidades
    │   ├── SecureApp.java        # Versión corregida
    │   └── utils/
    │       └── SecurityUtils.java # Utilidades de seguridad
    └── test/java/com/lab/security/
        ├── VulnerableAppTest.java # Tests versión vulnerable
        └── SecureAppTest.java     # Tests versión segura
```

## 🚀 Ejecución del Análisis

### Análisis de Código Vulnerable
```bash
# 1. Compilar proyecto
mvn clean compile test

# 2. Ejecutar análisis inicial (código vulnerable)
mvn sonar:sonar \
    -Dsonar.projectKey=vulnerable-app \
    -Dsonar.projectName="Security Lab" \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.login=YOUR_SONAR_TOKEN

# 3. Revisar resultados en http://localhost:9000
```

### Análisis de Código Corregido
```bash
# 1. Cambiar a versión segura del código
# (Ver instrucciones en el ejercicio)

# 2. Re-ejecutar análisis
mvn clean compile test
mvn sonar:sonar \
    -Dsonar.projectKey=vulnerable-app \
    -Dsonar.projectName="Security Lab" \
    -Dsonar.host.url=http://localhost:9000 \
    -Dsonar.login=YOUR_SONAR_TOKEN

# 3. Comparar mejoras en SonarQube dashboard
```

## 📊 Resultados Demostrados

### Métricas Antes vs Después

| Métrica | Código Vulnerable | Código Corregido | Mejora |
|---------|-------------------|------------------|---------|
| **Problemas Críticos** | 5+ | 0 | 100% ✅ |
| **Quality Gate** | ❌ FAILED | ✅ PASSED | ✅ |
| **Vulnerabilidades** | 3+ | 0 | 100% ✅ |
| **Code Smells** | 8+ | 0-2 | 80%+ ✅ |
| **Cobertura de Tests** | <50% | >80% | +30% ✅ |
| **Código Duplicado** | >5% | <1% | -4% ✅ |

### Problemas Detectados y Corregidos

#### 🔴 Vulnerabilidades de Seguridad
- **Hardcoded Credentials**: Contraseñas en código fuente
- **SQL Injection Risk**: Queries sin parametrizar
- **Weak Cryptography**: Uso de algoritmos MD5 inseguros
- **Input Validation**: Falta de validación de entradas

#### 💨 Code Smells Resueltos
- **Dead Code**: Variables y métodos sin usar
- **Code Duplication**: Métodos idénticos duplicados
- **Cognitive Complexity**: Lógica compleja simplificada
- **Naming Conventions**: Variables con nombres poco descriptivos

## 🎯 Casos de Uso Educativos

### 📚 Aprendizaje Demostrado
- **Materia:** Análisis Estático de Software y DevSecOps
- **Objetivo:** Implementar análisis de calidad en pipeline de desarrollo
- **Alcance:** Detección, análisis y corrección de vulnerabilidades

### 💼 Skills Técnicos Desarrollados
- **Static Code Analysis** con SonarQube
- **Security-First Development** principles
- **Quality Gate** configuration y enforcement
- **CI/CD Integration** preparedness
- **Maven Project** management

## 🔒 Aspectos de Seguridad

### Vulnerabilidades Abordadas
- **OWASP Top 10** compliance checking
- **Sensitive Data Exposure** prevention
- **Injection Attack** mitigation
- **Cryptographic** best practices

### Best Practices Implementadas
- **Environment-based configuration** (no hardcoding)
- **Input sanitization** y validation
- **Secure hashing algorithms** (SHA-256)
- **Prepared statements** para prevenir SQL injection

## 📈 Integración CI/CD

### Pipeline Preparado
```yaml
# Ejemplo para GitHub Actions
name: SonarQube Analysis
on:
  push:
    branches: [main, develop]
  pull_request:
    branches: [main]

jobs:
  sonarqube:
    runs-on: ubuntu-latest
    steps:
      - uses: actions/checkout@v3
      - name: Set up JDK 11
        uses: actions/setup-java@v3
        with:
          java-version: 11
      - name: Run SonarQube analysis
        run: mvn sonar:sonar -Dsonar.login=${{ secrets.SONAR_TOKEN }}
```

### Quality Gates Configurados
- **Coverage**: >80% cobertura de líneas
- **Duplications**: <3% código duplicado
- **Maintainability**: Rating A
- **Reliability**: Rating A
- **Security**: Rating A

## 🛠️ Comandos Útiles

### Docker SonarQube Management
```bash
# Iniciar SonarQube
docker start sonarqube

# Parar SonarQube
docker stop sonarqube

# Ver logs
docker logs -f sonarqube

# Limpiar y reinstalar
docker rm -f sonarqube
docker volume rm sonarqube_data sonarqube_extensions sonarqube_logs
```

### Maven Commands
```bash
# Compilar y probar
mvn clean compile test

# Solo análisis SonarQube
mvn sonar:sonar -Dsonar.login=TOKEN

# Generar reporte de cobertura
mvn jacoco:report

# Verificar dependencias vulnerables
mvn org.owasp:dependency-check-maven:check
```

## 📝 Documentación Adicional

### Archivos de Configuración
- `pom.xml` - Configuración Maven con plugins de calidad
- `sonar-project.properties` - Configuración específica de SonarQube
- `.gitignore` - Archivos excluidos del control de versiones

### Reportes Generados
- **SonarQube Dashboard** - Métricas interactivas
- **JaCoCo Coverage** - Reporte de cobertura HTML
- **Maven Site** - Documentación del proyecto

## 🤝 Contribución

Este proyecto está diseñado para propósitos educativos. Para contribuir:

1. **Fork** el repositorio
2. **Create feature branch**: `git checkout -b feature/mejora-seguridad`
3. **Add security improvements**: Implementar nuevas validaciones
4. **Commit changes**: `git commit -am 'Add nueva validación XSS'`
5. **Push to branch**: `git push origin feature/mejora-seguridad`
6. **Submit Pull Request** con análisis SonarQube limpio

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver [LICENSE](LICENSE) para detalles.

## 📞 Contacto y Soporte

**Desarrollador:** Juan Pablo Gajardo  
**Email:** jgajardodev@gmail.com  
**LinkedIn:** https://www.linkedin.com/in/juanpgajardo/  
**GitHub:** https://github.com/JpLetranger

### 🆘 Troubleshooting
Para problemas comunes, consulta la [Wiki del proyecto](../../wiki) o abre un [Issue](../../issues).

---

### 🏆 Tecnologías y Herramientas

<div align="center">
  
![Java](https://img.shields.io/badge/-Java-007396?style=flat-square&logo=java&logoColor=white)
![Maven](https://img.shields.io/badge/-Maven-C71A36?style=flat-square&logo=apache-maven&logoColor=white)
![SonarQube](https://img.shields.io/badge/-SonarQube-4E9BCD?style=flat-square&logo=sonarqube&logoColor=white)
![Docker](https://img.shields.io/badge/-Docker-2496ED?style=flat-square&logo=docker&logoColor=white)
![JUnit](https://img.shields.io/badge/-JUnit-25A162?style=flat-square&logo=junit5&logoColor=white)
![Git](https://img.shields.io/badge/-Git-F05032?style=flat-square&logo=git&logoColor=white)
![WSL](https://img.shields.io/badge/-WSL-0078D4?style=flat-square&logo=windows&logoColor=white)
![Linux](https://img.shields.io/badge/-Linux-FCC624?style=flat-square&logo=linux&logoColor=black)

</div>

---

<div align="center">
  <p><strong>DevOps: Integración Continua - Análisis Estático de Software</strong></p>
  <p>© 2025 SonarQube Security Lab - Proyecto Educativo</p>
</div>
