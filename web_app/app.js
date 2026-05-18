console.log("🔥 APP.JS ESTÁ CORRIENDO");

import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.5/firebase-app.js";

import {
    getFirestore,
    collection,
    onSnapshot,
    doc,
    updateDoc
} from "https://www.gstatic.com/firebasejs/10.12.5/firebase-firestore.js";

import {
    getAuth,
    signInWithEmailAndPassword,
    signOut,
    onAuthStateChanged
} from "https://www.gstatic.com/firebasejs/10.12.5/firebase-auth.js";


// ========================
// FIREBASE CONFIG
// ========================
const firebaseConfig = {
    apiKey: "AIzaSyD5aqiBJyn7cyKR_BTTpfD4nmf0-6En8Ng",
    authDomain: "tunehub-31853.firebaseapp.com",
    projectId: "tunehub-31853",
    storageBucket: "tunehub-31853.firebasestorage.app",
    messagingSenderId: "306140375799",
    appId: "1:306140375799:web:489972162bab3c3faac1f2"
};


// ========================
// INIT FIREBASE
// ========================
const app = initializeApp(firebaseConfig);
const db = getFirestore(app);
const auth = getAuth(app);

console.log("🔥 Firebase OK");


// ========================
// ELEMENTOS HTML
// ========================
const loginBox = document.querySelector(".login-box");
const panel = document.getElementById("panel");

const txtEmail = document.getElementById("txtEmail");
const txtPassword = document.getElementById("txtPassword");

const btnLogin = document.getElementById("btnLogin");
const btnLogout = document.getElementById("btnLogout");

const listaCitas = document.getElementById("listaCitas");

let citasRefGlobal = null;


// ========================
// VISTAS
// ========================
function showLogin() {
    loginBox.style.display = "block";
    panel.style.display = "none";
}

function showPanel() {
    loginBox.style.display = "none";
    panel.style.display = "block";
}

showLogin();


// ========================
// LOGIN
// ========================
btnLogin.addEventListener("click", async () => {

    try {
        const email = txtEmail.value;
        const password = txtPassword.value;

        const res = await signInWithEmailAndPassword(auth, email, password);

        console.log("LOGIN OK:", res.user.email);

    } catch (err) {
        console.log("ERROR LOGIN:", err.message);
        alert(err.message);
    }
});


// ========================
// LOGOUT
// ========================
btnLogout.addEventListener("click", () => {
    signOut(auth);
});


// ========================
// AUTH STATE
// ========================
onAuthStateChanged(auth, (user) => {

    console.log("AUTH STATE:", user);

    if (user) {

        showPanel();

        // 🔥 AQUÍ ESTÁ LA CLAVE
        const userId = user.uid;

        console.log("👤 UID:", userId);

        cargarCitas(userId);

    } else {
        showLogin();
    }
});


// ========================
// CARGAR CITAS (SUBCOLECCIÓN)
// ========================
function cargarCitas(userId) {

    const citasRef = collection(db, "usuarios", userId, "citas");

    onSnapshot(citasRef, (snapshot) => {

        console.log("🔥 CITAS:", snapshot.size);

        let html = "";

        snapshot.forEach((docSnap) => {

            const cita = docSnap.data();

            const vehiculo = `${cita.marca || ""} ${cita.modelo || ""}`.trim();

            html += `
                <div class="cita">

                    <h3>${cita.nombre || "Sin nombre"}</h3>

                    <p>Vehículo: ${vehiculo || "N/A"}</p>

                    <p>Año: ${cita.anio || "N/A"}</p>

                    <p>Placa: ${cita.placa || "N/A"}</p>

                    <p>Estado: ${cita.estado || "pendiente"}</p>

                </div>
            `;
        });

        listaCitas.innerHTML = html;
    });
}