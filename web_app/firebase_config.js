import { initializeApp } from "https://www.gstatic.com/firebasejs/10.12.5/firebase-app.js";
import { getFirestore, collection, onSnapshot } from "https://www.gstatic.com/firebasejs/10.12.5/firebase-firestore.js";
import { getAuth, signInWithEmailAndPassword, signOut, onAuthStateChanged } from "https://www.gstatic.com/firebasejs/10.12.5/firebase-auth.js";

// CONFIG
const firebaseConfig = {
    apiKey: "TU_API_KEY",
    authDomain: "tunehub-31853.firebaseapp.com",
    projectId: "tunehub-31853",
    storageBucket: "tunehub-31853.firebasestorage.app",
    messagingSenderId: "306140375799",
    appId: "1:306140375799:web:489972162bab3c3faac1f2"
};

const app = initializeApp(firebaseConfig);
const db = getFirestore(app);
const auth = getAuth(app);