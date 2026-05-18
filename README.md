# 🚗 TuneHub

TuneHub es una aplicación móvil desarrollada para Android enfocada en la gestión de servicios automotrices y personalización de vehículos (tuning).  
El proyecto busca ofrecer una experiencia moderna y confiable para clientes interesados en modificaciones, mantenimiento y organización de citas para talleres automotrices.

Además, el proyecto incluye un panel web administrativo pensado para la gestión de citas del taller.

---

# 📱 Tecnologías Utilizadas

## Android
- Kotlin
- XML para interfaces gráficas
- Firebase Authentication
- Cloud Firestore
- Gemini API (Google AI)
- RecyclerView
- Intents y múltiples Activities

## Web
- HTML
- CSS
- JavaScript
- Firebase Firestore
- Firebase Authentication

---

# 🎯 Objetivo del Proyecto

El objetivo de TuneHub es facilitar la interacción entre clientes y talleres automotrices mediante una aplicación móvil capaz de:

- Registrar usuarios
- Iniciar sesión de forma segura
- Registrar vehículos
- Agendar citas automotrices
- Visualizar citas registradas
- Consultar servicios disponibles
- Resolver dudas automotrices mediante inteligencia artificial

---

# 👥 Público Objetivo

TuneHub está orientado principalmente a:

- Personas interesadas en tuning y personalización de vehículos
- Clientes de talleres automotrices
- Usuarios que desean organizar servicios y mantenimiento de sus vehículos desde una aplicación móvil

---

# 🔥 Funcionalidades Implementadas

## ✅ Sistema de autenticación
Se implementó Firebase Authentication para:
- Registro de usuarios
- Inicio de sesión seguro
- Manejo de sesiones activas

---

## ✅ Gestión de vehículos
Los usuarios pueden:
- Registrar vehículos
- Guardar marca, modelo, año y placa
- Visualizar sus vehículos almacenados

La información se almacena en Cloud Firestore utilizando una estructura basada en colecciones y documentos.

---

## ✅ Gestión de citas
La aplicación permite:
- Agendar citas automotrices
- Asociar servicios al vehículo
- Consultar citas registradas

Cada usuario almacena sus citas utilizando su UID generado por Firebase Authentication.

---

## ✅ Servicios automotrices
La aplicación incluye pantallas informativas sobre:
- Servicios básicos
- Opciones de tuning
- Tipos de modificaciones automotrices

---

# 🤖 TuneBot — Integración de Inteligencia Artificial

Se implementó una integración con la API de Gemini IA de Google para desarrollar "TuneBot", un asistente automotriz inteligente.

TuneBot puede responder consultas relacionadas con:
- Fallas mecánicas
- Mantenimiento
- Tuning
- Personalización de vehículos
- Recomendaciones básicas automotrices

La integración fue desarrollada utilizando:
- OkHttpClient
- Solicitudes HTTP
- Procesamiento de respuestas JSON

---

# 🛠️ Mejoras y Correcciones Realizadas

Durante el desarrollo del proyecto se realizaron múltiples correcciones y mejoras, entre ellas:

- Corrección de botones duplicados en pantallas XML
- Corrección de pantallas que no cargaban correctamente
- Optimización de navegación entre Activities
- Corrección de errores visuales en interfaces
- Ajustes en Firebase Authentication
- Mejora en almacenamiento de datos en Firestore

## ✅ Optimización de TuneBot
Se realizaron ajustes al prompt de Gemini IA para:
- Reducir respuestas demasiado largas
- Generar respuestas más claras y breves
- Mejorar la experiencia del usuario

---

# 🌐 Panel Web Administrativo

El proyecto también incluye un panel web desarrollado con:
- HTML
- CSS
- JavaScript
- Firebase

La intención del panel era:
- Visualizar citas registradas desde Android
- Administrar estados de citas
- Facilitar la gestión del taller

Sin embargo, por cuestiones personales que desencadenó falta de tiempo y dificultades relacionadas con la estructura de Firestore y sincronización de colecciones anidadas, no se logró solucionar completamente el problema de lectura de datos en la aplicación web.

Además, algunos avances del panel web no pudieron subirse correctamente a GitHub debido a errores relacionados con sincronización y control de versiones.

A pesar de ello, el panel web sí logró:
- Implementar autenticación
- Conectarse a Firebase
- Detectar usuarios autenticados
- Realizar pruebas de lectura en Firestore

---

# 🧩 Arquitectura General

La aplicación Android está organizada mediante múltiples Activities, entre ellas:

- LoginActivity
- DashboardActivity
- AgregarVehiculoActivity
- VehiculosActivity
- AgendarCitaActivity
- MisCitasActivity
- ServiciosActivity
- TuningActivity
- AsistenteIAActivity

También se implementaron:
- Adaptadores personalizados
- Clases modelo
- Manejo dinámico de listas mediante RecyclerView

---

# ☁️ Base de Datos

Se utilizó Cloud Firestore como base de datos NoSQL.

La estructura se organiza mediante:
- Colecciones
- Documentos
- Subcolecciones

Ejemplo:

usuarios → UID del usuario → citas / vehiculos

Esto permite almacenar información individual por usuario y sincronizarla en tiempo real.

---

# 📌 Estado Actual del Proyecto

## Android
✅ Funcional y operativo

## Web
⚠️ Parcialmente funcional (pendiente solución de lectura de datos desde Firestore)

---

# 👨‍💻 Autor - Andrés Culajay - 23000109

Proyecto desarrollado como parte de aprendizaje y práctica de desarrollo móvil Android, integración con Firebase y uso de APIs de Inteligencia Artificial.
